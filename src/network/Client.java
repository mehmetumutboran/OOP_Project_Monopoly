package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client implements Runnable{
    private Socket socket;
    private ClientSender clientSender;
    private ClientReceiver clientReceiver;
    private ClientFacade clientFacade;

    private static DataOutputStream dos;
    private static DataInputStream dis;

    public Client(String ip, int port, ClientFacade clientFacade) {
        try {
            this.clientFacade = clientFacade;
            socket = new Socket(ip, port);
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
//            clientReceiver = new ClientReceiver(dis);
//            clientSender = new ClientSender(dos);
//            clientReceiver.start();
//            clientSender.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String received;
        while (true){
            received = receive();

            clientFacade.sendReceivedMessage(received);

        }
    }


    public synchronized void send(String message) {
        try {
            System.out.println(message);
            dos.writeUTF(message);
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized String receive(){
        String received = "";
        try {
            received = dis.readUTF();
            System.out.println(received);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return received;
    }


}
