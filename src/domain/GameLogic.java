package domain;

import domain.board.Board;
import domain.board.Railroad;
import domain.controller.GameCommunicationHandler;
import domain.player.Player;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class GameLogic {
    private static GameLogic ourInstance;

    private static final int GO_COLLECT = 200;
    private static final int SECONDLAYERSQ = 24;
    private static final int FIRSTLAYERSQ = 40;
    private static final int ZEROTHLAYERSQ = 56;

    public static final char buyFlag = 'B';
    public static final char rollFlag = 'R';
    public static final char getRentFlag = 'T';
    public static final char decreaseMoneyFlag = 'D';
    public static final char increaseMoneyFlag = 'I';
    public static final char payRentFlag = 'P';
    public static final char drawCardFlag = 'C';
    public static final char payDayFlag = 'Y';
    public static final char goFlag = 'G';
    public static final char bonusFlag = 'O';
    public static final char jailFlag = 'J';
    public static final char finishTurnFlag = 'F';
    public static final char queueFlag = 'Q';
    public static final char closeFlag = 'E';
    public static final char moveFlag = 'M';
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
        if(checkDouble()) GameLogic.getInstance().getPlayers().peekFirst().incrementDoubleCounter();
        int [] lastLoc = GameLogic.getInstance().getPlayers().peekFirst().getToken().getLocation();
        int [] newLoc;
        int totalRoll;
        int layerSQNumber = 0;
        switch (lastLoc[0]){
            case 0:
                layerSQNumber = ZEROTHLAYERSQ;
                break;
            case 1:
                layerSQNumber = FIRSTLAYERSQ;
                break;
            case 2:
                layerSQNumber = SECONDLAYERSQ;
                break;
        }
        if(GameLogic.getInstance().getPlayers().peekFirst().getFaceValues()[2] <= 3) {
           totalRoll = GameLogic.getInstance().getPlayers().peekFirst().getFaceValues()[0]
                    + GameLogic.getInstance().getPlayers().peekFirst().getFaceValues()[1]
                    + GameLogic.getInstance().getPlayers().peekFirst().getFaceValues()[2];
        }else{
            totalRoll = GameLogic.getInstance().getPlayers().peekFirst().getFaceValues()[0]
                    + GameLogic.getInstance().getPlayers().peekFirst().getFaceValues()[1];
        }
        if(lastLoc[1] + totalRoll < layerSQNumber-1) {
            if (Board.getInstance().railRoadFind(lastLoc, totalRoll)[0]!= null && lastLoc[1] + totalRoll > Board.getInstance().railRoadFind(lastLoc, totalRoll)[0].getLocation()[1]) {
                if (totalRoll % 2 == 1) {
                    newLoc = normalMove(lastLoc, totalRoll, layerSQNumber);
                } else {
                    newLoc = upDownMove(lastLoc, totalRoll, layerSQNumber);
                }
            }else{
                newLoc = normalMove(lastLoc, totalRoll, layerSQNumber);
            }
        }else{
            if (Board.getInstance().railRoadFind(lastLoc, totalRoll)[0]!= null && lastLoc[1] + totalRoll-layerSQNumber > Board.getInstance().railRoadFind(lastLoc, totalRoll)[0].getLocation()[1]) {
                if (totalRoll % 2 == 1) {
                    newLoc = normalMove(lastLoc, totalRoll, layerSQNumber);
                } else {
                    newLoc = upDownMove(lastLoc, totalRoll, layerSQNumber);
                }
            }else{
                newLoc = normalMove(lastLoc, totalRoll, layerSQNumber);
            }
        }
        GameLogic.getInstance().getPlayers().peekFirst().getToken().setLocation(newLoc);
        System.out.println("In the Game Logic Move Method");
        GameCommunicationHandler.getInstance().sendAction(moveFlag);
    }

    private int [] upDownMove(int [] lastLoc, int roll , int layerSQNumber){
        int railroad;
        if(Board.getInstance().railRoadFind(lastLoc, roll)[0].getLocation()[1] - lastLoc[1] < 0) railroad = Board.getInstance().railRoadFind(lastLoc, roll)[0].getLocation()[1] - lastLoc[1] + layerSQNumber;
        else railroad = Board.getInstance().railRoadFind(lastLoc, roll)[0].getLocation()[1] - lastLoc[1];
        roll = roll - railroad;
        lastLoc = normalMove(lastLoc, railroad , layerSQNumber);
        return upDownMoveRec(lastLoc, roll, layerSQNumber);
    }

    private int [] upDownMoveRec(int [] lastLoc, int roll , int layerSQNumber) {
            String sqName = Board.getInstance().railRoadFind(lastLoc, roll)[0].getName();
            int [] tryLoc = new int [2];
            switch (sqName) {
                case "reading railroad":
                    if (lastLoc[0] == 0) {
                        lastLoc[0] = 1;
                        lastLoc[1] = 5;
                        tryLoc[0] = lastLoc[0];
                        tryLoc[1] = lastLoc[1] + 1;
                        if(Board.getInstance().railRoadFind(tryLoc, roll)[0] == null || Board.getInstance().railRoadFind(tryLoc, roll)[0].getLocation()[1] - lastLoc[1] > roll){
                            lastLoc[1] = lastLoc[1] + roll;
                            return lastLoc;
                        }
                        int railroad;
                        if(Board.getInstance().railRoadFind(tryLoc, roll)[0].getLocation()[1] - lastLoc[1] < 0) railroad = Board.getInstance().railRoadFind(tryLoc, roll)[0].getLocation()[1] - lastLoc[1] + layerSQNumber;
                        else railroad = Board.getInstance().railRoadFind(tryLoc, roll)[0].getLocation()[1] - lastLoc[1];
                        roll = roll - railroad;
                        lastLoc = normalMove(lastLoc, railroad , layerSQNumber);
                        return upDownMoveRec(lastLoc,roll,FIRSTLAYERSQ);
                    } else if (lastLoc[0] == 1) {
                        lastLoc[0] = 0;
                        lastLoc[1] = 7;
                        tryLoc[0] = lastLoc[0];
                        tryLoc[1] = lastLoc[1] + 1;
                        if(Board.getInstance().railRoadFind(tryLoc, roll)[0] == null || Board.getInstance().railRoadFind(tryLoc, roll)[0].getLocation()[1] - lastLoc[1] > roll){
                            lastLoc[1] = lastLoc[1] + roll;
                            return lastLoc;
                        }
                        int railroad;
                        if(Board.getInstance().railRoadFind(tryLoc, roll)[0].getLocation()[1] - lastLoc[1] < 0) railroad = Board.getInstance().railRoadFind(tryLoc, roll)[0].getLocation()[1] - lastLoc[1] + layerSQNumber;
                        else railroad = Board.getInstance().railRoadFind(tryLoc, roll)[0].getLocation()[1] - lastLoc[1];
                        roll = roll - railroad;
                        lastLoc = normalMove(lastLoc, railroad , layerSQNumber);
                        return upDownMoveRec(lastLoc,roll,ZEROTHLAYERSQ);
                    }
                    break;
                case "b.&o. railroad":
                   if (lastLoc[0] == 0) {
                       lastLoc[0] = 1;
                       lastLoc[1] = 25;
                       tryLoc[0] = lastLoc[0];
                       tryLoc[1] = lastLoc[1] + 1;
                       if(Board.getInstance().railRoadFind(tryLoc, roll)[0] == null || Board.getInstance().railRoadFind(tryLoc, roll)[0].getLocation()[1] - lastLoc[1] > roll){
                           lastLoc[1] = lastLoc[1] + roll;
                           return lastLoc;
                       }
                       int railroad;
                       if(Board.getInstance().railRoadFind(tryLoc, roll)[0].getLocation()[1] - lastLoc[1] < 0) railroad = Board.getInstance().railRoadFind(tryLoc, roll)[0].getLocation()[1] - lastLoc[1] + layerSQNumber;
                       else railroad = Board.getInstance().railRoadFind(tryLoc, roll)[0].getLocation()[1] - lastLoc[1];
                       roll = roll - railroad;
                       lastLoc = normalMove(lastLoc, railroad , layerSQNumber);
                       return upDownMoveRec(lastLoc,roll,FIRSTLAYERSQ);
                   } else if (lastLoc[0] == 1) {
                       lastLoc[0] = 0;
                       lastLoc[1] = 35;
                       tryLoc[0] = lastLoc[0];
                       tryLoc[1] = lastLoc[1] + 1;
                       if(Board.getInstance().railRoadFind(tryLoc, roll)[0] == null || Board.getInstance().railRoadFind(tryLoc, roll)[0].getLocation()[1] - lastLoc[1] > roll){
                           lastLoc[1] = lastLoc[1] + roll;
                           return lastLoc;
                       }
                       int railroad;
                       if(Board.getInstance().railRoadFind(tryLoc, roll)[0].getLocation()[1] - lastLoc[1] < 0) railroad = Board.getInstance().railRoadFind(tryLoc, roll)[0].getLocation()[1] - lastLoc[1] + layerSQNumber;
                       else railroad = Board.getInstance().railRoadFind(tryLoc, roll)[0].getLocation()[1] - lastLoc[1];
                       roll = roll - railroad;
                       lastLoc = normalMove(lastLoc, railroad , layerSQNumber);
                       return upDownMoveRec(lastLoc,roll,ZEROTHLAYERSQ);
                   }
                    break;
                case "pennsylvania railroad":
                    if (lastLoc[0] == 1) {
                        lastLoc[0] = 2;
                        lastLoc[1] = 9;
                        tryLoc[0] = lastLoc[0];
                        tryLoc[1] = lastLoc[1] + 1;
                        if(Board.getInstance().railRoadFind(tryLoc, roll)[0] == null || Board.getInstance().railRoadFind(tryLoc, roll)[0].getLocation()[1] - lastLoc[1] > roll){
                            lastLoc[1] = lastLoc[1] + roll;
                            return lastLoc;
                        }
                        int railroad;
                        if(Board.getInstance().railRoadFind(tryLoc, roll)[0].getLocation()[1] - lastLoc[1] < 0) railroad = Board.getInstance().railRoadFind(tryLoc, roll)[0].getLocation()[1] - lastLoc[1] + layerSQNumber;
                        else railroad = Board.getInstance().railRoadFind(tryLoc, roll)[0].getLocation()[1] - lastLoc[1];
                        roll = roll - railroad;
                        lastLoc = normalMove(lastLoc, railroad , layerSQNumber);
                        return upDownMoveRec(lastLoc,roll,SECONDLAYERSQ);
                    } else if (lastLoc[0] == 2) {
                        lastLoc[0] = 1;
                        lastLoc[1] = 15;
                        tryLoc[0] = lastLoc[0];
                        tryLoc[1] = lastLoc[1] + 1;
                        if(Board.getInstance().railRoadFind(tryLoc, roll)[0] == null || Board.getInstance().railRoadFind(tryLoc, roll)[0].getLocation()[1] - lastLoc[1] > roll){
                            lastLoc[1] = lastLoc[1] + roll;
                            return lastLoc;
                        }
                        int railroad;
                        if(Board.getInstance().railRoadFind(tryLoc, roll)[0].getLocation()[1] - lastLoc[1] < 0) railroad = Board.getInstance().railRoadFind(tryLoc, roll)[0].getLocation()[1] - lastLoc[1] + layerSQNumber;
                        else railroad = Board.getInstance().railRoadFind(tryLoc, roll)[0].getLocation()[1] - lastLoc[1];
                        roll = roll - railroad;
                        lastLoc = normalMove(lastLoc, railroad , layerSQNumber);
                        return upDownMoveRec(lastLoc,roll,FIRSTLAYERSQ);
                    }
                    break;
                case "shortlinerailroad":
                    if (lastLoc[0] == 1) {
                        lastLoc[0] = 2;
                        lastLoc[1] = 21;
                        tryLoc[0] = lastLoc[0];
                        tryLoc[1] = lastLoc[1] + 1;
                        if(Board.getInstance().railRoadFind(tryLoc, roll)[0] == null || Board.getInstance().railRoadFind(tryLoc, roll)[0].getLocation()[1] - lastLoc[1] > roll){
                            if(lastLoc[1] + roll == 24) lastLoc[1] = 0;
                            lastLoc[1] = lastLoc[1] + roll;
                            return lastLoc;
                        }
                        int railroad ;
                        if(Board.getInstance().railRoadFind(tryLoc, roll)[0].getLocation()[1] - lastLoc[1] < 0) railroad = Board.getInstance().railRoadFind(tryLoc, roll)[0].getLocation()[1] - lastLoc[1] + layerSQNumber;
                        else railroad = Board.getInstance().railRoadFind(tryLoc, roll)[0].getLocation()[1] - lastLoc[1];
                        roll = roll - railroad;
                        lastLoc = normalMove(lastLoc, railroad , layerSQNumber);
                        return upDownMoveRec(lastLoc,roll,SECONDLAYERSQ);
                    } else if (lastLoc[0] == 2) {
                        lastLoc[0] = 1;
                        lastLoc[1] = 35;
                        tryLoc[0] = lastLoc[0];
                        tryLoc[1] = lastLoc[1] + 1;
                        if(Board.getInstance().railRoadFind(tryLoc, roll)[0] == null || Board.getInstance().railRoadFind(tryLoc, roll)[0].getLocation()[1] - lastLoc[1] > roll){
                            if(lastLoc[1] + roll == 40) lastLoc[1] = 0;
                            lastLoc[1] = lastLoc[1] + roll;
                            return lastLoc;
                        }
                        int railroad;
                        if(Board.getInstance().railRoadFind(tryLoc, roll)[0].getLocation()[1] - lastLoc[1] < 0) railroad = Board.getInstance().railRoadFind(tryLoc, roll)[0].getLocation()[1] - lastLoc[1] + layerSQNumber;
                        else railroad = Board.getInstance().railRoadFind(tryLoc, roll)[0].getLocation()[1] - lastLoc[1];
                        roll = roll - railroad;
                        lastLoc = normalMove(lastLoc, railroad , layerSQNumber);
                        return upDownMoveRec(lastLoc,roll,FIRSTLAYERSQ);
                    }
                    break;
            }
            return lastLoc;
    }

    private int [] normalMove(int [] lastLoc, int roll, int layerSQNumber){
        int [] newLoc = new int[2];
        if(lastLoc[1] + roll > layerSQNumber-1){
            newLoc[0] = lastLoc[0];
            newLoc[1] = lastLoc[1] + roll - layerSQNumber;
            if(lastLoc[0] == 1) {
                GameLogic.getInstance().getPlayers().peekFirst().increaseMoney(GO_COLLECT);
                System.out.println("Player passed above Go Square");
                GameCommunicationHandler.getInstance().sendAction(increaseMoneyFlag);
            }
        }
        else{
            newLoc[0] = lastLoc[0];
            newLoc[1] = lastLoc[1] + roll;
        }
        return newLoc;
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
        if(GameLogic.getInstance().getPlayers().peekFirst().getDoubleCounter() == 3){
            GameLogic.getInstance().getPlayers().peekFirst().setInJail(true);
            GameLogic.getInstance().getPlayers().peekFirst().resetDoubleCounter();
            return true;
        }else return false;
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
