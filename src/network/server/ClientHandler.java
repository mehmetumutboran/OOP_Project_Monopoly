package network.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket socket;
    private volatile DataInputStream dis;
    private volatile DataOutputStream dos;
    private boolean isSent;

    public ClientHandler(Socket clientSocket) {

        this.socket = clientSocket;
        try {
            this.dis = new DataInputStream(socket.getInputStream());
            this.dos = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        try {

            String line = dis.readUTF();
            Server.setClientInfo(line);

            while (true) {
                line = dis.readUTF();
                Server.sendAll(line);

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
        isSent = true;
    }

    public synchronized void terminate() {
        try {
            send("You are kicked!");

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(isSent) {
                    dis.close();
                    dos.close();
                    socket.close();
                    isSent = false;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
