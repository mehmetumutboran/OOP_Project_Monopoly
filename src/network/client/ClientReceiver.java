package network.client;

import network.client.clientFacade.ClientFacade;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientReceiver extends Thread {
    private DataInputStream dis;
    private Socket socket;


    public ClientReceiver(DataInputStream dis, Socket socket) {
        this.dis = dis;
        this.socket = socket;
    }

    @Override
    public void run() {
        String received;
        while (true) {
            try {
                received = receive();
            } catch (IOException e) {
                System.out.println("Server is down!!!");
                break;
            }
            if (socket.isClosed()) break;

            ClientFacade.getInstance().sendReceivedMessage(received);


        }
    }

    public synchronized String receive() throws IOException {
        String received = dis.readUTF();
        System.out.println("Client class received Message:\n" + received + "\n\n");

        return received;
    }
}
