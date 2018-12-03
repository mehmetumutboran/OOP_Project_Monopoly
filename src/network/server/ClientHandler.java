package network.server;

import network.server.serverFacade.ServerFacade;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Deque;
import java.util.LinkedList;

public class ClientHandler implements Runnable {
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
    private int index;
    private Deque<String> messageQueue;
    private boolean received;


    public ClientHandler(Socket clientSocket, int index) {
        this.socket = clientSocket;
        this.index = index;
        this.messageQueue = new LinkedList<>();
        this.received = true;
    }

    @Override
    public void run() {
        try {
            this.dis = new DataInputStream(socket.getInputStream());
            this.dos = new DataOutputStream(socket.getOutputStream());
            String line;

            while (true) {
                line = dis.readUTF();
                System.out.println("\n\nReceived message Server:" + line);
                if (line.charAt(0) == 'z') { // Received flag
                    received = true;
                    continue;
                }
                ServerFacade.getInstance().interpretRequest(line, index);

            }

        } catch (IOException e) {
            //TODO Handle Player exit
            System.out.println("\n\n Player exited\n\n");
            Server.removeClient(this);
        }

    }


    public synchronized void send(String m) throws IOException {
        messageQueue.addLast(m);
        while (true) {
            if (received) break;
        }
        received = false;
        dos.writeUTF(messageQueue.removeFirst());
        dos.flush();
    }

    public synchronized void terminate() {
        try {
            dis.close();
            dos.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
