package domain.util;

import domain.server.Savable;
import domain.server.listeners.PlayerListChangedListener;
import domain.server.player.Player;
import network.client.clientFacade.ClientFacade;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class GameInfo implements Savable {
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

    public Deque<String> getPlayerQueue() {
        return playerQueue;
    }

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public ArrayList<String> getPlayerListName() {
        return (ArrayList<String>) playerList.stream().map(Player::getName).collect(Collectors.toList());
    }

    public ArrayList<String> getPlayerListColor() {
        return (ArrayList<String>) playerList.stream().map(Player::tokenColor).collect(Collectors.toList());
    }

    public void setPlayerQueue(Deque<String> playerQueue) {
        this.playerQueue = playerQueue;
    }

    public void setPlayerList(ArrayList<Player> playerList) {
        this.playerList = playerList;
    }

    public Player getPlayer(String name){
        return playerList.stream().filter(player -> player.getName().equals(name)).findFirst().orElse(null);
    }

    public Player getMyself(){
        return getPlayer(ClientFacade.getInstance().getUsername());
    }

    public boolean isMyselfHost(){ return getPlayer(ClientFacade.getInstance().getUsername()).getReadiness().equals("Host");}

    public boolean hasPlayer(String name){
        return !playerList.stream().filter(player -> player.getName().equals(name)).collect(Collectors.toCollection(ArrayList::new)).isEmpty();
    }

    public boolean isListEmpty() {
        return playerList.isEmpty();
    }

    public void addPlayer(String playerName) {
        Player player = new Player(playerName);
        if(playerList.isEmpty()){
            player.setReadiness("Host");
            selectedColors.add("White"); // TODO initial White check isn't correct
        }
        playerList.add(player);
        publishPlayerListEvent();
        System.out.println(playerList);
    }

    public void addPlayer(String name, String color, String readiness) {
        playerList.add(new Player(name, color, readiness));
        //publishPlayerListEvent();
        selectedColors.add(color);
        System.out.println(playerList);
    }

    public void addPlayer(Player player) {
        playerList.add(player);
        publishPlayerListEvent();
        selectedColors.add(player.tokenColor());
        System.out.println(playerList);
    }

    public boolean hasColor(String color){
        System.out.println(color);
        System.out.println(selectedColors);
        for (String clr : selectedColors){
            if(clr.equals(color))
            {
                System.out.println(clr);
                return true;
            }
        }
        System.out.println("Not found!");
        return false;
    }

    public void setPlayerColor(String name, String color){
        selectedColors.remove(getPlayer(name).tokenColor());
        getPlayer(name).getToken().setColor(color);
        selectedColors.add(color);
        publishPlayerListEvent();
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

    public void setReadiness(String username) {
        getPlayer(username).setReadiness();
        publishPlayerListEvent();
    }

    public int checkReadiness() {
        int count = 0;
        for (Player player : playerList){
            if(player.getReadiness().equals("Not Ready")) count++;
        }
        return count;
    }

    @Override
    public String generateSaveInfo() {
        return MessageConverter.convertQueueToString(playerQueue);
    }
}
