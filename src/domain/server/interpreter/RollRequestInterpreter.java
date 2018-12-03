package domain.server.interpreter;

import domain.server.GameLogic;
import domain.server.controller.ServerCommunicationHandler;
import domain.util.Flags;

public class RollRequestInterpreter implements RequestInterpretable {
    @Override
    public void interpret(String[] message, int index) {
        System.out.println("\n\nRollResponseInterpreter: interpret\n\n");

        String name = message[1];

        GameLogic.getInstance().roll(name);

        ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Button"), "000010000");
    }
}
