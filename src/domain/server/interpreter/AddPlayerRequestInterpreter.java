package domain.server.interpreter;

import domain.server.controller.ServerCommunicationHandler;
import domain.util.Flags;
import domain.util.GameInfo;
import network.server.serverFacade.ServerFacade;

public class AddPlayerRequestInterpreter implements RequestInterpretable {
    @Override
    public void interpret(String[] message, int index) {
        String name = message[1];

        if (GameInfo.getInstance().hasPlayer(name)) {
            ServerCommunicationHandler.getInstance()
                    .sendResponse(Flags.getFlag("Close"), index, name);
            return;
        }

        if (!GameInfo.getInstance().isListEmpty())
            ServerCommunicationHandler.getInstance()
                    .sendResponse(Flags.getFlag("AddPlayerList"), index, GameInfo.getInstance().getPlayersAsString());

        ServerCommunicationHandler.getInstance()
                .sendResponse(Flags.getFlag("AddPlayer"), name);

        ServerFacade.getInstance().setClientInfo(name);

    }
}
