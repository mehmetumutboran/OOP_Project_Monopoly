package domain;

import domain.die.DiceCup;
import domain.player.Player;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class MonopolyGameController {
    private ArrayList<Player> playerList;
    private Deque<Player> playerQueue;
    private DiceCup cup;

    private static MonopolyGameController monopolyGameController;

    private ArrayList<PlayerListChangedListener> playerListChangedListeners;

    private MonopolyGameController() {
        playerList = new ArrayList<>();
        playerQueue = new LinkedList<>();
        cup = new DiceCup();
        playerListChangedListeners = new ArrayList<>();
    }

    public boolean addPlayer(Player player) {
//        return playerList.add(player);
        playerList.add(player);
        System.out.println(playerList);
        publishPlayerListEvent();
        return true;
    }

    private void publishPlayerListEvent() {
        for (PlayerListChangedListener plc : playerListChangedListeners) {
            if (plc == null) continue;
            plc.onPlayerListChangedEvent();
        }
    }

    public boolean addPlayerListChangedListener(PlayerListChangedListener plc) {
        return playerListChangedListeners.add(plc);
    }

    public static MonopolyGameController getInstance() {
        if (monopolyGameController == null) {
            monopolyGameController = new MonopolyGameController();
        }

        return monopolyGameController;
    }

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

//    public ArrayList<String> getPlayerListAsJSON() {
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
//        String playerJson = null;
//        ArrayList<String> playerJSONs = new ArrayList<>();
//        try {
//            for (int i = 0; i < playerList.size(); i++) {
//                playerJson = mapper.writeValueAsString(playerList.get(i));
//                playerJSONs.add(playerJson);
//            }
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        return playerJSONs;
//    }

    /**
     * Uses stream to take each player's username and returns them as a list
     *
     * @return {@link ArrayList} of {@link Player}'s username
     */
    public ArrayList<String> getPlayerListName() {
        return (ArrayList<String>) playerList.stream().map(Player::getName).collect(Collectors.toList());
    }

    public ArrayList<ArrayList<String>> getPlayerConnectAttributes() {
        ArrayList<ArrayList<String>> playerConnectAttributes = new ArrayList<>();
        for (Player player : playerList) {
            ArrayList<String> temp = new ArrayList<>();
            temp.add(player.getName());
            temp.add(player.getToken().getColor());
            temp.add(player.getReadiness());
            playerConnectAttributes.add(temp);
        }
        //System.out.println("Player list is" + playerList.isEmpty());
        return playerConnectAttributes;
    }

    public void changePlayerColor(int index, String color) {
        playerList.get(index).getToken().setColor(color);
        System.out.println("Player's color " + playerList.get(0).getToken().getColor());
        if (playerList.size() > 1) {
            ConnectGameHandler.getInstance().sendChange(playerList.get(index));
        }
        publishPlayerListEvent();
    }

    public void changePlayerReadiness(int index) {
        playerList.get(index).setReadiness();
        if (playerList.size() > 1) {
            ConnectGameHandler.getInstance().sendChange(playerList.get(index));
        }
        publishPlayerListEvent();
    }

    public boolean checkReadiness() {
        if (playerList.size() == 1) return false;
        for (int i = 1; i < playerList.size(); i++) {
            if (playerList.get(i).getReadiness().equals("Not Ready")) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        MonopolyGameController.getInstance().addPlayer(new Player("Mostafazadeh"));
        MonopolyGameController.getInstance().addPlayer(new Player("Benjamin"));
        MonopolyGameController.getInstance().addPlayer(new Player("asddsa"));
        System.out.println(MonopolyGameController.getInstance().getPlayerListName());
    }


}
