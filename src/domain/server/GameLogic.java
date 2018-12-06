package domain.server;

import domain.server.board.Property;
import domain.server.board.Railroad;
import domain.server.board.Square;
import domain.server.building.Hotel;
import domain.server.building.House;
import domain.server.building.Skyscraper;
import domain.server.controller.ServerCommunicationHandler;
import domain.server.player.Player;
import domain.util.Flags;
import domain.util.GameInfo;
import domain.util.MessageConverter;

import java.sql.SQLClientInfoException;

public class GameLogic {
    private static GameLogic ourInstance;

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
    public void roll(String name) {
        //TODO check if the player can roll
        System.out.println("\n\nGAmrLogic: roll\n\n");

        GameInfo.getInstance().getPlayer(name).rollDice();
        ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Roll"), name);

//        if (checkThirdDouble()) {
//            sendToJail();
//        } else if (checkJail()) {
//            tryToGoOutOfJail();
//        } else if (checkTriple()) {
//            selectDestinationSQ();
//        } else if (checkBus()) {
//
//        } else {
//            move(false);
//        }


//        checkMrMonopoly();
        System.out.println("In the Game Logic Roll Method");

    }
//
//    private void selectDestinationSQ() {
//        // TODO Burda UI ile iletisime gecmem lazim gibi duruyo
//        // UIUptader e squareden dinlemesini soyleyecek, Square den dinlenen square name Player Action controller ile burdaki bi methoda gelcek o da player suraya gitti diye mesaj yollayacak buyuk olasilikla move flag i ile.
//    }
//
//    private void tryToGoOutOfJail() {
//        if (checkDouble()) {
//            ServerCommunicationHandler.getInstance().sendResponse(goOutJailFlag, getCurrentPlayer().getName());
//            //getCurrentPlayer().setInJail(false); // TODO Message Interpret does this
//            move(true); // TODO Jailden cikarkenki double atma double count u arttirir mi?
//        } // TODO Burda else yazcak bisey olur mu?
//    }
//
//    private void sendToJail() {
//        //getCurrentPlayer().setInJail(true); // TODO Message Interpret does this
//        //getCurrentPlayer().getToken().setLocation(Board.getInstance().getNameGivenSquare("Jail").getLocation()); // TODO Message Interpret does this
//        ServerCommunicationHandler.getInstance().sendResponse(jailFlag, getCurrentPlayer().getName());
//        //ServerCommunicationHandler.getInstance().sendResponse(tokenFlag,getCurrentPlayer().getName());
//    }
//
//    private void move(Boolean isFromJail) {
//        if (checkDouble() && !isFromJail)
//            getCurrentPlayer().incrementDoubleCounter(); // TODO Burada UI a yada MessageInt. den UI a mesaj yollayip cift degilse roll butonunu kapatmamiz lazim.
//        int[] lastLoc = getCurrentPlayer().getToken().getLocation();
//        int[] newLoc;
//        int totalRoll;
//        int layerSQNumber = 0;
//        switch (lastLoc[0]) {
//            case 0:
//                layerSQNumber = ZEROTH_LAYER_SQ;
//                break;
//            case 1:
//                layerSQNumber = FIRST_LAYER_SQ;
//                break;
//            case 2:
//                layerSQNumber = SECOND_LAYER_SQ;
//                break;
//        }
//        if (getCurrentPlayer().getFaceValues()[2] <= 3) {
//            totalRoll = getCurrentPlayer().getFaceValues()[0]
//                    + getCurrentPlayer().getFaceValues()[1]
//                    + getCurrentPlayer().getFaceValues()[2];
//        } else {
//            totalRoll = getCurrentPlayer().getFaceValues()[0]
//                    + getCurrentPlayer().getFaceValues()[1];
//        }
//        if (Board.getInstance().railRoadFind(lastLoc, totalRoll)[0] != null) {
//            if (totalRoll % 2 == 1) {
//                newLoc = normalMove(lastLoc, totalRoll, layerSQNumber);
//            } else {
//                newLoc = upDownMove(lastLoc, totalRoll, layerSQNumber);
//            }
//        } else {
//            newLoc = normalMove(lastLoc, totalRoll, layerSQNumber);
//        }
//
//        //getCurrentPlayer().getToken().setLocation(newLoc);
//
//        String locStr = MessageConverter.convertArrayToString(newLoc);
//        System.out.println("In the Game Logic Move Method");
//
//        ServerCommunicationHandler.getInstance().sendResponse(moveFlag, getCurrentPlayer().getName(), locStr);
//        ServerCommunicationHandler.getInstance().sendResponse(tokenFlag, getCurrentPlayer().getName(), locStr);
//        checkSpecialSquare(newLoc);
//    }
//
//    private int[] upDownMove(int[] lastLoc, int roll, int layerSQNumber) {
//        int railroad;
//        if (Board.getInstance().railRoadFind(lastLoc, roll)[0].getLocation()[1] - lastLoc[1] < 0)
//            railroad = Board.getInstance().railRoadFind(lastLoc, roll)[0].getLocation()[1] - lastLoc[1] + layerSQNumber;
//        else railroad = Board.getInstance().railRoadFind(lastLoc, roll)[0].getLocation()[1] - lastLoc[1];
//        roll = roll - railroad;
//        lastLoc = normalMove(lastLoc, railroad, layerSQNumber);
//        return upDownMoveRec(lastLoc, roll);
//    }
//
//    private int[] upDownMoveRec(int[] lastLoc, int roll) {
//        if (Board.getInstance().railRoadFind(lastLoc, roll)[0] != null) {
//            String sqName = Board.getInstance().railRoadFind(lastLoc, roll)[0].getName();
//            int[] tryLoc = new int[2];
//            switch (sqName) {
//                case "Reading Railroad":
//                    if (lastLoc[0] == 0) {
//                        return railRoadHelper(lastLoc, 1, 5, tryLoc, roll, FIRST_LAYER_SQ);
//                    } else if (lastLoc[0] == 1) {
//                        return railRoadHelper(lastLoc, 0, 7, tryLoc, roll, ZEROTH_LAYER_SQ);
//                    }
//                    break;
//                case "B.&O. Railroad":
//                    if (lastLoc[0] == 0) {
//                        return railRoadHelper(lastLoc, 1, 25, tryLoc, roll, FIRST_LAYER_SQ);
//                    } else if (lastLoc[0] == 1) {
//                        return railRoadHelper(lastLoc, 0, 35, tryLoc, roll, ZEROTH_LAYER_SQ);
//                    }
//                    break;
//                case "Pennsylvania Railroad":
//                    if (lastLoc[0] == 1) {
//                        return railRoadHelper(lastLoc, 2, 9, tryLoc, roll, SECOND_LAYER_SQ);
//                    } else if (lastLoc[0] == 2) {
//                        return railRoadHelper(lastLoc, 1, 15, tryLoc, roll, FIRST_LAYER_SQ);
//                    }
//                    break;
//                case "Short Line Railroad":
//                    if (lastLoc[0] == 1) {
//                        return railRoadHelper(lastLoc, 2, 21, tryLoc, roll, SECOND_LAYER_SQ);
//                    } else if (lastLoc[0] == 2) {
//                        return railRoadHelper(lastLoc, 1, 35, tryLoc, roll, FIRST_LAYER_SQ);
//                    }
//                    break;
//                default:
//                    break;
//            }
//        }
//        return lastLoc;
//    }
//
//    private int[] railRoadHelper(int[] lastLoc, int layer, int railSq, int[] tryLoc, int roll, int layerSQNumber) {
//        lastLoc[0] = layer;
//        lastLoc[1] = railSq;
//        tryLoc[0] = lastLoc[0];
//        tryLoc[1] = lastLoc[1] + 1;
//        if (Board.getInstance().railRoadFind(tryLoc, roll)[0] == null || Board.getInstance().railRoadFind(tryLoc, roll)[0].getLocation()[1] - lastLoc[1] > roll) {
//            lastLoc = normalMove(lastLoc, roll, layerSQNumber);
//            return lastLoc;
//        }
//        int railroad;
//        if (Board.getInstance().railRoadFind(tryLoc, roll)[0].getLocation()[1] - lastLoc[1] < 0)
//            railroad = Board.getInstance().railRoadFind(tryLoc, roll)[0].getLocation()[1] - lastLoc[1] + layerSQNumber;
//        else railroad = Board.getInstance().railRoadFind(tryLoc, roll)[0].getLocation()[1] - lastLoc[1];
//        roll = roll - railroad;
//        lastLoc = normalMove(lastLoc, railroad, layerSQNumber);
//        return upDownMoveRec(lastLoc, roll);
//    }
//
//    private int[] normalMove(int[] lastLoc, int roll, int layerSQNumber) {
//        int[] newLoc = new int[2];
//        if (lastLoc[1] + roll > layerSQNumber - 1) {
//            newLoc[0] = lastLoc[0];
//            newLoc[1] = lastLoc[1] + roll - layerSQNumber;
//            if (lastLoc[0] == 1) {
//                // getCurrentPlayer().increaseMoney(GO_COLLECT); // TODO Message Interpret does this
//                System.out.println("Player passed above Go Square");
//                ServerCommunicationHandler.getInstance().sendResponse(moneyFlag, getCurrentPlayer().getName(), GO_COLLECT);
//            }
//        } else {
//            newLoc[0] = lastLoc[0];
//            newLoc[1] = lastLoc[1] + roll;
//        }
//        return newLoc;
//    }
//
//    private boolean checkDouble() {
//        return (getCurrentPlayer().getFaceValues()[0] ==
//                getCurrentPlayer().getFaceValues()[1]);
//    }
//
//
//    private boolean checkJail() {
//        return getCurrentPlayer().isInJail();
//    }
//
//    private boolean checkBus() {
//        return false;
//    }
//
//    private boolean checkMrMonopoly() {
//        return false;
//    }
//
//    private boolean checkTriple() {
//        return (getCurrentPlayer().getFaceValues()[0] ==
//                getCurrentPlayer().getFaceValues()[1] && getCurrentPlayer().getFaceValues()[1] ==
//                getCurrentPlayer().getFaceValues()[2]);
//    }
//
//    private boolean checkThirdDouble() {
//        if (getCurrentPlayer().getDoubleCounter() == 2 && checkDouble()) {
//            getCurrentPlayer().resetDoubleCounter();
//            return true;
//        }
//        return false;
//    }
//
//    public ArrayList<Player> getPlayerList() {
//        return playerList;
//    }
//
//    public void setPlayers(Deque<String> playerQueue) {
//        this.players = playerQueue;
//    }
//
//
//    public Deque<String> getPlayers() {
//        return players;
//    }
//
//    public Player getPlayer(String name) {
//        return playerList.stream().filter(x -> x.getName().equals(name)).collect(Collectors.toList()).get(0);
//    }
//
//    public Player getMyself() {
//        return playerList.get(0);
//    }
//
//
//    public void setPlayerList(ArrayList<Player> playerList) {
//        this.playerList = playerList;
//    }
//
//    synchronized void switchTurn() {
//        getCurrentPlayer().resetDoubleCounter();
//        players.addLast(players.removeFirst());
//        // Bots will play on only host's program
//        if (!playerList.get(0).getReadiness().equals("Host")) return;
//        // Check for bot's turn
//        for (Player player : playerList) {
//            if (player.getReadiness().equals("Bot") && ((RandomPlayer) player).checkTurn()) {
//                break;
//            }
//        }
//        // TODO Burda UI a yada MessageInt. den UI a mesaj yollayip jail de ise adam sadece roll dice ve finish turn buttonlarinin acik olmasi lazim.
//    }
//
//    public void finishTurn() {
//        ServerCommunicationHandler.getInstance().sendResponse(finishTurnFlag, getCurrentPlayer().getName());
//    }
//
        public void upgrade (Square square){
            Player currentPlayer = GameInfo.getInstance().getCurrentPlayer();
            if(square instanceof Railroad && currentPlayer.getBalance()>= ((Railroad) square).getHouseBuildingCost()){
                currentPlayer.decreaseMoney(((Railroad) square).getHouseBuildingCost());
                ((Railroad) square).setHasDepot(true);
                ((Railroad) square).updateRent(((Railroad) square).getCurrentRent()*2);
            }else if(square instanceof Property && currentPlayer.checkMajority((Property)square)
                    &&((Property) square).isUpgradable((Property)square) && currentPlayer.getBalance()>=((Property) square).getHouseBuildingCost()){
                applyUpgrade(square,currentPlayer);
            }
            ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Upgrade"), MessageConverter.convertArrayToString(square.getLocation()));
        }

