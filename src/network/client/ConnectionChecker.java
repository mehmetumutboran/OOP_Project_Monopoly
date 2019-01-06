package network.client;

import network.client.clientFacade.ClientFacade;

import java.io.DataOutputStream;
import java.io.IOException;

public class ConnectionChecker extends Thread {
    private DataOutputStream dos;
    private volatile boolean connected;

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

                if (!connected) {
                    throw new IOException();
                }
                connected = false;

            } catch (IOException | InterruptedException e) {
                System.out.println("\n=============================\n" +
                        "Connection Lost"
                        + "\n=============================\n");
                ClientFacade.getInstance().reconnect();
                break;
            }
        }

    }


    public synchronized void setConnected() {
        connected = true;
    }

    public void setDos(DataOutputStream dos) {
        this.dos = dos;
    }

}
