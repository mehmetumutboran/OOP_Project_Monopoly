package network.client;

import jdk.jfr.SettingControl;

import java.io.DataOutputStream;
import java.io.IOException;

public class ConnectionChecker extends Thread {
    private DataOutputStream dos;
    private static volatile boolean connected;

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

                if (!connected){
                    throw new IOException();
                }
                connected = false;

            } catch (IOException | InterruptedException e) {
                System.out.println("\n=============================\n" +
                        "Connection Lost"
                        + "\n=============================\n");
                break;
            }
        }

    }


    public static synchronized void setConnected(){
        connected = true;
    }

}
