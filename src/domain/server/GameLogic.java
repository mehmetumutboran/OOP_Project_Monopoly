package domain.server;

import domain.server.board.Board;
import domain.server.board.DeedSquare;
import domain.server.board.Square;
import domain.server.building.Hotel;
import domain.server.building.Skyscraper;
import domain.server.controller.ServerCommunicationHandler;
import domain.server.die.DiceCup;
import domain.server.move.NormalMove;
import domain.server.move.UpDownMove;
import domain.server.player.Player;
import domain.util.Flags;
import domain.util.GameInfo;
import domain.util.MessageConverter;

import java.util.Arrays;

public class GameLogic {
    private static GameLogic ourInstance;
    boolean mrMonopolyChecked = false;


    private static final int SECOND_LAYER_SQ = 24;
    private static final int FIRST_LAYER_SQ = 40;
    private static final int ZEROTH_LAYER_SQ = 56;
    private static final int GO_COLLECT = 200;

    public static GameLogic getInstance() {
        if (ourInstance == null) {
            ourInstance = new GameLogic();
        }
        return ourInstance;
    }

    private GameLogic() {

    }

//    void changePool(String money) { // TODO Useless
//        Board.getInstance().increasePool(Integer.parseInt(money));
//        ServerCommunicationHandler.getInstance().sendResponse(poolFlag, getCurrentPlayerName().getName(), money);
//    }

    //    public boolean buy() {
//        /* true ??????? */
//        System.out.println("in  logic buy");
//
//        if (getCurrentPlayerName().buy()) {
//            System.out.println("player buy completed, is sending action");
//            ServerCommunicationHandler.getInstance().sendResponse(buyFlag, getCurrentPlayerName().getName());
//            return true;
//        } else return false;
//    }
//
//    public boolean payRent() {
//        /* true ??????? */
//        System.out.println("in  logic buy");
//
//        if (getCurrentPlayerName().payRent()) {
//            System.out.println("player payRent completed, is sending action");
//            ServerCommunicationHandler.getInstance().sendResponse(payRentFlag, getCurrentPlayerName().getName());
//            return true;
//        } else return false;
//    }
//
//
    public int[] roll(String name) {
        //TODO check if the player can roll
        System.out.println("\n\nGameLogic: roll\n\n");
        int[] loc = GameInfo.getInstance().getPlayer(name).getToken().getLocation();
        String locName = Board.getInstance().getSquare(loc[0], loc[1]).getName();
        int[] currentDice = DiceCup.getInstance().rollDice(locName);
        System.out.println("In the Game Logic Roll Method");
        return currentDice;
    }

//
//    private void selectDestinationSQ() {
//        // TODO Burda UI ile iletisime gecmem lazim gibi duruyo
//        // UIUptader e squareden dinlemesini soyleyecek, Square den dinlenen square name Player Action controller ile burdaki bi methoda gelcek o da player suraya gitti diye mesaj yollayacak buyuk olasilikla move flag i ile.
//    }
//

    public int getTotalRoll(String name) {
        int totalRoll;
//        if (GameInfo.getInstance().getPeek().getFaceValues()[2] <= 3) { // TODO Why peek?
        if (GameInfo.getInstance().getPlayer(name).getFaceValues()[2] <= 3) {
            totalRoll = GameInfo.getInstance().getPlayer(name).getFaceValues()[0]
                    + GameInfo.getInstance().getPlayer(name).getFaceValues()[1]
                    + GameInfo.getInstance().getPlayer(name).getFaceValues()[2];
        } else {
            totalRoll = GameInfo.getInstance().getPlayer(name).getFaceValues()[0]
                    + GameInfo.getInstance().getPlayer(name).getFaceValues()[1];
        }
        return totalRoll;
    }

    private boolean checkDouble(String name) {
        return (GameInfo.getInstance().getPlayer(name).getFaceValues()[0] ==
                GameInfo.getInstance().getPlayer(name).getFaceValues()[1]);
    }

    private boolean checkJail(String name) {
        return GameInfo.getInstance().getPlayer(name).isInJail();
    }

    private boolean checkBus(String name) {
        return false;
    }

    public boolean isMrMonopolyChecked() {
        return mrMonopolyChecked;
    }

    public void setMrMonopolyChecked(boolean mrMonopolyChecked) {
        this.mrMonopolyChecked = mrMonopolyChecked;
    }

