package domain.server.interpreter;

import domain.server.controller.ServerCommunicationHandler;
import domain.util.Flags;
import domain.util.GameInfo;
import network.server.serverFacade.ServerFacade;

public class RemoveRequestInterpreter implements RequestInterpretable {
    @Override
    public void interpret(String[] message, int index) {
        String username = message[1];

        if (!GameInfo.getInstance().isBot(username)) {
            ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Kick"),
                    ServerFacade.getInstance().nameToIndex(username), username);

            ServerFacade.getInstance().kick(username);
        }

        if (GameInfo.getInstance().isCurrentPlayer(username))
            ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Finish"), username);

        ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Remove"), username);
    }
}
