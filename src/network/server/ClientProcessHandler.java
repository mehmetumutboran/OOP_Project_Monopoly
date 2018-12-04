package network.server;

import network.server.serverFacade.ServerFacade;

public class ClientProcessHandler implements Runnable {
    private String line;
    private int index;

    public ClientProcessHandler(String line, int index) {
        this.line = line;
        this.index = index;
    }

    @Override
    public void run() {
        ServerFacade.getInstance().interpretRequest(line, index);
    }
}
