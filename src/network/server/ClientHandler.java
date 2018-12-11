package network.server;

import domain.server.ReceivedChecker;
import network.server.serverFacade.ServerFacade;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;

public class ClientHandler implements Runnable {
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
    private int index;
    private ClientProcessHandler clientProcessHandler;


    public ClientHandler(Socket clientSocket, int index) {
        this.socket = clientSocket;
        this.index = index;
        this.clientProcessHandler = new ClientProcessHandler(index);
    }

    @Override
    public void run() {
        try {
            this.dis = new DataInputStream(socket.getInputStream());
            this.dos = new DataOutputStream(socket.getOutputStream());

            while (true) {
                String line = dis.readUTF();
                System.out.println("\n\nReceived message Server:" + line);
                if (line.charAt(0) == 'z') { // Received flag
                    synchronized (this) {
                        ReceivedChecker.getInstance().received[index] = true;
                    }
//                    System.out.println(Arrays.toString(ReceivedChecker.getInstance().received));

                    continue;
                }


                //ServerFacade.getInstance().interpretRequest(dis, line, index);
                clientProcessHandler.setLine(line);
                new Thread(clientProcessHandler, "ClientProcessHandler").start();

//                synchronized (this){
//                    this.wait();
//                }
            }

        } catch (IOException e) {
            //TODO Handle Player exit
            System.out.println("\n\n Player exited\n\n");
            ServerFacade.getInstance().removeClient(this);
        }

    }




    public synchronized void send(String m) throws IOException {
        dos.writeUTF(m);
        dos.flush();
        ReceivedChecker.getInstance().received[index] = false;
    }

    public synchronized void terminate() {
        try {
            dis.close();
            dos.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
