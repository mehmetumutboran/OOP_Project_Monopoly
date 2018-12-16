package domain.server.interpreter;

import domain.server.board.*;
import domain.server.controller.ServerCommunicationHandler;
import domain.server.player.Player;
import domain.util.Flags;
import domain.util.GameInfo;
import domain.util.MessageConverter;

public class DowngradeRequestInterpreter implements RequestInterpretable {
    @Override
    public void interpret(String[] message, int index) {
        String name = message[1];
        Player currentPlayer = GameInfo.getInstance().getCurrentPlayer();
        int[] loc = MessageConverter.convertStringToIntArray(message[2], ',');
        Upgradable square = (Upgradable) Board.getInstance().getSquare(loc[0], loc[1]);

        String buildingtoDowngradeFrom;
        if(square.getUpgradeLevel()==7){
            buildingtoDowngradeFrom="TrainDepot";
        }
        else if(square.getUpgradeLevel()==6){
            buildingtoDowngradeFrom="Skyscraper";
        }else if(square.getUpgradeLevel()==5){
            buildingtoDowngradeFrom="Hotel";
        }else{
            buildingtoDowngradeFrom="House";
        }

        ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Downgrade"), currentPlayer.getName(), ((Square) square).getName(), buildingtoDowngradeFrom);


//        if(square instanceof Railroad && ((Railroad) square).isUpgraded()){
//            GameLogic.getInstance().applyRailDowngrade(square,currentPlayer);
//
//        }else if (square instanceof Property && (((Property) square).isUpgraded()) && ((Property) square).isDowngradable()){
//            GameLogic.getInstance().applydowngrade(square,currentPlayer);
//
//        }else{
//            //fix this later
//           // ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("DontDowngrade"), index, " ");
//        }
    }
}