    public boolean checkMrMonopoly(String name) {

        /*check if bot*/
        /*
        if(GameInfo.getInstance().getPlayer(GameInfo.getInstance().getCurrentPlayer()).getReadiness().equals("Bot")){
            setMrMonopolyChecked(false);
        }
        */
        /*check if mrMonopoly and it is not checked played yet*/
        //     if (GameInfo.getInstance().getPlayer(name).getFaceValues()[2] == 7 /*&& !isMrMonopolyChecked()*/){
        /*setMrMonopolyChecked(true);*/
        /*check if bot*/
            /*
            if(GameInfo.getInstance().getPlayer(GameInfo.getInstance().getCurrentPlayer()).getReadiness().equals("Bot")){
                setMrMonopolyChecked(false);
            }
            */
        if (GameInfo.getInstance().getPlayer(name).getFaceValues()[2] == 7) {

            int[] loc = GameInfo.getInstance().getPlayer(name).getToken().getLocation().clone();
            System.out.println("!!!!!!!!!!!!" + Arrays.toString(loc));
            loc = findNextUnOwnedSquare(loc);
            System.out.println("!!!!!!!!!!!!" + Arrays.toString(loc));

            //GameInfo.getInstance().getPlayer(name).getToken().setLocation(loc);

            String locName = Board.getInstance().getSquare(loc[0], loc[1]).getName();
            String locat = MessageConverter.convertArrayToString(loc) + "@" + locName;
            ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Move"), name, locat);
            return true;

        }
        //      return  true;
        //  }
        else return false;
    }

    public int[] findNextUnOwnedSquare(int[] loc) {
        return scanLayer(loc, loc[0]);
    }

    public int[] scanLayer(int[] loc, int layer) {
        if (layer == 0) {
            for (int i = 1; i < ZEROTH_LAYER_SQ; i++) {
                if (findNextSquare(loc, i) instanceof DeedSquare
                        && ((DeedSquare) findNextSquare(loc, i)).getOwner() == null)
                    return findNextSquare(loc, i).getLocation();
            }
        } else if (layer == 1) {
            for (int i = 1; i < FIRST_LAYER_SQ; i++) {
                findNextSquare(loc, i);
                if (findNextSquare(loc, i) instanceof DeedSquare
                        && ((DeedSquare) findNextSquare(loc, i)).getOwner() == null)
                    return findNextSquare(loc, i).getLocation();
            }
        } else if (layer == 2) {
            for (int i = 1; i < SECOND_LAYER_SQ; i++) {
                findNextSquare(loc, i);
                if (findNextSquare(loc, i) instanceof DeedSquare
                        && ((DeedSquare) findNextSquare(loc, i)).getOwner() == null)
                    return findNextSquare(loc, i).getLocation();
            }
        }
        return loc;
    }

    public Square findNextSquare(int[] loc, int i) {
        if (loc[0] == 0) {
            int[] searchLoc = {0, loc[1]};

            if ((loc[1] + i) > (ZEROTH_LAYER_SQ - 1)) {
                searchLoc[1] = loc[1] + i - ZEROTH_LAYER_SQ + 1;
            } else {
                searchLoc[1] = loc[1] + i;
            }
            return Board.getInstance().getSquare(searchLoc[0], searchLoc[1]);
        } else if (loc[0] == 1) {
            int[] searchLoc = {1, loc[1]};
            if ((loc[1] + i) > (FIRST_LAYER_SQ - 1)) {
                searchLoc[1] = loc[1] + i - FIRST_LAYER_SQ + 1;
            } else {
                searchLoc[1] = loc[1] + i;
            }
            return Board.getInstance().getSquare(searchLoc[0], searchLoc[1]);

        } else if (loc[0] == 2) {
            int[] searchLoc = {2, loc[1]};
            if ((loc[1] + i) > (SECOND_LAYER_SQ - 1)) {
                searchLoc[1] = loc[1] + i - SECOND_LAYER_SQ + 1;
            } else {
                searchLoc[1] = loc[1] + i;
            }
            return Board.getInstance().getSquare(searchLoc[0], searchLoc[1]);

        } else return Board.getInstance().getSquare(loc[0], loc[1]);

    }


    private boolean checkTriple(String name) {
        return (GameInfo.getInstance().getPlayer(name).getFaceValues()[0] ==
                GameInfo.getInstance().getPlayer(name).getFaceValues()[1] && GameInfo.getInstance().getPlayer(name).getFaceValues()[1] ==
                GameInfo.getInstance().getPlayer(name).getFaceValues()[2]);
    }

