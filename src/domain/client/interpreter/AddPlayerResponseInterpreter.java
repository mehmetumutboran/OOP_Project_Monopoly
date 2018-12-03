package domain.client.interpreter;

import domain.client.ClientCommunicationHandler;
import domain.client.UIUpdater;
import domain.util.GameInfo;
import network.client.clientFacade.ClientFacade;

public class AddPlayerResponseInterpreter implements ResponseInterpretable {
    @Override
    public void interpret(String[] message) {
        String username = message[1];
        UIUpdater.getInstance().changePanel("Lobby");
        if(ClientFacade.getInstance().getUsername().equals(username))
            UIUpdater.getInstance().setTitle(username);
        GameInfo.getInstance().addPlayer(username);

        ClientCommunicationHandler.getInstance().sendReceived();

    }
}
