package domain.server.interpreter;

import domain.server.board.Board;
import domain.server.board.Property;
import domain.server.board.Square;
import domain.server.board.Upgradable;
import domain.server.controller.ServerCommunicationHandler;
import domain.server.player.Player;
import domain.util.Flags;
import domain.util.GameInfo;
import domain.util.MessageConverter;

public class UpgradeRequestInterpreter implements RequestInterpretable {
    @Override
    public void interpret(String[] message, int index) {
        String name = message[1];
        Player currentPlayer = GameInfo.getInstance().getCurrentPlayer();
        int[] loc = MessageConverter.convertStringToIntArray(message[2], ',');
        Upgradable square = (Upgradable) Board.getInstance().getSquare(loc[0], loc[1]);


        String typeOfUpgrade;

        if (square.getUpgradeLevel() == 5) {
            if (GameInfo.getInstance().getPlayer(name).checkMonopoly((Property) square)) {
                typeOfUpgrade = "Skyscraper";
            } else {
                //TODO optionPane
                return;
            }
        } else if (square.getUpgradeLevel() == 4) typeOfUpgrade = "Hotel";
        else if (square.getUpgradeLevel() == 7) typeOfUpgrade = "TrainDepot";
        else typeOfUpgrade = "House";

        if(currentPlayer.getBalance()>=square.getBuildingCost())
            ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Upgrade"), currentPlayer.getName(), ((Square) square).getName(), typeOfUpgrade);
        else {
            //OptionPane
        }

//
//        if (square instanceof Railroad && !square.isUpgraded() && currentPlayer.getBalance() >= ((Railroad) square).getHouseBuildingCost()) {
//            GameLogic.getInstance().applyRailRoadUpgrade(square, currentPlayer);
//        } else if (square instanceof Property && currentPlayer.checkMajority((Property) square)
//                && square.isUpgradable() && currentPlayer.getBalance() >= ((Property) square).getHouseBuildingCost()) {
//            GameLogic.getInstance().applyPropertyUpgrade(square, index, currentPlayer);
//        } else {
//            //fix this later
//            //ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("DontUpgrade"), index, " ");
//        }


    }


}
