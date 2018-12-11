package domain.server;

import domain.server.board.Board;
import domain.server.board.DeedSquare;
import domain.server.board.Square;
import domain.server.controller.ServerCommunicationHandler;
import domain.server.die.DiceCup;
import domain.server.move.NormalMove;
import domain.server.move.UpDownMove;
import domain.util.Flags;
import domain.util.GameInfo;
import domain.util.MessageConverter;


public class GameLogic {
    private static GameLogic ourInstance;
    boolean mrMonopolyChecked = false;


    //TODO Add more

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
//        ServerCommunicationHandler.getInstance().sendResponse(poolFlag, getCurrentPlayer().getName(), money);
//    }

    //    public boolean buy() {
//        /* true ??????? */
//        System.out.println("in  logic buy");
//
//        if (getCurrentPlayer().buy()) {
//            System.out.println("player buy completed, is sending action");
//            ServerCommunicationHandler.getInstance().sendResponse(buyFlag, getCurrentPlayer().getName());
//            return true;
//        } else return false;
//    }
//
//    public boolean payRent() {
//        /* true ??????? */
//        System.out.println("in  logic buy");
//
//        if (getCurrentPlayer().payRent()) {
//            System.out.println("player payRent completed, is sending action");
//            ServerCommunicationHandler.getInstance().sendResponse(payRentFlag, getCurrentPlayer().getName());
//            return true;
//        } else return false;
//    }
//
//
    public int [] roll(String name) {
        //TODO check if the player can roll
        System.out.println("\n\nGameLogic: roll\n\n");
        int [] loc = GameInfo.getInstance().getPlayer(name).getToken().getLocation();
        String locName = Board.getInstance().getSquare(loc[0], loc[1]).getName();
        int [] currentDice = DiceCup.getInstance().rollDice(locName);
        System.out.println("In the Game Logic Roll Method");
        return currentDice;
    }

//
//    private void selectDestinationSQ() {
//        // TODO Burda UI ile iletisime gecmem lazim gibi duruyo
//        // UIUptader e squareden dinlemesini soyleyecek, Square den dinlenen square name Player Action controller ile burdaki bi methoda gelcek o da player suraya gitti diye mesaj yollayacak buyuk olasilikla move flag i ile.
//    }
//

