package domain.server.interpreter;

import domain.server.controller.ServerCommunicationHandler;
import domain.util.Flags;

public class DrawCardRequestInterpreter implements RequestInterpretable {
    @Override
    public void interpret(String[] message, int index) {
        String name = message[1];

        ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Button"), index, "000010000", name);
    }
}
