package domain.listeners;

import java.util.ArrayList;

public interface GameStartedListener {
    void onGameStartedEvent(ArrayList<String> playerColorList);
}
