package domain.server.util;

import domain.server.player.Player;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class GameInfo {
    private static GameInfo ourInstance;

    public static GameInfo getInstance() {
        if(ourInstance == null)
            ourInstance = new GameInfo();
        return ourInstance;
    }

    private volatile Deque<String> playerQueue;
    private volatile ArrayList<Player> playerList;


    private GameInfo() {
        playerList = new ArrayList<>();
        playerQueue = new LinkedList<>();
    }

    public Player getPlayer(String name){
        return playerList.stream().filter(player -> player.getName().equals(name)).findFirst().orElse(null);
    }

    public void addPlayer(Player player) {
        playerList.add(player);
    }
}
