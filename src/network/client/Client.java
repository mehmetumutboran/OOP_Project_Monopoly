package network.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    private Socket socket;
    private ClientReceiver clientReceiver;
    private String username;

    private DataOutputStream dos;
    private DataInputStream dis;

    public Client(String username, String ip, int port) throws IOException {
        this.username = username;
        socket = new Socket(ip, port);
        dis = new DataInputStream(socket.getInputStream());
        dos = new DataOutputStream(socket.getOutputStream());
        clientReceiver = new ClientReceiver(dis, socket);
        clientReceiver.start();

    }

    public Socket getSocket() {
        return socket;
    }

    public DataOutputStream getDos() {
        return dos;
    }

    public DataInputStream getDis() {
        return dis;
    }

    public String getUsername() {
        return username;
    }

    public synchronized void send(String message) {
        try {

            System.out.println("In the Client class sending message:\n" + message + "\n\n");
            dos.writeUTF(message);
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
