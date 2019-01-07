package domain.client.interpreter;

import domain.client.ClientCommunicationHandler;
import domain.client.UIUpdater;
import domain.server.board.Board;
import domain.server.board.DeedSquare;
import domain.server.board.Square;
import domain.server.board.Upgradable;
import domain.server.player.Player;
import domain.util.GameInfo;

import java.util.ArrayList;

public class DowngradeResponseInterpreter implements ResponseInterpretable {

    @Override
    public void interpret(String[] message) {
        //@requires message[2]message[3] not null
        Player currentPlayer = GameInfo.getInstance().getPlayer(message[1]);
        Upgradable selectedSquareForDowngrade = (Upgradable) Board.getInstance().getNameGivenSquare(message[2]);
        selectedSquareForDowngrade.downgrade();
//        System.out.println("!!!!!!!!!!!!!!!!!!!================!!!!!!!!!!!!!!!==================!!!!!!!!!!!!!!!");
//        Player currentPlayer = GameInfo.getInstance().getPlayer(message[1]);
//        Square selectedSquareForDowngrade = Board.getInstance().getNameGivenSquare(message[2]);
//        Building buildingtoDowngrade = null ;
//
//        if(selectedSquareForDowngrade instanceof Property){
//            if(message[3].equals("Skyscrapper")){
//                buildingtoDowngrade = new Hotel(((Property) selectedSquareForDowngrade).getHouseBuildingCost());
//                ((Property) selectedSquareForDowngrade).getBuildingList().remove(0);
//                ((Property) selectedSquareForDowngrade).getBuildingList().add(buildingtoDowngrade);
//            }else if(message[3].equals("Hotel")){
//                ((Property) selectedSquareForDowngrade).getBuildingList().remove(0);
//                for (int i=0; i<4; i++){
//                    ((Property) selectedSquareForDowngrade).getBuildingList().add(new House(((Property) selectedSquareForDowngrade).getHouseBuildingCost()));
//                }
//            }else if(message[3].equals("House")){
//                ((Property) selectedSquareForDowngrade).getBuildingList().remove(((Property) selectedSquareForDowngrade).getBuildingList().size()-1);
//            }
//            ((Property) selectedSquareForDowngrade).updateRent();
//            if(((Property) selectedSquareForDowngrade).getBuildingList().isEmpty()){
//                ((Property) selectedSquareForDowngrade).setUpgraded(false);
//            }
//        }else {//square is type of railroad
//            ((Railroad) selectedSquareForDowngrade).setHasDepot(false);
//            ((Railroad) selectedSquareForDowngrade).updateRentInUpDownGrade("DOWN");
//        }
//
//        currentPlayer.increaseMoney(((DeedSquare) selectedSquareForDowngrade).getHouseBuildingCost()/2);
//
//        ArrayList<int[]> locationList = new ArrayList<>();
//        for(Square s : currentPlayer.getOwnedProperties()){
//            locationList.add(s.getLocation());
//        }
//
//        for(Square s : currentPlayer.getOwnedRailroads()){
//            locationList.add(s.getLocation());
//        }
//
//        UIUpdater.getInstance().updateLabels(locationList, "DOWN", 0);
//
//
//        UIUpdater.getInstance().setMessage(currentPlayer.getName() + " downgraded " +selectedSquareForDowngrade.getName()
//                + " from " + message[3]);
//
//         ClientCommunicationHandler.getInstance().sendReceived();
        if(((DeedSquare) selectedSquareForDowngrade).getOwner().equals(currentPlayer.getName()))
            currentPlayer.increaseMoney(selectedSquareForDowngrade.getBuildingCost() / 2);

        ArrayList<int[]> locationList = new ArrayList<>();
        for (Square s : currentPlayer.getOwnedProperties()) {
            locationList.add(s.getLocation());
        }

        for (Square s : currentPlayer.getOwnedRailroads()) {
            locationList.add(s.getLocation());
        }
        UIUpdater.getInstance().setMessage(currentPlayer.getName() + " downgraded " + ((Square) selectedSquareForDowngrade).getName()
                + " from " + message[3]);

        UIUpdater.getInstance().updateLabels(locationList, "DOWN", 0);

        ClientCommunicationHandler.getInstance().sendReceived();

    }
}

