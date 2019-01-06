package domain.server;

import network.server.serverFacade.ServerFacade;
import static java.lang.System.currentTimeMillis;

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

    public void check(){
        double timeout = currentTimeMillis() + 5000;
        while (true) {
            if (checkReceived()) {
                setReceived();
                break;
            }
            if(timeout < currentTimeMillis()){
                break;
            }
        }
    }

    public void check(int index){
        double timeout = currentTimeMillis() + 5000;
        while (true) {
            if (checkReceived(index)) {
                setReceived(index);
                break;
            }
            if(timeout < currentTimeMillis()){
                break;
            }
        }
    }

    public void setReceived() {
        received = new boolean[12];
    }

    public boolean checkReceived(int index) {
        return received[index];
    }

    public void setReceived(int index) {
        received[index] = false;
    }
}