    private boolean checkThirdDouble(String name) {
        if (GameInfo.getInstance().getPlayer(name).getDoubleCounter() == 3) {
            ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("DoubleCounter"), name, "0");
            return true;
        }
        return false;
    }

    public boolean checkSecondTurn(String name) {
        if (!checkDouble(name)) return false;
        else if (checkJail(name)) return false;
        else return true;
    }

    public void updateDoubleCounter(String name) {
        if (checkDouble(name) && !checkJail(name)) {
            ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("DoubleCounter"), name, "1");
        }
    }


    public boolean checkMoveConditions(String name) {
        if (GameLogic.getInstance().checkThirdDouble(name)) {
            GameLogic.getInstance().sendToJail(name);
            return false;
        } else if (GameLogic.getInstance().checkJail(name)) {
            GameLogic.getInstance().tryToGoOutOfJail(name);
            return false;
        } else if (GameLogic.getInstance().checkTriple(name)) {
            //selectDestinationSQ();
            return false;
        } else if (GameLogic.getInstance().checkBus(name)) {
            //
            return false;
        }
        return true;

    }

    private void tryToGoOutOfJail(String name) {
        if (checkDouble(name)) {
            ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("GoOutOfJail"), name);
            //TODO Prompt yollanabilir.
            move(name);
        }
    }

    private void sendToJail(String name) {
        ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("GoToJail"), name);
        ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Move"), name, MessageConverter.convertArrayToString(Board.getInstance().getNameGivenSquare("Jail").getLocation()) + "@" + "Jail");
    }

    public String move(String name) {
        int[] lastLoc = GameInfo.getInstance().getPlayer(name).getToken().getLocation();
        int[] newLoc;
        int totalRoll;
        int layerSQNumber = 0;
        switch (lastLoc[0]) {
            case 0:
                layerSQNumber = ZEROTH_LAYER_SQ;
                break;
            case 1:
                layerSQNumber = FIRST_LAYER_SQ;
                break;
            case 2:
                layerSQNumber = SECOND_LAYER_SQ;
                break;
        }
        totalRoll = getTotalRoll(name);
        if (Board.getInstance().railRoadFind(lastLoc, totalRoll)[0] != null) {
            if (totalRoll % 2 == 1) {
                newLoc = NormalMove.getInstance().move(lastLoc, totalRoll, layerSQNumber);
            } else {
                newLoc = UpDownMove.getInstance().move(lastLoc, totalRoll, layerSQNumber);
            }
        } else {
            newLoc = NormalMove.getInstance().move(lastLoc, totalRoll, layerSQNumber);
        }

        String locStr = MessageConverter.convertArrayToString(newLoc);
        System.out.println("In the Game Logic Move Method");

        return locStr;
    }

    //    private int[] upDownMove(int[] lastLoc, int roll, int layerSQNumber) {
//        return null;
//    }

//    private int[] upDownMoveRec(int[] lastLoc, int roll) {
//        return null;
//    }

//    private int[] railRoadHelper(int[] lastLoc, int layer, int railSq, int[] tryLoc, int roll, int layerSQNumber) {
//        return null;
//    }

//    private int[] normalMove(int[] lastLoc, int roll, int layerSQNumber) {
//    }


//    public void applyRailRoadUpgrade(Square square, Player currentPlayer) {
//        String typeOfUpgrade = "Railroad";
//        ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Upgrade"), currentPlayer.getName(), square.getName(), typeOfUpgrade);
//    }
//
//    public void applyPropertyUpgrade(Square square, int index, Player currentPlayer) {
//        String typeOfUpgrade = null;
//        if (!((Property) square).getBuildingList().isEmpty() && ((Property) square).getBuildingList().get(0) instanceof Hotel) {
//            if (currentPlayer.checkMonopoly((Property) square)) {
//                typeOfUpgrade = "Skyscrapper";
//            } else {
//                System.out.println("CANT UPGRADE TO SKYSCRAPPER BECAUSE NOT MONOPOLY");
//                return;
//                //ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("DontUpgrade"),index," ");
//            }
//        } else if (((Property) square).getBuildingList().size() == 4) {
//            typeOfUpgrade = "Hotel";
//        } else {
//            typeOfUpgrade = "House";
//        }
//
//        ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Upgrade"), currentPlayer.getName(), square.getName(), typeOfUpgrade);
//    }




//    public void applyRailDowngrade(Square square, Player currentPlayer) {
//        String buildingtoDownFrom = "Railroad";
//        ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Downgrade"), currentPlayer.getName(), square.getName(), buildingtoDownFrom);
//    }
//
//    public void applydowngrade(Square square, Player currentPlayer) {
//        String buildingtoDownfrom = null;
//        if (!((Property) square).getBuildingList().isEmpty() && ((Property) square).getBuildingList().get(0) instanceof Skyscraper) {
//            buildingtoDownfrom = "Skyscrapper";
//        } else if (!((Property) square).getBuildingList().isEmpty() && ((Property) square).getBuildingList().get(0) instanceof Hotel) {
//            buildingtoDownfrom = "Hotel";
//        } else if (!((Property) square).getBuildingList().isEmpty() && ((Property) square).getBuildingList().size() > 0) {
//            buildingtoDownfrom = "House";
//        }
//        ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Downgrade"), currentPlayer.getName(), square.getName(), buildingtoDownfrom);
//    }


}
