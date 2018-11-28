package domain.server.util;

import domain.server.listeners.PlayerListChangedListener;
import domain.server.player.Player;
import domain.server.player.RandomPlayer;
import network.client.Client;
import network.client.clientFacade.ClientFacade;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class GameInfo {
    private static GameInfo ourInstance;

    private ArrayList<PlayerListChangedListener> playerListChangedListeners;
    private ArrayList<String> selectedColors;

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
        playerListChangedListeners = new ArrayList<>();
        selectedColors = new ArrayList<>();
    }

    public Player getPlayer(String name){
        return playerList.stream().filter(player -> player.getName().equals(name)).findFirst().orElse(null);
    }

    public Player getMyself(){
        return getPlayer(ClientFacade.getInstance().getUsername());
    }

    public boolean isMyselfHost(){ return getPlayer(ClientFacade.getInstance().getUsername()).getReadiness().equals("Host");}

    public boolean hasPlayer(String name){
        return !playerList.stream().filter(player -> player.getName().equals(name)).findFirst().isEmpty();
    }

    public boolean isListEmpty() {
        return playerList.isEmpty();
    }

    public void addPlayer(String playerName) {
        playerList.add(new Player(playerName));
        publishPlayerListEvent();
        System.out.println(playerList);
    }

    public void addPlayer(String name, String color, String readiness) {
        playerList.add(new Player(name, color, readiness));
        //publishPlayerListEvent();
        System.out.println(playerList);
    }

    public void addPlayer(Player player) {
        playerList.add(player);
        publishPlayerListEvent();
        System.out.println(playerList);
    }

    public String getPlayersAsString() {
        StringBuilder list = new StringBuilder();
        for (Player player : playerList) {
            list.append(player).append("|");
        }
        return list.toString();
    }

    private void publishPlayerListEvent() {
        for (PlayerListChangedListener plc : playerListChangedListeners) {
            if (plc == null) continue;
            plc.onPlayerListChangedEvent(selectedColors);
        }
    }

    public boolean addPlayerListChangedListener(PlayerListChangedListener plc) {
        return playerListChangedListeners.add(plc);
    }

    public ArrayList<ArrayList<String>> getPlayerConnectAttributes() {
        ArrayList<ArrayList<String>> playerConnectAttributes = new ArrayList<>();
        for (Player player : playerList) {
            System.out.println("\n\n Get Player Connect Attr. " + player + "\n\n\n");
            ArrayList<String> temp = new ArrayList<>();
            temp.add(player.getName());
            temp.add(player.getToken().getColor());
            temp.add(player.getReadiness());
            playerConnectAttributes.add(temp);
        }
        return playerConnectAttributes;
    }
}
