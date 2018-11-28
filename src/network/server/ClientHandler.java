package network.server;

import network.server.serverFacade.ServerFacade;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
    private int index;

    public ClientHandler(Socket clientSocket, int index) {
        this.socket = clientSocket;
        this.index = index;
    }

    @Override
    public void run() {
        try {
            this.dis = new DataInputStream(socket.getInputStream());
            this.dos = new DataOutputStream(socket.getOutputStream());
            String line;

            while (true) {
                line = dis.readUTF();
                ServerFacade.getInstance().interpretRequest(line, index);
//                Server.sendAll(line);

            }


        } catch (IOException e) {
            //TODO Handle Player exit
            System.out.println("\n\n Player exited\n\n");
            Server.removeClient(this);
        }

    }


    public synchronized void send(String m) throws IOException {
        dos.writeUTF(m);
        dos.flush();
    }

    public void terminate() {
        try {
            send("You are kicked!");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                dis.close();
                dos.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
