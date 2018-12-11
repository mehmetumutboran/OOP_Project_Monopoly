package domain.server.interpreter;

import domain.server.controller.ServerCommunicationHandler;
import domain.util.Flags;

public class PauseRequestInterpreter implements RequestInterpretable {
    @Override
    public void interpret(String[] message, int index) {
        String username = message[1];
        ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Pause"),username);
    }
}
