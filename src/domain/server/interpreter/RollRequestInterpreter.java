package domain.server.interpreter;

import domain.server.GameLogic;
import domain.server.controller.ServerCommunicationHandler;
import domain.util.Flags;
import domain.util.MessageConverter;

public class RollRequestInterpreter implements RequestInterpretable {
    @Override
    public void interpret(String[] message, int index) {
        System.out.println("\n\nRollResponseInterpreter: interpret\n\n");

        String name = message[1];

        int [] rolled = GameLogic.getInstance().roll(name);

        //boolean isFromJail = GameLogic.getInstance().roll(name);

        ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Roll"), name, MessageConverter.convertArrayToString(rolled));

        //GameLogic.getInstance().move(isFromJail);

        ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Move"), name);

    }
}
