package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{
    private ServerSocket ss;

    private ClientHandler[] clientThreads;

    private final int maxClientsCount = 12;

    public Server(int port) {
        clientThreads = new ClientHandler[maxClientsCount];
        try {
            ss = new ServerSocket(port);
            System.out.println("Server crated with the port: " + port);
            (new Thread(this)).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        while (true){
            try {
                Socket clientSocket = this.ss.accept();
                int i = 0;
                for (; i < maxClientsCount; i++) {
                    if (clientThreads[i] == null) {
                        clientThreads[i] = new ClientHandler(clientSocket, clientThreads);
                        (new Thread(clientThreads[i])).start();
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }
}
