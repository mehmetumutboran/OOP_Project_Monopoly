package domain.server;

import network.server.serverFacade.ServerFacade;

public class ReceivedChecker {
    private static ReceivedChecker ourInstance;

    public volatile boolean[] received;

    public static ReceivedChecker getInstance() {
        if (ourInstance == null) {
            ourInstance = new ReceivedChecker();
        }
        return ourInstance;
    }

    private ReceivedChecker() {
        received = new boolean[12];
        for (int i = 0; i < received.length; i++) {
            received[i] = false;
        }
    }

    public boolean checkReceived() {
        int x = ServerFacade.getInstance().getTotalNumPlayers();
        for (int i = 0; i < x; i++) {
            if (!received[i]) return false;
        }
        return true;
    }

    public void setReceived() {
        received = new boolean[12];
    }
}
