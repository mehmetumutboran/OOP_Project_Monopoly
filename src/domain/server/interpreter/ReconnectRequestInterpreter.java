package domain.server.interpreter;

import domain.server.controller.ServerCommunicationHandler;
import domain.util.Flags;
import domain.util.GameInfo;
import network.client.clientFacade.ClientFacade;
import network.server.serverFacade.ServerFacade;

public class ReconnectRequestInterpreter implements RequestInterpretable {
    @Override
    public void interpret(String[] message, int index) {
        String name = message[1];

        if (GameInfo.getInstance().hasPlayer(name))
            ServerFacade.getInstance().setClientInfo(name);

        String oldHostName = ClientFacade.getInstance().getOldHostName();
        ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Reconnect"),index,name,oldHostName);
    }
}
