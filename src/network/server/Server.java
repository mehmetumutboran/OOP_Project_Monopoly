package network.server;

import domain.controller.ConnectGameHandler;
import network.client.Client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

/**
 * {@link Server} class that opens a server
 * It will use Observer Pattern to publish new Connected {@link Client} to {@link ConnectGameHandler}  ???? Not possible??
 */
public class Server implements Runnable {
    private ServerSocket ss;
    private boolean isMulti;

    private static final int maxClientsCount = 12;

    private volatile static ClientHandler[] clientThreads = new ClientHandler[maxClientsCount];
    private volatile static String[] clientNames = new String[maxClientsCount];


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

    public synchronized static void removeClient(ClientHandler clientHandler) {
        for (int i = 0; i < maxClientsCount; i++) {
            if (clientThreads[i] == clientHandler) {
                try {
                    clientThreads[i] = null;
                    sendAll("X" + clientNames[i]);
                    clientNames[i] = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void setClientInfo(String line) {
        for (int i = 0; i < maxClientsCount; i++) {
            if (clientNames[i] == null) {
                clientNames[i] = line;
                break;
            }
        }
        System.out.println("\n\n---------============-------\n" + Arrays.toString(clientNames)
                + "\n---------============-------\n\n");
    }

    /**
     * Sets and initialized {@link ClientHandler}
     * It was needed before since this class used to use Observer Pattern to publish new Connected {@link Client}
     * But now probably it is not needed.
     *
     * @param i
     * @param clientSocket
     */
    private synchronized void setClientThread(int i, Socket clientSocket) { //TODO delete
        clientThreads[i] = new ClientHandler(clientSocket);
    }

    public ClientHandler getClientHandler(String username) {
        for (int i = 0; i < clientNames.length; i++) {
            if (clientNames[i] == null) continue;
            if (clientNames[i].equals(username)) return clientThreads[i];
        }
        return null;
    }


    /**
     * server thread that accepts new {@link Client} and assigns a {@link ClientHandler} to it.
     */
    @Override
    public void run() {

        while (true) {
            try {
                Socket clientSocket = this.ss.accept();
                int i = 0;

                for (; i < maxClientsCount; i++) {
                    if (clientThreads[i] == null) {
                        clientThreads[i] = new ClientHandler(clientSocket);
                        if (!isMulti && !clientSocket.getInetAddress().getHostAddress().equals("127.0.0.1")) {

                            clientThreads[i].terminate();
                            clientThreads[i] = null;
                            //                            continue;
                        } else {

                            (new Thread(clientThreads[i])).start();
                        }

                        break;
                    }
                }

                System.out.println("\n\n ClientThreads: " + Arrays.toString(clientThreads) + "\n\n");
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }

    public ServerSocket getSs() {
        return ss;
    }

    public synchronized static void sendAll(String m) throws IOException {
        for (ClientHandler clientThread : clientThreads) {
            if (clientThread == null) continue;
            clientThread.send(m);
        }
    }

}
