package domain;

import domain.board.Board;
import domain.board.DeedSquare;
import domain.board.Property;
import domain.board.Railroad;
import domain.board.Square;
import domain.building.Building;
import domain.building.Hotel;
import domain.building.House;
import domain.building.Skyscraper;
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
    public static final char upgradeFlag = 'U';
    public static final char downgradeFlag = 'Z';
    public static final char moveFlag = 'M';
    //TODO Add more

    private volatile Deque<Player> players;
    private volatile ArrayList<Player> playerList;

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
                case "short line railroad":
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

    public synchronized void switchTurn() {
        players.addLast(players.removeFirst());
        // Bots will play on only host's program
        if(!playerList.get(0).getReadiness().equals("Host")) return;
        // Check for bot's turn
        for(Player player : playerList) {
            if(player.getReadiness().equals("Bot") && ((RandomPlayer) player).checkTurn()){
                break;
            }
        }
    }

    public void finishTurn() {
        GameCommunicationHandler.getInstance().sendAction(finishTurnFlag);
    }

    public void upgrade(Square square) {
        Player currentPlayer = players.peekFirst();
        if (square.getClass().getName().equals("RailRoad")) {
            if (currentPlayer.getBalance() >= 100) {
                ((Railroad) square).setHasDepot(true);
                currentPlayer.setBalance(currentPlayer.getBalance() - 100); //change decrease money method
                ((Railroad) square).updateRent();
                System.out.println(currentPlayer.getName() + " upgraded Railroad ");
            } else
                System.out.println(currentPlayer.getName() + " does not have enough money to upgrade " + square.getName());
        } else if (square.getClass().getName().equals("Property")) {
            if(currentPlayer.checkMajority(((Property)square))){
                if(((Property)square).isUpgradable((Property)square)){
                    if(((Property) square).getBuildingList().get(0).getName().equals("Hotel")){
                        if(currentPlayer.getBalance() >= ((Property)square).getSkyScrapperCost()) {
                            ((Property) square).getBuildingList().remove(0);
                            ((Property) square).getBuildingList().add(new Skyscraper(((Property) square).getSkyScrapperCost()));
                            currentPlayer.setBalance((currentPlayer).getBalance() - ((Property) square).getSkyScrapperCost());
                        }
                    }else if(((Property) square).getBuildingList().size()==4){
                        if(currentPlayer.getBalance() >= ((Property)square).getSkyScrapperCost()) {
                            ((Property) square).getBuildingList().clear();
                            ((Property) square).getBuildingList().add(new Hotel(((Property) square).getHotelCost()));
                            currentPlayer.setBalance((currentPlayer).getBalance() - ((Property) square).getHotelCost());
                        }
                    }else {
                        if(currentPlayer.getBalance() >= ((Property)square).getSkyScrapperCost()) {
                            ((Property) square).getBuildingList().add(new House(((Property) square).getHouseCost()));
                            currentPlayer.setBalance((currentPlayer).getBalance() - ((Property) square).getHouseCost());
                        }
                    }
                    ((Property)square).updateRent();
                    System.out.println(currentPlayer.getName() + " upgraded " + square.getName() + " to " +
                            ((Property)square).getBuildingList().get(0));
                    ((Property)square).setUpgraded(true);
                }

            }else System.out.println("Current square is not a deed square");
        }
    }

    public void downgrade(Square square){
        Player currentPlayer = players.peekFirst();
        if (square.getClass().getName().equals("Railroad")){
            if(((Railroad)square).isHasDepot()){
                currentPlayer.setBalance(currentPlayer.getBalance()+50);
                ((Railroad)square).setHasDepot(false);
                ((Railroad)square).updateRent();
                System.out.println(currentPlayer.getName()+ " downgraded Railroad");
            }else System.out.println(currentPlayer.getName() + " can not downgrade " + square.getName());
        }else if(square.getClass().getName().equals("Property")){
            if(((Property)square).isUpgraded()){
                Building building = ((Property)square).getBuildingList().get(0);
                ((Property)square).getBuildingList().remove(0);
                currentPlayer.setBalance(currentPlayer.getBalance()+building.getCost()/2); //change increase money
                if(building.getName().equals("Skyscrapper"))
                    ((Property)square).getBuildingList().add(new Hotel(((Property)square).getHotelCost())); //ADD HOTEL TO BUILDING LIST
                else if(building.getName().equals("Hotel")){
                    for (int i=0; i<4; i++)
                        ((Property)square).getBuildingList().add(new House(((Property)square).getHouseCost())); //ADD HOUSE TO BUILDING LIST
                }
                ((Property)square).updateRent(); //SET RENT according to number of houses in the building list
                if(((Property)square).getBuildingList().isEmpty()) ((Property)square).setUpgraded(false);
            }
            else System.out.println(currentPlayer.getName() + " can not downgrade " + square.getName());
        }else System.out.println("Current square is not a deed square");
    }
}
