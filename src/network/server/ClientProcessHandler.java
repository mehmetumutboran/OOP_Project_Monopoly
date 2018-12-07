package network.server;

import network.server.serverFacade.ServerFacade;

public class ClientProcessHandler implements Runnable {
    private String line;
    private int index;
    private final ClientHandler clientHandler;

    public ClientProcessHandler(ClientHandler clientHandler, int index) {
        this.index = index;
        this.clientHandler = clientHandler;
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (this) {
                    this.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


//            ServerFacade.getInstance().interpretRequest(index, line);

            synchronized (clientHandler) {
                clientHandler.notify();
            }
        }
    }

    public synchronized String getLine() {
        return line;
    }

    public synchronized void setLine(String line) {
        this.line = line;
        this.notify();
    }
}
