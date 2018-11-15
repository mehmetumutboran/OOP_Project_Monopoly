package domain;

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
                        ((Property)square).getBuildingList().remove(0);
                        ((Property)square).getBuildingList().add(new Skyscraper(((Property)square).getSkyScrapperCost()));
                        currentPlayer.setBalance((currentPlayer).getBalance()-((Property)square).getSkyScrapperCost());
                    }else if(((Property) square).getBuildingList().size()==4){
                        ((Property)square).getBuildingList().clear();
                        ((Property)square).getBuildingList().add(new Hotel(((Property)square).getHotelCost()));
                        currentPlayer.setBalance((currentPlayer).getBalance()-((Property)square).getHotelCost());
                    }else {
                        ((Property)square).getBuildingList().add(new House (((Property)square).getHouseCost()));
                        currentPlayer.setBalance((currentPlayer).getBalance()-((Property)square).getHouseCost());
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
