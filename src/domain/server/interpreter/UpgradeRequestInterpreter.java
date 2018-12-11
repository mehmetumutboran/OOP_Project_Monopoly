package domain.server.interpreter;

import domain.server.GameLogic;
import domain.server.board.Board;
import domain.server.board.Property;
import domain.server.board.Railroad;
import domain.server.board.Square;
import domain.server.controller.ServerCommunicationHandler;
import domain.server.player.Player;
import domain.util.Flags;
import domain.util.GameInfo;
import domain.util.MessageConverter;

public class UpgradeRequestInterpreter implements RequestInterpretable {
    @Override
    public void interpret(String[] message, int index) {
        Player currentPlayer = GameInfo.getInstance().getCurrentPlayer();
        int [] loc = MessageConverter.convertStringToIntArray(message[2], ',');
        Square square = Board.getInstance().getSquare(loc[0],loc[1]);

        if(square instanceof Railroad && !((Railroad) square).isHasDepot() && currentPlayer.getBalance()>= ((Railroad) square).getHouseBuildingCost()){
                GameLogic.getInstance().applyRailRoadUpgrade(square,currentPlayer);
        }else if(square instanceof Property && currentPlayer.checkMajority((Property)square)
                &&((Property) square).isUpgradable((Property)square) && currentPlayer.getBalance()>=((Property) square).getHouseBuildingCost()){
                GameLogic.getInstance().applyPropertyUpgrade(square,index,currentPlayer);
        }else{
            //fix this later
            ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("DontUpgrade"), index, " ");
        }


    }


}
