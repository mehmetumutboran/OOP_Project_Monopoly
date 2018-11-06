package network;

import java.io.DataInputStream;

public class ClientReceiver extends Thread {
    private DataInputStream dis;
    private ClientFacade clientFacade;

    public ClientReceiver(DataInputStream dis, ClientFacade clientFacade) {
        this.dis = dis;
        this.clientFacade = clientFacade;
    }

    @Override
    public void run() {
        String received;
        while (true) {
            received = Client.receive();

//            System.out.println("Client receiver :" + received);
            clientFacade.sendReceivedMessage(received);

        }
    }
}
