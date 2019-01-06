package domain.server.interpreter;

import domain.server.board.Board;
import domain.server.board.Upgradable;
import domain.server.controller.ServerCommunicationHandler;
import domain.util.Flags;
import domain.util.MessageConverter;

public class MortgageRequestInterpreter implements RequestInterpretable {
    @Override
    public void interpret(String[] message, int index) {
        String name = message[1];
        int[] location = MessageConverter.convertStringToIntArray(message[2], ',');

        if (((Upgradable) Board.getInstance().getSquare(location[0], location[1])).hasUpgradedSquareInGroup()) {

            ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("DontMortgage"), index, name, MessageConverter.convertArrayToString(location));

        } else {

            ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Mortgage"), name,
                    Board.getInstance().getSquare(location[0], location[1]).getName());

        }

    }
}
