package domain.server.interpreter;

import domain.server.controller.ServerCommunicationHandler;
import domain.util.Flags;
import domain.util.GameInfo;

public class DrawCardRequestInterpreter implements RequestInterpretable {
    @Override
    public void interpret(String[] message, int index) {
        String name = message[1];

        if (GameInfo.getInstance().getPlayer(name).getFaceValues()[2] != 7)
            ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Button"), index, "000010000110", name);
        else
            ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Button"), index, "000010000111", name);

    }
}
