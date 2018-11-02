package network;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    private ServerSocket ss;

    public Server(int port) {
        try {
            ss = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
