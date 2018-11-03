package network;

import network.listeners.NewClientListener;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * {@link Server} class that opens a server
 * It will use Observer Pattern to publish new Connected {@link Client} to {@link domain.ConnectGameHandler}
 */
public class Server implements Runnable {
    private ServerSocket ss;

    private ArrayList<NewClientListener> newClientListeners;
    private static final int maxClientsCount = 12;

    private static final ClientHandler[] clientThreads = new ClientHandler[maxClientsCount];


    public Server(int port) {

        newClientListeners = new ArrayList<>();
        try {
            ss = new ServerSocket(port);
            System.out.println("Server crated with the port: " + port);
            (new Thread(this)).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private synchronized void setClientThread(int i, Socket clientSocket){
        clientThreads[i] = new ClientHandler(clientSocket, clientThreads);
        publishNewClientEvent();
    }

    private void publishNewClientEvent(){
        for(NewClientListener nl : newClientListeners) {
            if (nl == null) continue;
            nl.onNewClientEvent();
        }
    }

    public boolean addNewClientListener(NewClientListener nl){
        return newClientListeners.add(nl);
    }

    /**
     * Server thread that accepts new {@link Client} and assigns a {@link ClientHandler} to it.
     */
    @Override
    public void run() {

        while (true) {
            try {
                Socket clientSocket = this.ss.accept();
                int i = 0;
                for (; i < maxClientsCount; i++) {
                    if (clientThreads[i] == null) {
                        setClientThread(i, clientSocket);
                        (new Thread(clientThreads[i])).start();
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }

    public synchronized static void sendAll(String m){
        for (ClientHandler clientThread : clientThreads) {
            if (clientThread == null) continue;
            clientThread.send(m);
        }
    }

    public synchronized static void sendAllExceptOne(String m, ClientHandler ch){
        for (ClientHandler clientThread : clientThreads) {
            if (clientThread == ch || clientThread == null) continue;
            clientThread.send(m);
        }
    }
}
