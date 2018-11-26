package domain.server.listeners;

import java.util.ArrayList;

public interface PlayerListChangedListener {
    void onPlayerListChangedEvent(ArrayList<String> selectedColors);
}
