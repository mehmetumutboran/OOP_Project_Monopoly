package domain.server.interpreter;

import domain.server.controller.ServerCommunicationHandler;
import domain.util.Flags;

import java.io.DataInputStream;

public class ReadinessRequestInterpreter implements RequestInterpretable {
    @Override
    public void interpret(DataInputStream dis, String[] message, int index) {
        String username = message[1];

        ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Readiness"), username);
    }
}