    public int getTotalRoll(String name){
        int totalRoll;
        if (GameInfo.getInstance().getPeek().getFaceValues()[2] <= 3) {
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
        if(GameInfo.getInstance().getPlayer(GameInfo.getInstance().getCurrentPlayer()).getReadiness().equals("Bot")){
            setMrMonopolyChecked(false);
        }
        if (GameInfo.getInstance().getPlayer(name).getFaceValues()[2] == 7 && !isMrMonopolyChecked()){

            setMrMonopolyChecked(true);
            if(GameInfo.getInstance().getPlayer(GameInfo.getInstance().getCurrentPlayer()).getReadiness().equals("Bot")){
                setMrMonopolyChecked(false);
            }
            int[] loc = GameInfo.getInstance().getPlayer(name).getToken().getLocation().clone();
            loc = findNextUnOwnedSquare(loc);

            GameInfo.getInstance().getPlayer(name).getToken().setLocation(loc);

            /*write this   3 line of code better*/

            //if (GameLogic.getInstance().checkMoveConditions(name)) {

            //   String newLoc = loc.toString();
            //GameLogic.getInstance().move(name);

            String locName = Board.getInstance().getSquare(loc[0], loc[1]).getName();

            String locat =                 MessageConverter.convertArrayToString(loc) + "@" + locName;

            ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Move"), name, locat);
            // }




            return  true;
        }

        return false;
    }

    public int[] findNextUnOwnedSquare(int[] loc) {
        return scanLayer(loc , loc[0]);

    }

    public int[] scanLayer(int[] loc ,int layer){
        if(layer==0) {
            for(int i=1;i<ZEROTH_LAYER_SQ;i++){
                if(  findNextSquare(loc , i) instanceof DeedSquare
                        && ((DeedSquare) findNextSquare(loc , i)).getOwner()==null)
                    return findNextSquare(loc , i).getLocation();
            }
        }
        else if(layer==1) {
            for(int i=1;i<FIRST_LAYER_SQ;i++){
                findNextSquare(loc , i);
                if(  findNextSquare(loc , i) instanceof DeedSquare
                        && ((DeedSquare) findNextSquare(loc , i)).getOwner()==null)
                    return findNextSquare(loc , i).getLocation();}
        }
        else if(layer==2) {
            for(int i=1;i<SECOND_LAYER_SQ;i++){
                findNextSquare(loc , i);
                if(  findNextSquare(loc , i) instanceof DeedSquare
                        && ((DeedSquare) findNextSquare(loc , i)).getOwner()==null)
                    return findNextSquare(loc , i).getLocation();}
        }
        return  loc ;      }

    public Square findNextSquare(int[] loc , int i){
        if(loc[0]==0){
            int[] searchLoc = {0,loc[1]};

            if((loc[1]+i)>(ZEROTH_LAYER_SQ-1)){
                searchLoc[1]=loc[1]+i-ZEROTH_LAYER_SQ+1;}
            else{
                searchLoc[1]=loc[1]+i;}
            return Board.getInstance().getSquare(searchLoc[0] , searchLoc[1]);
        }

        else if(loc[0]==1){
            int[] searchLoc = {1,loc[1]};
            if((loc[1]+i)>(FIRST_LAYER_SQ-1)){
                searchLoc[1]=loc[1]+i-FIRST_LAYER_SQ+1;}
            else{
                searchLoc[1]=loc[1]+i;}
            return Board.getInstance().getSquare(searchLoc[0] , searchLoc[1]);

        }

        else if(loc[0]==2){
            int[] searchLoc = {2,loc[1]};
            if((loc[1]+i)>(SECOND_LAYER_SQ-1)){
                searchLoc[1]=loc[1]+i-SECOND_LAYER_SQ+1;}
            else{
                searchLoc[1]=loc[1]+i;}
            return Board.getInstance().getSquare(searchLoc[0] , searchLoc[1]);

        }
        else  return Board.getInstance().getSquare(loc[0] , loc[1]);

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
        if(!checkDouble(name)) return false;
        else if(checkJail(name)) return false;
        else return true;
    }

    public void updateDoubleCounter(String name) {
        if(checkDouble(name) && !checkJail(name)){
            ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("DoubleCounter"), name, "1");
        }
    }


