package domain.server.interpreter;

import domain.server.ReceivedChecker;
import domain.server.board.Board;
import domain.server.controller.ServerCommunicationHandler;
import domain.server.die.DiceCup;
import domain.server.move.MoveControl;
import domain.server.util.ButtonStringGenerator;
import domain.util.Flags;
import domain.util.GameInfo;
import domain.util.MessageConverter;

public class RollRequestInterpreter implements RequestInterpretable {
    @Override
    public void interpret(String[] message, int index) {
        System.out.println("\n\nRollResponseInterpreter: interpret\n\n");

        String name = message[1];

        int[] rolled = roll(name);

        ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Roll"), name, MessageConverter.convertArrayToString(rolled));

        ReceivedChecker.getInstance().check();

        MoveControl.getInstance().updateDoubleCounter(name);

        String newLoc = null;

        if (MoveControl.getInstance().checkMoveConditions(name)) {

            newLoc = MoveControl.getInstance().move(name);

            String locName = Board.getInstance().getSquare(MessageConverter.convertStringToIntArray(newLoc, ',')[0], MessageConverter.convertStringToIntArray(newLoc, ',')[1]).getName();

            String loc = newLoc + "@" + locName;

            ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Move"), name, loc, 0);

            ReceivedChecker.getInstance().check();
        }
//       if(GameInfo.getInstance().getPlayer(GameInfo.getInstance().getCurrentPlayerName()).getReadiness().equals("Bot"))
//       { MoveControl.getInstance().checkMrMonopoly(name);}

        if (MoveControl.getInstance().checkSecondTurn(name)) {
            //ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Button"), name, "");
        }

        System.out.println(ButtonStringGenerator.getInstance().getButtonStringForNextSquare(name));

//        while (true) {
//            try {
//                String line = dis.readUTF();
//                if (line.charAt(0) == 'z') break;
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

        if (!GameInfo.getInstance().isBot(name)) {
            ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Token"), name, newLoc);
            String layout = ButtonStringGenerator.getInstance().getButtonStringForNextSquare(name);
            System.out.println(layout);
            ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Button"), index, layout, name);
        }
    }

    /**
     * This method calls the roll method of {@link DiceCup} to roll the dice for given player.
     *
     * @param name The name of the player that rolls the dice.
     * @return int array that includes faces of rolled dice.
     */
    public int[] roll(String name) {
        //TODO check if the player can roll
        System.out.println("\n\nGameLogic: roll\n\n");
        int[] loc = GameInfo.getInstance().getPlayer(name).getToken().getLocation();
        String locName = Board.getInstance().getSquare(loc[0], loc[1]).getName();
        int[] currentDice = DiceCup.getInstance().rollDice(locName);
        return currentDice;
    }
}
