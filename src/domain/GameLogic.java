package domain;

import domain.controller.GameCommunicationHandler;
import domain.player.Player;

import java.util.ArrayList;
import java.util.Deque;
import java.util.stream.Collectors;

public class GameLogic {
    private static GameLogic ourInstance;

    public static final char buyFlag = 'B';
    public static final char rollFlag = 'R';
    public static final char getRentFlag = 'I';
    public static final char decreaseMoneyFlag = 'D';
    public static final char payRentFlag = 'P';
    public static final char drawCardFlag = 'C';
    public static final char payDayFlag = 'Y';
    public static final char goFlag = 'G';
    public static final char bonusFlag = 'O';
    public static final char jailFlag = 'J';
    public static final char finishTurnFlag = 'F';
    //TODO Add more

    private Deque<Player> players;
    private ArrayList<Player> playerList;

    public static GameLogic getInstance() {
        if (ourInstance == null) {
            ourInstance = new GameLogic();
        }
        return ourInstance;
    }

    private GameLogic() {

    }

    public void roll() {
        GameCommunicationHandler.getInstance().sendAction(rollFlag);
    }

    private void checkJail() {
    }

    private void checkBus() {
    }

    private void checkMrMonopoly() {
    }

    private void checkTriple() {
    }

    private void checkThirdDouble() {
    }

    public void setPlayers(Deque<Player> playerQueue) {

    }

    public Deque<Player> getPlayers() {
        return players;
    }

    public Player getPlayer(String name) {
        return playerList.stream().filter(x -> x.getName().equals(name)).collect(Collectors.toList()).get(0);
    }

    public void setPlayerList(ArrayList<Player> playerList) {
        this.playerList = playerList;
    }
}
