package network.server;

import domain.util.GameInfo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

/**
 * {@link Server} class that opens a server
 */
public class Server implements Runnable {
    private ServerSocket ss;
    private boolean isMulti;

    private static final int maxClientsCount = 12;

    private volatile static ClientHandler[] clientThreads = new ClientHandler[maxClientsCount];
    private volatile static String[] clientNames = new String[maxClientsCount]; //TODO


    public Server(int port, boolean isMulti) {
        try {
            ss = new ServerSocket(port);
            this.isMulti = isMulti;
            System.out.println("server crated with the port: " + port);
            (new Thread(this)).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void removeClient(ClientHandler clientHandler) {
        int i;
        for (i = 0; i < maxClientsCount; i++) {
            if (clientThreads[i] == clientHandler) {
                clientThreads[i] = null;
                clientNames[i] = null;
                break;
            }
        }

        for (int j = i; j < maxClientsCount - 1; j++) {
            if(clientThreads[j] == null) continue;
            clientThreads[j] = clientThreads[j + 1];
            clientThreads[j].setIndex(j);
            clientNames[j] = clientNames[j + 1];
        }

    }

    public static void setClientInfo(String line) {
        for (int i = 0; i < maxClientsCount; i++) {
            if (line.equals(clientNames[i])) {
                clientThreads[i].terminate();
                break;
            } else if (clientNames[i] == null) {
                clientNames[i] = line;
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
                        if (!isMulti && !clientSocket.getInetAddress().getHostAddress().equals("127.0.0.1")) {
                            clientThreads[i].terminate();
                            clientThreads[i] = null;
                        } else {
                            (new Thread(clientThreads[i], "ClientThread " + i)).start();
                        }
                        break;
                    }
                }
                System.out.println("\n\n ClientThreads: " + Arrays.toString(clientThreads) + "\n\n");
            } catch (IOException e) {
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

}
