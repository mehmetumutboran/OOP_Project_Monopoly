package domain.server.interpreter;

import domain.server.ReceivedChecker;
import domain.server.board.Board;
import domain.server.board.DeedSquare;
import domain.server.board.Property;
import domain.server.controller.ServerCommunicationHandler;
import domain.util.Flags;
import domain.util.MessageConverter;

public class ColorDowngradeRequestInterpreter implements RequestInterpretable {
    @Override
    public void interpret(String[] message, int index) {
        int[] location = MessageConverter.convertStringToIntArray(message[1], ',');

        String name = ((DeedSquare) Board.getInstance().getSquare(location[0], location[1])).getOwner();

        Property[] sameColoredSquares = Board.getInstance().getSameColoredSquares(((Property) Board.getInstance().getSquare(location[0], location[1])).getColor());
        for (Property p : sameColoredSquares) {


            if (p.getOwner() != null && p.getOwner().equals(name)) {
                System.out.println(p.getName());
                String buildingtoDowngradeFrom = null;
                if (p.getUpgradeLevel() == 7) {
                    buildingtoDowngradeFrom = "TrainDepot";
                } else if (p.getUpgradeLevel() == 6) {
                    buildingtoDowngradeFrom = "Skyscraper";
                } else if (p.getUpgradeLevel() == 5) {
                    buildingtoDowngradeFrom = "Hotel";
                } else if (p.getUpgradeLevel() > 0) {
                    buildingtoDowngradeFrom = "House";
                }

                if (buildingtoDowngradeFrom != null) {
                    ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Downgrade"), name, p.getName(), buildingtoDowngradeFrom);
                    while (true) {
                        if (ReceivedChecker.getInstance().checkReceived()) {
                            ReceivedChecker.getInstance().setReceived();
                            break;
                        }
                    }
                }
            }
        }
    }
}
