package domain.server.interpreter;

import domain.server.controller.ServerCommunicationHandler;
import domain.util.Flags;

public class SaveRequestInterpreter implements RequestInterpretable {
    @Override
    public void interpret(String[] message, int index) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Game Saved");
        ServerCommunicationHandler.getInstance().
                sendResponse(Flags.getFlag("Save"), stringBuilder.toString());
    }
}