    public boolean checkMoveConditions(String name){
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
        ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Move"), name,MessageConverter.convertArrayToString(Board.getInstance().getNameGivenSquare("Jail").getLocation()) + "@" + "Jail");
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


////    public void upgrade(Square square) {
////        Player currentPlayer = getCurrentPlayer();
////        if (square.getClass().getName().equals("RailRoad")) {
////            if (currentPlayer.getBalance() >= 100) {
////                ((Railroad) square).setHasDepot(true);
////                currentPlayer.decreaseMoney(100);
////                ((Railroad) square).updateRent();
////                System.out.println(currentPlayer.getName() + " upgraded Railroad ");
////            } else
////                System.out.println(currentPlayer.getName() + " does not have enough money to upgrade " + square.getName());
////        } else if (square.getClass().getName().equals("Property")) {
////            if (currentPlayer.checkMajority(((Property) square))) {
////                if (((Property) square).isUpgradable((Property) square)) {
////                    if (((Property) square).getBuildingList().get(0).getName().equals("Hotel") && currentPlayer.getBalance() >= ((Property) square).getSkyScrapperCost()) {
////                        ((Property) square).getBuildingList().remove(0);
////                        ((Property) square).getBuildingList().add(new Skyscraper());
////                        currentPlayer.decreaseMoney(((Property) square).getSkyScrapperCost());
////
////                    } else if ((((Property) square).getBuildingList().size() == 4) && currentPlayer.getBalance() >= ((Property) square).getSkyScrapperCost()) {
////                        ((Property) square).getBuildingList().clear();
////                        ((Property) square).getBuildingList().add(new Hotel());
////                        currentPlayer.decreaseMoney(((Property) square).getHotelCost());
////
////                    } else {
////                        if (currentPlayer.getBalance() >= ((Property) square).getHouseCost()) {
////                            ((Property) square).getBuildingList().add(new House());
////                            currentPlayer.decreaseMoney(((Property) square).getHouseCost());
////                        }
////                    }
////                    ((Property) square).updateRent();
////                    System.out.println(currentPlayer.getName() + " upgraded " + square.getName() + " to " +
////                            ((Property) square).getBuildingList().get(0));
////                    ((Property) square).setUpgraded(true);
////                } else System.out.println("Current square is not a deed square");
////            }
////
////        }
////        ServerCommunicationHandler.getInstance().sendupdowngradeAction(upgradeFlag, (DeedSquare) square);
////    }
//
////    public void downgrade(Square square) {
////        Player currentPlayer = getCurrentPlayer();
////        if (square.getClass().getName().equals("Railroad")) {
////            if (((Railroad) square).isHasDepot()) {
////                currentPlayer.increaseMoney(50);
////                ((Railroad) square).setHasDepot(false);
////                ((Railroad) square).updateRent();
////                System.out.println(currentPlayer.getName() + " downgraded Railroad");
////            } else System.out.println(currentPlayer.getName() + " can not downgrade " + square.getName());
////        } else if (square.getClass().getName().equals("Property")) {
////            if (((Property) square).isUpgraded()) {
////                Building building = ((Property) square).getBuildingList().get(0);
////                ((Property) square).getBuildingList().remove(0);
////                currentPlayer.increaseMoney(building.getCost() / 2);
////                if (building.getName().equals("Skyscrapper"))
////                    ((Property) square).getBuildingList().add(new Hotel());
////                else if (building.getName().equals("Hotel")) {
////                    for (int i = 0; i < 4; i++)
////                        ((Property) square).getBuildingList().add(new House());
////                }
////                ((Property) square).updateRent(); //SET RENT according to number of houses in the building list
////                if (((Property) square).getBuildingList().isEmpty()) ((Property) square).setUpgraded(false);
////            } else System.out.println(currentPlayer.getName() + " can not downgrade " + square.getName());
////        } else System.out.println("Current square is not a deed square");
////        ServerCommunicationHandler.getInstance().sendupdowngradeAction(downgradeFlag, (DeedSquare) square);
////    }
//
//
//    private void checkSpecialSquare(int[] newLoc) { // TODO Buggy Code Burda para degisimi yollanan mesajlar felan ile ilgili sorunlar var.
//        Square square = Board.getInstance().getSquare(newLoc[0], newLoc[1]);
//        if (square instanceof SpecialSquareStrategy) {
//            int initMoney = getCurrentPlayer().getBalance();
//            System.out.println("\n\n================\nInitMoney: " + initMoney + "\n");
//
//            ((SpecialSquareStrategy) square).doAction();
//
//            int finalMoney = getCurrentPlayer().getBalance();
//            System.out.println("\n\n================\nFinalMoney: " + finalMoney + "\n");
//
//            ServerCommunicationHandler.getInstance().sendResponse(specialSquareFlag, getCurrentPlayer().getName());
//
//
//            /* sendResponse will be handled for many cards
//             * so far considers only two cards
//             * interpret should consider other cards as well.
//             * defined flags not enough*/
//            if (square instanceof Chance) {
//                ServerCommunicationHandler.getInstance().sendResponse(moneyFlag, getCurrentPlayer().getName(), finalMoney - initMoney);
//            } else if (square instanceof CommunityChest) {
//                int loc[] = getCurrentPlayer().getToken().getLocation();
//                if (loc[0] != 1)
//                    ServerCommunicationHandler.getInstance().sendResponse(moneyFlag, getCurrentPlayer().getName(), finalMoney - initMoney);
//                /*increase money flag handles both increase and decrease*/
//
//            }
//        }
//    }
//
//

}
