package domain;

import domain.board.Board;
import domain.board.SpecialSquareStrategy;
import domain.board.Square;
import domain.board.specialSquares.Chance;
import domain.board.specialSquares.CommunityChest;
import domain.controller.GameCommunicationHandler;
import domain.player.Player;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class GameLogic {
    private static GameLogic ourInstance;

    public static final char buyFlag = 'B';
    public static final char rollFlag = 'R';
    public static final char getRentFlag = 'I';
    public static final char moneyFlag = 'S';
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
    public static final char removeFlag = 'X';
    public static final char specialSquareFlag = 'A';
    public static final char poolFlag = 'H';
    public static final char tokenFlag = 'T';

    //TODO Add more

    private volatile Deque<String> players;
    private volatile ArrayList<Player> playerList;

    private static final int SECONDLAYERSQ = 24;
    private static final int FIRSTLAYERSQ = 40;
    private static final int ZEROTHLAYERSQ = 56;
    private static final int GO_COLLECT = 200;

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

    public void changePool(String money) {
        Board.getInstance().increasePool(Integer.parseInt(money));
    }

    public boolean buy() {
        /* true ??????? */
        System.out.println("in  logic buy");

        if (getCurrentPlayer().buy()) {
            System.out.println("player buy completed, is sending action");
            GameCommunicationHandler.getInstance().sendAction(buyFlag);
            return true;
        } else return false;
    }

    public boolean payRent() {
        /* true ??????? */
        System.out.println("in  logic buy");

        if (getCurrentPlayer().payRent()) {
            System.out.println("player payRent completed, is sending action");
            GameCommunicationHandler.getInstance().sendAction(payRentFlag);
            return true;
        } else return false;
    }


    public void roll() {
        getCurrentPlayer().rollDice();
        if (checkThirdDouble()) {

        } else if (checkJail()) {

        } else if (checkBus()) {

        } else {
            move();
        }


        checkMrMonopoly();
        System.out.println("In the Game Logic Roll Method");
        GameCommunicationHandler.getInstance().sendAction(rollFlag);
    }

    private void move() {
        if (checkDouble()) getCurrentPlayer().incrementDoubleCounter();
        int[] lastLoc = getCurrentPlayer().getToken().getLocation();
        int[] lL = lastLoc.clone();
        int[] newLoc;
        int totalRoll;
        int layerSQNumber = 0;
        switch (lastLoc[0]) {
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
        if (getCurrentPlayer().getFaceValues()[2] <= 3) {
            totalRoll = getCurrentPlayer().getFaceValues()[0]
                    + getCurrentPlayer().getFaceValues()[1]
                    + getCurrentPlayer().getFaceValues()[2];
        } else {
            totalRoll = getCurrentPlayer().getFaceValues()[0]
                    + getCurrentPlayer().getFaceValues()[1];
        }
        if (Board.getInstance().railRoadFind(lastLoc, totalRoll)[0] != null) {
            if (totalRoll % 2 == 1) {
                newLoc = normalMove(lastLoc, totalRoll, layerSQNumber);
            } else {
                newLoc = upDownMove(lastLoc, totalRoll, layerSQNumber);
            }
        } else {
            newLoc = normalMove(lastLoc, totalRoll, layerSQNumber);
        }

        getCurrentPlayer().getToken().setLocation(newLoc);
        System.out.println("In the Game Logic Move Method");

        GameCommunicationHandler.getInstance().sendAction(moveFlag);
        /*********************************/
        GameCommunicationHandler.getInstance().sendTokenMovementAction(tokenFlag, lL);
        checkSpecialSquare(newLoc);
    }

    private int[] upDownMove(int[] lastLoc, int roll, int layerSQNumber) {
        int railroad;
        if (Board.getInstance().railRoadFind(lastLoc, roll)[0].getLocation()[1] - lastLoc[1] < 0)
            railroad = Board.getInstance().railRoadFind(lastLoc, roll)[0].getLocation()[1] - lastLoc[1] + layerSQNumber;
        else railroad = Board.getInstance().railRoadFind(lastLoc, roll)[0].getLocation()[1] - lastLoc[1];
        roll = roll - railroad;
        lastLoc = normalMove(lastLoc, railroad, layerSQNumber);
        return upDownMoveRec(lastLoc, roll);
    }

    private int[] upDownMoveRec(int[] lastLoc, int roll) {
        String sqName = Board.getInstance().railRoadFind(lastLoc, roll)[0].getName();
        int[] tryLoc = new int[2];
        switch (sqName) {
            case "Reading Railroad":
                if (lastLoc[0] == 0) {
                    return railRoadHelper(lastLoc, 1, 5, tryLoc, roll, FIRSTLAYERSQ);
                } else if (lastLoc[0] == 1) {
                    return railRoadHelper(lastLoc, 0, 7, tryLoc, roll, ZEROTHLAYERSQ);
                }
                break;
            case "B.&O. Railroad":
                if (lastLoc[0] == 0) {
                    return railRoadHelper(lastLoc, 1, 25, tryLoc, roll, FIRSTLAYERSQ);
                } else if (lastLoc[0] == 1) {
                    return railRoadHelper(lastLoc, 0, 35, tryLoc, roll, ZEROTHLAYERSQ);
                }
                break;
            case "Pennsylvania Railroad":
                if (lastLoc[0] == 1) {
                    return railRoadHelper(lastLoc, 2, 9, tryLoc, roll, SECONDLAYERSQ);
                } else if (lastLoc[0] == 2) {
                    return railRoadHelper(lastLoc, 1, 15, tryLoc, roll, FIRSTLAYERSQ);
                }
                break;
            case "Short Line Railroad":
                if (lastLoc[0] == 1) {
                    return railRoadHelper(lastLoc, 2, 21, tryLoc, roll, SECONDLAYERSQ);
                } else if (lastLoc[0] == 2) {
                    return railRoadHelper(lastLoc, 1, 35, tryLoc, roll, FIRSTLAYERSQ);
                }
                break;
            default:
                break;
        }
        return lastLoc;
    }

    private int[] railRoadHelper(int[] lastLoc, int layer, int railSq, int[] tryLoc, int roll, int layerSQNumber) {
        lastLoc[0] = layer;
        lastLoc[1] = railSq;
        tryLoc[0] = lastLoc[0];
        tryLoc[1] = lastLoc[1] + 1;
        if (Board.getInstance().railRoadFind(tryLoc, roll)[0] == null || Board.getInstance().railRoadFind(tryLoc, roll)[0].getLocation()[1] - lastLoc[1] > roll) {
            lastLoc = normalMove(lastLoc, roll, layerSQNumber);
            return lastLoc;
        }
        int railroad;
        if (Board.getInstance().railRoadFind(tryLoc, roll)[0].getLocation()[1] - lastLoc[1] < 0)
            railroad = Board.getInstance().railRoadFind(tryLoc, roll)[0].getLocation()[1] - lastLoc[1] + layerSQNumber;
        else railroad = Board.getInstance().railRoadFind(tryLoc, roll)[0].getLocation()[1] - lastLoc[1];
        roll = roll - railroad;
        lastLoc = normalMove(lastLoc, railroad, layerSQNumber);
        return upDownMoveRec(lastLoc, roll);
    }

    private int[] normalMove(int[] lastLoc, int roll, int layerSQNumber) {
        int[] newLoc = new int[2];
        if (lastLoc[1] + roll > layerSQNumber - 1) {
            newLoc[0] = lastLoc[0];
            newLoc[1] = lastLoc[1] + roll - layerSQNumber;
            if (lastLoc[0] == 1) {
                getCurrentPlayer().increaseMoney(GO_COLLECT);
                System.out.println("Player passed above Go Square");
                GameCommunicationHandler.getInstance().sendMoneyAction(GO_COLLECT, getCurrentPlayer().getName());
            }
        } else {
            newLoc[0] = lastLoc[0];
            newLoc[1] = lastLoc[1] + roll;
        }
        return newLoc;
    }

    //public int bigSquareChecker (int [] l1 , int [] l2){
    //7  if(l1[])
    //}

    private boolean checkDouble() {
        return (getCurrentPlayer().getFaceValues()[0] ==
                getCurrentPlayer().getFaceValues()[1]);
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

    public void setPlayers(Deque<String> playerQueue) {
        this.players = playerQueue;
    }


    public Deque<String> getPlayers() {
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
        if (!playerList.get(0).getReadiness().equals("Host")) return;
        // Check for bot's turn
        for (Player player : playerList) {
            if (player.getReadiness().equals("Bot") && ((RandomPlayer) player).checkTurn()) {
                break;
            }
        }
    }

    public void finishTurn() {
        GameCommunicationHandler.getInstance().sendAction(finishTurnFlag);
    }

//    public void upgrade(Square square) {
//        Player currentPlayer = getCurrentPlayer();
//        if (square.getClass().getName().equals("RailRoad")) {
//            if (currentPlayer.getBalance() >= 100) {
//                ((Railroad) square).setHasDepot(true);
//                currentPlayer.decreaseMoney(100);
//                ((Railroad) square).updateRent();
//                System.out.println(currentPlayer.getName() + " upgraded Railroad ");
//            } else
//                System.out.println(currentPlayer.getName() + " does not have enough money to upgrade " + square.getName());
//        } else if (square.getClass().getName().equals("Property")) {
//            if (currentPlayer.checkMajority(((Property) square))) {
//                if (((Property) square).isUpgradable((Property) square)) {
//                    if (((Property) square).getBuildingList().get(0).getName().equals("Hotel") && currentPlayer.getBalance() >= ((Property) square).getSkyScrapperCost()) {
//                        ((Property) square).getBuildingList().remove(0);
//                        ((Property) square).getBuildingList().add(new Skyscraper());
//                        currentPlayer.decreaseMoney(((Property) square).getSkyScrapperCost());
//
//                    } else if ((((Property) square).getBuildingList().size() == 4) && currentPlayer.getBalance() >= ((Property) square).getSkyScrapperCost()) {
//                        ((Property) square).getBuildingList().clear();
//                        ((Property) square).getBuildingList().add(new Hotel());
//                        currentPlayer.decreaseMoney(((Property) square).getHotelCost());
//
//                    } else {
//                        if (currentPlayer.getBalance() >= ((Property) square).getHouseCost()) {
//                            ((Property) square).getBuildingList().add(new House());
//                            currentPlayer.decreaseMoney(((Property) square).getHouseCost());
//                        }
//                    }
//                    ((Property) square).updateRent();
//                    System.out.println(currentPlayer.getName() + " upgraded " + square.getName() + " to " +
//                            ((Property) square).getBuildingList().get(0));
//                    ((Property) square).setUpgraded(true);
//                } else System.out.println("Current square is not a deed square");
//            }
//
//        }
//        GameCommunicationHandler.getInstance().sendupdowngradeAction(upgradeFlag, (DeedSquare) square);
//    }

//    public void downgrade(Square square) {
//        Player currentPlayer = getCurrentPlayer();
//        if (square.getClass().getName().equals("Railroad")) {
//            if (((Railroad) square).isHasDepot()) {
//                currentPlayer.increaseMoney(50);
//                ((Railroad) square).setHasDepot(false);
//                ((Railroad) square).updateRent();
//                System.out.println(currentPlayer.getName() + " downgraded Railroad");
//            } else System.out.println(currentPlayer.getName() + " can not downgrade " + square.getName());
//        } else if (square.getClass().getName().equals("Property")) {
//            if (((Property) square).isUpgraded()) {
//                Building building = ((Property) square).getBuildingList().get(0);
//                ((Property) square).getBuildingList().remove(0);
//                currentPlayer.increaseMoney(building.getCost() / 2);
//                if (building.getName().equals("Skyscrapper"))
//                    ((Property) square).getBuildingList().add(new Hotel());
//                else if (building.getName().equals("Hotel")) {
//                    for (int i = 0; i < 4; i++)
//                        ((Property) square).getBuildingList().add(new House());
//                }
//                ((Property) square).updateRent(); //SET RENT according to number of houses in the building list
//                if (((Property) square).getBuildingList().isEmpty()) ((Property) square).setUpgraded(false);
//            } else System.out.println(currentPlayer.getName() + " can not downgrade " + square.getName());
//        } else System.out.println("Current square is not a deed square");
//        GameCommunicationHandler.getInstance().sendupdowngradeAction(downgradeFlag, (DeedSquare) square);
//    }

    public void removePlayer(String name) {
        Player player = playerList.stream().filter(p -> p.getName().equals(name)).collect(Collectors.toList()).get(0);

        resetPlayer(player);
        if (getCurrentPlayer().getName().equals(name))
            GameLogic.getInstance().finishTurn();

        playerList.removeIf(p -> p.getName().equals(name));
        players.remove(player.getName());

        System.out.println("\n\n===========================\n" +
                playerList + "\n" +
                players + "\n");


    }


    private void resetPlayer(Player player) {
        //remove Buildings
        player.getOwnedProperties().forEach(x -> x.setBuildingList(new ArrayList<>()));
        player.getOwnedRailroads().forEach(x -> x.setHasDepot(false));

        //reset owner
        player.getOwnedProperties().forEach(x -> x.setOwner(null));
        player.getOwnedRailroads().forEach(x -> x.setOwner(null));
        player.getOwnedUtilities().forEach(x -> x.setOwner(null));

    }

    private void checkSpecialSquare(int[] newLoc) {
        Square square = Board.getInstance().getSquare(newLoc[0], newLoc[1]);
        if (square instanceof SpecialSquareStrategy) {
            int initMoney = getCurrentPlayer().getBalance();
            System.out.println("\n\n================\nInitMoney: " + initMoney + "\n");

            ((SpecialSquareStrategy) square).doAction();

            int finalMoney = getCurrentPlayer().getBalance();
            System.out.println("\n\n================\nFinalMoney: " + finalMoney + "\n");

            GameCommunicationHandler.getInstance().sendAction(specialSquareFlag);


            /* sendAction will be handled for many cards
             * so far considers only two cards
             * interpret should consider other cards as well.
             * defined flags not enough*/
            if (square instanceof Chance) {
                GameCommunicationHandler.getInstance().sendMoneyAction(finalMoney - initMoney, getCurrentPlayer().getName());
            } else if (square instanceof CommunityChest) {
                int loc[] = getCurrentPlayer().getToken().getLocation();
                if (loc[0] != 1)
                    GameCommunicationHandler.getInstance().sendMoneyAction(finalMoney - initMoney, getCurrentPlayer().getName());
                /*increase money flag handles both increase and decrease*/

            }
        }
    }


    public Player getCurrentPlayer() {
        return getPlayer(players.peekFirst());
    }

}
