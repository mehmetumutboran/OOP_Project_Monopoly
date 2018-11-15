package network.client;

import network.client.clientFacade.ClientFacade;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    private Socket socket;
    private ClientReceiver clientReceiver;
    private ClientFacade clientFacade;

    private static DataOutputStream dos;
    private static DataInputStream dis;

    public Client(String ip, int port, ClientFacade clientFacade) throws IOException {
            this.clientFacade = clientFacade;
            socket = new Socket(ip, port);
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            clientReceiver = new ClientReceiver(dis, clientFacade);
            clientReceiver.start();
    }


    public synchronized void send(String message) {
        try {

            System.out.println("In the Client class sending message:\n" + message + "\n\n");
            dos.writeUTF(message);
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized String receive() {
        String received = "";
        try {
            received = dis.readUTF();
            System.out.println("Client class received Message:\n" + received + "\n\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return received;
    }


}
