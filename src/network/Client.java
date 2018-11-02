package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client{
    private Socket socket;

    private static DataOutputStream dos;
    private static DataInputStream dis;

    public Client(String ip, int port) {
        try {

            socket = new Socket(ip, port);
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            (new ClientReceiver(dis)).start();
            (new ClientSender(dos)).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
