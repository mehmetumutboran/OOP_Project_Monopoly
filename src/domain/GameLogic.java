package domain;

import domain.controller.GameCommunicationHandler;
import domain.player.Player;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
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
    public static final char queueFlag = 'Q';
    public static final char closeFlag = 'E';
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
        players = new LinkedList<>();
        playerList = new ArrayList<>();

    }

    public boolean buy() {
        /* true ??????? */
        System.out.println("in  logic buy");

        if (players.peekFirst().buy()) {
            System.out.println("player buy completed, is sending action");
            GameCommunicationHandler.getInstance().sendAction(buyFlag);
            return true;}
        else return false;
    }

    public boolean rent() {
        /* true ??????? */
        System.out.println("in  logic buy");

        if (players.peekFirst().rent()) {
            System.out.println("player rent completed, is sending action");
            GameCommunicationHandler.getInstance().sendAction(payRentFlag);
            return true;}
        else return false;
    }


    public void roll() {
        players.peekFirst().rollDice();
        if(checkThirdDouble()){

        }else if(checkJail()){

        }else if (checkBus()){

        }else{
            move();
        }


        checkMrMonopoly();
        System.out.println("In the Game Logic Roll Method");
        GameCommunicationHandler.getInstance().sendAction(rollFlag);
    }

    private void move() {
        checkDouble();
    }

    private boolean checkDouble() {
        return (GameLogic.getInstance().getPlayers().peekFirst().getFaceValues()[0] ==
                GameLogic.getInstance().getPlayers().peekFirst().getFaceValues()[1]);
    }


    private boolean checkJail() {
        return false;
    }

    private boolean checkBus() {
        return false;
    }

    private boolean checkMrMonopoly() {
        return false;
    }

    private boolean checkTriple() {
        return false;
    }

    private boolean checkThirdDouble() {
        return false;
    }

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayers(Deque<Player> playerQueue) {
        this.players = playerQueue;
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

    public void switchTurn() {
        players.addLast(players.removeFirst());


    }

    public void finishTurn() {
        GameCommunicationHandler.getInstance().sendAction(finishTurnFlag);
    }
}
