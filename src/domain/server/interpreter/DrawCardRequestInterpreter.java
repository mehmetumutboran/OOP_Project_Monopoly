package domain.server.interpreter;

import domain.server.controller.ServerCommunicationHandler;
import domain.server.util.ButtonStringGenerator;
import domain.util.Flags;

import java.io.DataInputStream;

public class DrawCardRequestInterpreter implements RequestInterpretable {
    @Override
    public void interpret(DataInputStream dis, String[] message, int index) {
        String name = message[1];

        ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Button"), index, "000010000", name);
    }
}
