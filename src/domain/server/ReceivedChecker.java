package domain.server;

import network.server.serverFacade.ServerFacade;

public class ReceivedChecker {
    private static ReceivedChecker ourInstance;

    public volatile boolean[] recevied;

    public static ReceivedChecker getInstance() {
        if (ourInstance == null) {
            ourInstance = new ReceivedChecker();
        }
        return ourInstance;
    }

    private ReceivedChecker() {
        recevied = new boolean[12];
        for (int i = 0; i < recevied.length; i++) {
            recevied[i] = false;
        }
    }

    public boolean checkReceived(){
        for (int i = 0; i < ServerFacade.getInstance().getTotalNumPlayers(); i++) {
            if(!recevied[i]) return false;
        }
        return true;
    }

    public void setReceived() {
        recevied = new boolean[12];
        return;
    }
}
