package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket socket;
    private final ClientHandler[] clientThreads;
    private DataInputStream dis;
    private DataOutputStream dos;

    public ClientHandler(Socket clientSocket, ClientHandler[] clientThreads) {
        this.socket = clientSocket;
        this.clientThreads = clientThreads;
    }

    @Override
    public void run() {
        try {
            this.dis = new DataInputStream(socket.getInputStream());
            this.dos = new DataOutputStream(socket.getOutputStream());

            synchronized (this) {
                for (int i = 0; i < clientThreads.length; i++) {
                    if (clientThreads[i] != null && clientThreads[i] != this) {
                        clientThreads[i].dos.writeUTF("A new user entered: " + socket.toString());
                    }
                }

            }

            while (true) {
                String line = dis.readUTF();
                if (line.startsWith("/quit")) {
                    break;
                }
                Server.sendAllExceptOne(line, this);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public synchronized void send(String m) {
        try {
            dos.writeUTF(m);
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
