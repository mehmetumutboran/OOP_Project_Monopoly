package network.server;

import domain.controller.ConnectGameHandler;
import network.client.Client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * {@link Server} class that opens a server
 * It will use Observer Pattern to publish new Connected {@link Client} to {@link ConnectGameHandler}  ???? Not possible??
 */
public class Server implements Runnable {
    private ServerSocket ss;

    private static final int maxClientsCount = 12;

    private static final ClientHandler[] clientThreads = new ClientHandler[maxClientsCount];


    public Server(int port) {
        try {
            ss = new ServerSocket(port);
            System.out.println("server crated with the port: " + port);
            (new Thread(this)).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                        (new Thread(clientThreads[i])).start();
                        break;
                    }
                }
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
