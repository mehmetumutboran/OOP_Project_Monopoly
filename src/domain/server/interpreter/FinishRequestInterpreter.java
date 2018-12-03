package domain.server.interpreter;

import domain.server.controller.ServerCommunicationHandler;
import domain.util.Flags;
import domain.util.GameInfo;
import network.server.serverFacade.ServerFacade;

public class FinishRequestInterpreter implements RequestInterpretable {
    @Override
    public void interpret(String[] message, int index) {
        String name = message[1];

        ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Finish"), name);

        ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Button"), ServerFacade.getInstance().nameToIndex(GameInfo.getInstance().getCurrentPlayer()), "000001000", name);
    }
}
