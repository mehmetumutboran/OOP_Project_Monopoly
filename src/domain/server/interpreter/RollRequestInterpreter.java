package domain.server.interpreter;

import domain.server.GameLogic;
import domain.server.ReceivedChecker;
import domain.server.board.Board;
import domain.server.controller.ServerCommunicationHandler;
import domain.server.util.ButtonStringGenerator;
import domain.util.Flags;
import domain.util.GameInfo;
import domain.util.MessageConverter;

public class RollRequestInterpreter implements RequestInterpretable {
    @Override
    public void interpret(String[] message, int index) {
        System.out.println("\n\nRollResponseInterpreter: interpret\n\n");

        String name = message[1];

        int[] rolled = GameLogic.getInstance().roll(name);

        ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Roll"), name, MessageConverter.convertArrayToString(rolled));

        while (true) {
            if (ReceivedChecker.getInstance().checkReceived()) {
                ReceivedChecker.getInstance().setReceived();
                break;
            }
        }

        GameLogic.getInstance().updateDoubleCounter(name);

        if (GameLogic.getInstance().checkMoveConditions(name)) {

            String newLoc = GameLogic.getInstance().move(name);

            String locName = Board.getInstance().getSquare(MessageConverter.convertStringToIntArray(newLoc)[0], MessageConverter.convertStringToIntArray(newLoc)[1]).getName();

            String loc = newLoc + "@" + locName;

            ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Move"), name, loc);
        }

       if(GameInfo.getInstance().getPlayer(GameInfo.getInstance().getCurrentPlayer()).getReadiness().equals("Bot"))
       { GameLogic.getInstance().checkMrMonopoly(name);}

        if (GameLogic.getInstance().checkSecondTurn(name)) {
            //ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Button"), name, "");
        }

        System.out.println(ButtonStringGenerator.getInstance().getButtonString(name));

//        while (true) {
//            try {
//                String line = dis.readUTF();
//                if (line.charAt(0) == 'z') break;
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

        if (!GameInfo.getInstance().isBot(name))
            ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Button"), index, ButtonStringGenerator.getInstance().getButtonString(name), name);
    }
}
