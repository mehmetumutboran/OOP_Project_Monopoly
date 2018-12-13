package domain.server.interpreter;

import domain.server.GameLogic;
import domain.server.board.Board;
import domain.server.board.Property;
import domain.server.board.Railroad;
import domain.server.board.Square;
import domain.server.player.Player;
import domain.util.GameInfo;
import domain.util.MessageConverter;

public class DowngradeRequestInterpreter implements RequestInterpretable {
    @Override
    public void interpret(String[] message, int index) {

        Player currentPlayer = GameInfo.getInstance().getCurrentPlayer();
        int [] loc = MessageConverter.convertStringToIntArray(message[2], ',');
        Square square = Board.getInstance().getSquare(loc[0],loc[1]);

        if(square instanceof Railroad && ((Railroad) square).isUpgraded()){
            GameLogic.getInstance().applyRailDowngrade(square,currentPlayer);

        }else if (square instanceof Property && (((Property) square).isUpgraded()) && ((Property) square).isDowngradable((Property)square)){
            GameLogic.getInstance().applydowngrade(square,currentPlayer);

        }else{
            //fix this later
           // ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("DontDowngrade"), index, " ");
        }
    }
}
