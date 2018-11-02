package network;

import java.io.IOException;
import java.net.Socket;

public class Client {
    Socket socket;

    public Client(String ip, int port) {
        try {
            socket = new Socket(ip, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
