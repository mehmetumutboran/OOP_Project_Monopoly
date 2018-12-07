package domain.server.interpreter;

import domain.server.controller.ServerCommunicationHandler;
import domain.util.Flags;
import domain.util.GameInfo;

public class ColorChangeRequestInterpreter implements RequestInterpretable {
    @Override
    public void interpret(String[] message, int index) {
        String name = message[1];
        String color = message[2];
        if (GameInfo.getInstance().hasColor(color)) {
            ServerCommunicationHandler.getInstance()
                    .sendResponse(Flags.getFlag("DontChangeColor"), index, name);
            return;
        }

        ServerCommunicationHandler.getInstance()
                .sendResponse(Flags.getFlag("Color"), name, color);
    }
}
