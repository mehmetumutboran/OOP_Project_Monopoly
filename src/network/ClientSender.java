package network;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClientSender extends Thread {
    DataOutputStream dos;

    public ClientSender(DataOutputStream dos) {
        this.dos = dos;
    }

    @Override
    public void run() {

        while (true) {
            try {
                dos.writeUTF(""); //TODO
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
