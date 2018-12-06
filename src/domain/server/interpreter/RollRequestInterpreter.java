package domain.server.interpreter;

import domain.server.GameLogic;
import domain.server.board.Board;
import domain.server.controller.ServerCommunicationHandler;
import domain.util.Flags;
import domain.util.MessageConverter;

public class RollRequestInterpreter implements RequestInterpretable {
    @Override
    public void interpret(String[] message, int index) {
        System.out.println("\n\nRollResponseInterpreter: interpret\n\n");

        String name = message[1];

        int [] rolled = GameLogic.getInstance().roll(name);

        ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Roll"), name, MessageConverter.convertArrayToString(rolled));

        GameLogic.getInstance().uptadeDoubleCounter(name);

        if (GameLogic.getInstance().checkMoveConditions(name)) {

            String newLoc = GameLogic.getInstance().move(name);

            String locName = Board.getInstance().getSquare(MessageConverter.convertStringToIntArray(newLoc)[0], MessageConverter.convertStringToIntArray(newLoc)[1]).getName();

            String loc = newLoc + "@" + locName;

            ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Move"), name, loc);
        }

        GameLogic.getInstance().checkMrMonopoly(name);

        if(GameLogic.getInstance().checkSecondTurn(name)){
            //ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Button"), name, "");
        }

    }
}
