package network.server;

import network.server.serverFacade.ServerFacade;

public class ClientProcessHandler implements Runnable {
    private String line;
    private int index;

    public ClientProcessHandler(int index) {
        this.index = index;
    }

    @Override
    public void run() {
//        while (true) {
//            try {
//                synchronized (this) {
//                    this.wait();
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }


        ServerFacade.getInstance().interpretRequest(line, index);

//            synchronized (clientHandler) {
//                clientHandler.notify();
//            }
//        }
    }

    public synchronized String getLine() {
        return line;
    }

    public void setLine(String line) {
        System.out.println("\nLine set: " + line + "\n");
        this.line = line;
//        this.notify();

    }
}
