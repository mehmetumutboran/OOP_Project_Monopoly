package domain.server.interpreter;

import domain.server.controller.ServerCommunicationHandler;
import domain.util.Flags;

public class HurricaneRequestInterpreter implements RequestInterpretable {
    @Override
    public void interpret(String[] message, int index) {
        String name = message[1];
        String input = message[2];
        if (input.equals("fail")) {
            ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Hurricane"), name, input);
            return;
        }

        ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Hurricane"), name, input);
    }
}
