package network.server;

import domain.util.GameInfo;
import network.client.clientFacade.ClientFacade;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

/**
 * {@link Server} class that opens a server
 */
public class Server implements Runnable {
    private ServerSocket ss;

    private static final int maxClientsCount = 12;

    private volatile ClientHandler[] clientThreads = new ClientHandler[maxClientsCount];
    private volatile String[] clientNames = new String[maxClientsCount]; //TODO
    private volatile String[][] clientInfo = new String[maxClientsCount][2];


    public Server(int port) {
        try {
            ss = new ServerSocket(port);
            System.out.println("server crated with the port: " + port);
            (new Thread(this)).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void removeClient(ClientHandler clientHandler) {
        System.out.println("=====Before shifting in the server \n" + Arrays.deepToString(clientInfo));

        int i;
        for (i = 0; i < maxClientsCount; i++) {
            if (clientThreads[i] == clientHandler) {
                clientThreads[i] = null;
                clientNames[i] = null;
                clientInfo[i] = new String[]{null, null};
                break;
            }
        }

        for (int j = i; j < maxClientsCount - 1; j++) {
            if (clientThreads[j + 1] == null) continue;
            clientThreads[j] = clientThreads[j + 1];
            clientThreads[j + 1] = null;
            clientThreads[j].setIndex(j);

            clientNames[j] = clientNames[j + 1];
            clientNames[j + 1] = null;

            clientInfo[j] = clientInfo[j + 1];
            clientInfo[j + 1] = new String[]{null, null};
        }


        System.out.println("=============" + Arrays.toString(clientThreads));
        System.out.println("===After shifting in the server \n" + Arrays.deepToString(clientInfo));
    }

    public void setClientInfo(String line) {
        for (int i = 0; i < maxClientsCount; i++) {
            if (clientNames[i] == null) {
                clientNames[i] = line;
                clientInfo[i][0] = line;
                break;
            }
        }

        System.out.println("\n\n---------============-------\n" + Arrays.toString(clientNames)
                + "\n---------============-------\n\n");
    }


    public ClientHandler getClientHandler(String username) {
        for (int i = 0; i < clientNames.length; i++) {
            if (clientNames[i] == null) continue;
            if (clientNames[i].equals(username)) return clientThreads[i];
        }
        return null;
    }


    /**
     * server thread that accepts new Client and assigns a {@link ClientHandler} to it.
     */
    @Override
    public void run() {

        while (true) {
            try {
                Socket clientSocket = this.ss.accept();
                int i = 0;
                for (; i < maxClientsCount; i++) {
                    if (clientThreads[i] == null) {
                        clientThreads[i] = new ClientHandler(clientSocket, i);
                        if (clientSocket.getInetAddress().getHostAddress().equals("127.0.0.1"))
                            clientInfo[i][1] = InetAddress.getLocalHost().getHostAddress();
                        else clientInfo[i][1] = clientSocket.getInetAddress().getHostAddress();
                        (new Thread(clientThreads[i], "ClientThread " + i)).start();
                        break;
                    }
                }
                System.out.println("\n\n ClientThreads: " + Arrays.toString(clientThreads) + "\n\n");
            } catch (IOException e) {
                System.out.println("ServerSocket is now closed!!");
                for (int i = 0; i < clientThreads.length - 1; i++) {
                    if (clientThreads[i] != null)
                        clientThreads[i].terminate();
                }
                break;
            }

        }

    }

    public ServerSocket getSs() {
        return ss;
    }

    public synchronized void sendAll(String m) {
        for (ClientHandler clientThread : clientThreads) {
            if (clientThread == null) continue;
            clientThread.send(m);
        }
    }

    public synchronized void sendToOne(int index, String response) {
        clientThreads[index].send(response);
    }

    public int getClientIndex(String username) {
        if (GameInfo.getInstance().isBot(username))
            return 0; // Bot messages are sent to host
        for (int i = 0; i < clientNames.length; i++) {
            if (clientNames[i] == null) continue;
            if (clientNames[i].equals(username)) return i;
        }
        return -1;
    }

    public int getTotalNumPlayers() {
        int count = 0;
        for (int i = 0; i < clientThreads.length; i++) {
            if (clientThreads[i] != null) count++;
        }
        return count;
    }

    public String getClientNameFromIndex(int i) {
        return clientNames[i];
    }

    public String[][] getClientInfo() {
        int j = 0;
        for (int i = 0; i < clientInfo.length; i++) {
            if (clientInfo[i][0] != null && clientInfo[i][0].equals(ClientFacade.getInstance().getUsername()) && i != 0) {
                System.out.println("\nIteration " + i + "\n CLient Info == " + Arrays.deepToString(clientInfo));
                String[] ithArray = clientInfo[i].clone();
                clientInfo[i] = clientInfo[0].clone();
                clientInfo[0] = ithArray.clone();
            }
        }
        System.out.println("In get client info" + Arrays.deepToString(clientInfo));
        return clientInfo;
    }

    public synchronized void kick(int i) {
        clientThreads[i].terminate();
        removeClient(clientThreads[i]);

    }
}
