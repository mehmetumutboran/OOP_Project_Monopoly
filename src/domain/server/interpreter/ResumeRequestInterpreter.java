package domain.server.interpreter;

import domain.server.controller.ServerCommunicationHandler;
import domain.util.Flags;

public class ResumeRequestInterpreter implements RequestInterpretable {
    @Override
    public void interpret(String[] message, int index) {
        String username = message[1];
        System.out.println("ResumeRequestInterpreter");
        ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Resume"), username);
    }
}
