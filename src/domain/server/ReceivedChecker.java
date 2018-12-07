package domain.server;

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
}
