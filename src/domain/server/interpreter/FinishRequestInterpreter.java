package domain.server.interpreter;

import domain.server.ReceivedChecker;
import domain.server.controller.ServerCommunicationHandler;
import domain.util.Flags;
import domain.util.GameInfo;
import network.server.serverFacade.ServerFacade;

public class FinishRequestInterpreter implements RequestInterpretable {
    @Override
    public void interpret(String[] message, int index) {
        String name = message[1];

        ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Finish"), name);

        ReceivedChecker.getInstance().check();

        String nextPlayer = GameInfo.getInstance().getCurrentPlayerName();
        if (!GameInfo.getInstance().isBot(nextPlayer))
            ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Button"), ServerFacade.getInstance().nameToIndex(nextPlayer), "001001110110", name);

        ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("DoubleCounter"), name, "0");
    }
}
