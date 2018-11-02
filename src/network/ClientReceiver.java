package network;

import java.io.DataInputStream;
import java.io.IOException;

public class ClientReceiver extends Thread {
    private DataInputStream dis;

    public ClientReceiver(DataInputStream dis) {
        this.dis = dis;
    }

    @Override
    public void run() {
        String received;
        while (true) {
            try {
                received = dis.readUTF().trim();
                System.out.println(received);
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
