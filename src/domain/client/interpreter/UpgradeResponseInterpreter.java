package domain.client.interpreter;

import domain.client.ClientCommunicationHandler;
import domain.client.UIUpdater;
import domain.server.board.*;
import domain.server.building.Building;
import domain.server.building.Hotel;
import domain.server.building.House;
import domain.server.building.Skyscraper;
import domain.server.player.Player;
import domain.util.GameInfo;

import java.nio.channels.SeekableByteChannel;
import java.util.ArrayList;

public class UpgradeResponseInterpreter implements ResponseInterpretable {
    @Override
    public void interpret(String[] message) {

        Player currentPlayer = GameInfo.getInstance().getPlayer(message[1]);
        Upgradable selectedSquareForUpgrade = (Upgradable) Board.getInstance().getNameGivenSquare(message[2]);

        selectedSquareForUpgrade.upgrade();

//        Building buildingtoUpgrade = null ;
//        if(selectedSquareForUpgrade instanceof Property) {
//            if (message[3].equals("Skyscrapper")) {
//                buildingtoUpgrade = new Skyscraper(((Property) selectedSquareForUpgrade).getHouseBuildingCost());
//                ((Property) selectedSquareForUpgrade).getBuildingList().remove(0);
//                ((Property) selectedSquareForUpgrade).getBuildingList().add(buildingtoUpgrade);
//            } else if (message[3].equals("Hotel")) {
//                buildingtoUpgrade = new Hotel(((Property) selectedSquareForUpgrade).getHouseBuildingCost());
//                ((Property) selectedSquareForUpgrade).getBuildingList().clear();
//                ((Property) selectedSquareForUpgrade).getBuildingList().add(buildingtoUpgrade);
//            } else if (message[3].equals("House")) {
//                buildingtoUpgrade = new House(((Property) selectedSquareForUpgrade).getHouseBuildingCost());
//                ((Property) selectedSquareForUpgrade).getBuildingList().add(buildingtoUpgrade);
//            }
//
//            ((Property) selectedSquareForUpgrade).setUpgraded(true);
//            ((Property) selectedSquareForUpgrade).updateRent();
//
//        }
//        else {//means that square is a Railroad
//            ((Railroad)selectedSquareForUpgrade).setHasDepot(true);
//            ((Railroad)selectedSquareForUpgrade).updateRentInUpDownGrade("UP");
//        }
        currentPlayer.decreaseMoney(selectedSquareForUpgrade.getBuildingCost());

        ArrayList<int[]> locationList = new ArrayList<>();
        for(Square s : currentPlayer.getOwnedProperties()){
            locationList.add(s.getLocation());
        }

        for(Square s : currentPlayer.getOwnedRailroads()){
            locationList.add(s.getLocation());
        }


        /////Skyscrapper oldugunda hata var
        UIUpdater.getInstance().setMessage(currentPlayer.getName() + " upgraded " + ((Square) selectedSquareForUpgrade).getName()
                + " to " + message[3]);

        UIUpdater.getInstance().updateLabels(locationList, "UP", 0);

         ClientCommunicationHandler.getInstance().sendReceived();


    }
}
