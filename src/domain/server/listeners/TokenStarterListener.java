package domain.server.listeners;

import java.util.ArrayList;

public interface TokenStarterListener {
    void onTokenStarterEvent(ArrayList<String> pList);
}