        public void applyUpgrade (Square square, Player currentPlayer){
            if(((Property)square).getBuildingList().get(0) instanceof Hotel){
                if(currentPlayer.checkMonopoly((Property)square)){
                    ((Property)square).getBuildingList().remove(0);
                    ((Property)square).getBuildingList().add(new Skyscraper(((Property) square).getHouseBuildingCost()));
                    currentPlayer.decreaseMoney(((Property) square).getHouseBuildingCost());
                }else{
                    return;
                }
            }else if(((Property)square).getBuildingList().size()==4){
                ((Property) square).getBuildingList().clear();
                ((Property) square).getBuildingList().add(new Hotel(((Property) square).getHouseBuildingCost()));
                currentPlayer.decreaseMoney(((Property) square).getHouseBuildingCost());
            }else{
                ((Property) square).getBuildingList().add(new House(((Property) square).getHouseBuildingCost()));
                currentPlayer.decreaseMoney(((Property) square).getHouseBuildingCost());
            }
            ((Property) square).setUpgraded(true);
            ((Property) square).updateRent();
        }
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
        public void downgrade (Square square){
        Player currentPlayer = GameInfo.getInstance().getCurrentPlayer();
        if(square instanceof Railroad && !(((Railroad) square).isHasDepot())){
            currentPlayer.increaseMoney(((Railroad) square).getHouseBuildingCost()/2);
            ((Railroad) square).setHasDepot(false);
            ((Railroad) square).updateRent(((Railroad) square).getCurrentRent()/2);
        }else if (square instanceof Property && !(((Property) square).isUpgraded())){
            applydowngrade(square,currentPlayer);
        }
        ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Downgrade"),MessageConverter.convertArrayToString(square.getLocation()));

    }
    public void applydowngrade (Square square, Player currentPlayer){
        int buildingCost = ((Property)square).getBuildingList().get(0).getCost();
        if(((Property) square).getBuildingList().get(0) instanceof Skyscraper){
            ((Property) square).getBuildingList().remove(0);
            ((Property) square).getBuildingList().add(new Hotel(((Property) square).getHouseBuildingCost()));
        }else if(((Property) square).getBuildingList().get(0) instanceof Hotel){
            ((Property) square).getBuildingList().remove(0);
            for (int i=0; i<4; i++){
                ((Property) square).getBuildingList().add(new House(((Property) square).getHouseBuildingCost()));
            }
        }else if (((Property) square).getBuildingList().size()>0)
            ((Property) square).getBuildingList().remove(((Property) square).getBuildingList().size()-1);
        currentPlayer.increaseMoney(buildingCost/2);
        ((Property) square).updateRent();
        if(((Property) square).getBuildingList().isEmpty())
            ((Property) square).setUpgraded(false);
    }

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
//    void removePlayer(String name) {
//        Player player = playerList.stream().filter(p -> p.getName().equals(name)).collect(Collectors.toList()).get(0);
//
//        resetPlayer(player);
//        if (getCurrentPlayer().getName().equals(name))
//            GameLogic.getInstance().finishTurn();
//
//        playerList.removeIf(p -> p.getName().equals(name));
//        players.remove(player.getName());
//
//        System.out.println("\n\n===========================\n" +
//                playerList + "\n" +
//                players + "\n");
//
//
//    }
//
//
//    private void resetPlayer(Player player) {
//        //remove Buildings
//        player.getOwnedProperties().forEach(x -> x.setBuildingList(new ArrayList<>()));
//        player.getOwnedRailroads().forEach(x -> x.setHasDepot(false));
//
//        //reset owner
//        player.getOwnedProperties().forEach(x -> x.setOwner(null));
//        player.getOwnedRailroads().forEach(x -> x.setOwner(null));
//        player.getOwnedUtilities().forEach(x -> x.setOwner(null));
//
//    }
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
//    public Player getCurrentPlayer() {
//        return getPlayer(players.peekFirst());
//    }

}
