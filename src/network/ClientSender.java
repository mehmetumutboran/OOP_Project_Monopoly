package network;

import java.io.*;

public class ClientSender extends Thread {
    DataOutputStream dos;

    public ClientSender(DataOutputStream dos) {
        this.dos = dos;
    }

    @Override
    public void run(){
        BufferedReader inputLine = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            try {
                dos.writeUTF(inputLine.readLine().trim());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
