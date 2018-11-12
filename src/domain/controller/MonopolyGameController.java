package domain.controller;

import domain.GameLogic;
import domain.MessageInterpreter;
import domain.UIUpdater;
import domain.die.DiceCup;
import domain.listeners.CloseButtonListener;
import domain.listeners.GameStartedListener;
import domain.listeners.PlayerListChangedListener;
import domain.player.Player;

import java.util.*;
import java.util.stream.Collectors;

public class MonopolyGameController {
    private ArrayList<Player> playerList;
    private ArrayList<Player> playerSortList;
    private Deque<Player> playerQueue;

    private static MonopolyGameController monopolyGameController;

    private ArrayList<PlayerListChangedListener> playerListChangedListeners;
    private ArrayList<GameStartedListener> gameStartedListeners;
    private ArrayList<CloseButtonListener> closeButtonListeners;

    private MonopolyGameController() {
        playerList = new ArrayList<>();
        playerSortList = new ArrayList<>();
        playerQueue = new LinkedList<>();
        playerListChangedListeners = new ArrayList<>();
        gameStartedListeners = new ArrayList<>();
        closeButtonListeners = new ArrayList<>();
    }

    public boolean addPlayer(Player player) {
//        return playerList.add(player);
        playerList.add(player);
        System.out.println("\n\nAdded Player" +  player + "\n\n");
        System.out.println(playerList + "\n\n");
        publishPlayerListEvent();
        return true;
    }

    private void publishCloseClickedEvent() {
        for (CloseButtonListener cbl : closeButtonListeners) {
            if (cbl == null) continue;
            cbl.onCloseClickedEvent();
        }
    }

    private void publishPlayerListEvent() {
        for (PlayerListChangedListener plc : playerListChangedListeners) {
            if (plc == null) continue;
            plc.onPlayerListChangedEvent();
        }
    }

    private void publishGameStartedEvent(){
        for (GameStartedListener gls : gameStartedListeners) {
            if (gls == null) continue;
            gls.onGameStartedEvent();
        }
    }

    public boolean addPlayerListChangedListener(PlayerListChangedListener plc) {
        return playerListChangedListeners.add(plc);
    }

    public boolean addCloseButtonListener(CloseButtonListener cbl) {
        return closeButtonListeners.add(cbl);
    }

    public boolean addGameStartedListener(GameStartedListener gsl){
        return gameStartedListeners.add(gsl);
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
            System.out.println("\n\n Get Player Connect Attr. " + player + "\n\n\n");
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
//        if (playerList.size() == 1) return false; TODO
        System.out.println("Hello");
        if((playerList.get(0).getReadiness().equals("Bot")) || (playerList.get(0).getReadiness().equals("Ready") &&
                !playerList.get(0).getReadiness().equals("Host"))){
            startGame();
            return true;
        }
        for (int i = 1; i < playerList.size(); i++) {
            if (playerList.get(i).getReadiness().equals("Not Ready")) return false;
        }
        startGame();
        return true;
    }

    private void startGame(){
        playerList.get(0).setStarted(true);
        ConnectGameHandler.getInstance().sendChange(playerList.get(0));
        publishGameStartedEvent();
        GameCommunicationHandler.getInstance();
        UIUpdater.getInstance();
        GameLogic.getInstance().setPlayerList(playerList);
        if(playerList.get(0).getReadiness().equals("Host")) {
            initGame();
        }

    }

    private void initGame(){ // For now in this method players roll dice with initial roll strategy and they put to the queue corresponding to their total face values.
        for (Player p : playerList) {
            DiceCup.getInstance().rollDice("Init");
            p.setFaceValues(DiceCup.getInstance().getFaceValues());
            System.out.println(Arrays.toString(DiceCup.getInstance().getFaceValues()));
        }
        playerSortList.addAll(playerList);
        Collections.sort(playerSortList);
        playerQueue.addAll(playerSortList);
        System.out.println("/////Player Queue//////");
        for (Player p:playerQueue) {
            System.out.println(p);
        }
        GameLogic.getInstance().setPlayers(playerQueue);
        GameCommunicationHandler.getInstance().sendQueue();
    }

    public static void main(String[] args) {
        MonopolyGameController.getInstance().addPlayer(new Player("Mostafazadeh"));
        MonopolyGameController.getInstance().addPlayer(new Player("Benjamin"));
        MonopolyGameController.getInstance().addPlayer(new Player("asddsa"));
        System.out.println(MonopolyGameController.getInstance().getPlayerListName());
        DiceCup.getInstance().rollDice("RollThree");
        System.out.println("Roll Three: " + Arrays.toString(DiceCup.getInstance().getFaceValues()));
        System.out.println("Total Face Value: " + DiceCup.getInstance().getTotalFaceValue());
        DiceCup.getInstance().rollDice("RollNormal");
        System.out.println("Roll Normal: " + Arrays.toString(DiceCup.getInstance().getFaceValues()));
        System.out.println("Total Face Value: " + DiceCup.getInstance().getTotalFaceValue());
        DiceCup.getInstance().rollDice("Jail");
        System.out.println("Jail: " + Arrays.toString(DiceCup.getInstance().getFaceValues()));
        System.out.println("Total Face Value: " + DiceCup.getInstance().getTotalFaceValue());
    }


    public void informClosed() {
        if(MonopolyGameController.getInstance().getPlayerList().size() > 1)ConnectGameHandler.getInstance().sendChange(playerList.get(0),'E');
        publishCloseClickedEvent();
    }

}
