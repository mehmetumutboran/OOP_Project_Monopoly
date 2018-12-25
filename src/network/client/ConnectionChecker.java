package network.client;

import java.io.DataOutputStream;
import java.io.IOException;

public class ConnectionChecker extends Thread {
    private DataOutputStream dos;

    public ConnectionChecker(DataOutputStream dos) {
        this.dos = dos;
    }

    @Override
    public void run() {

        while (true) {
            try {
                dos.writeUTF("isConnected");
                dos.flush();

                Thread.sleep(5000);

            } catch (IOException | InterruptedException e) {
                System.out.println("\n=============================\n" +
                        "Connection Lost"
                        + "\n=============================\n");
            }
        }

    }
}
