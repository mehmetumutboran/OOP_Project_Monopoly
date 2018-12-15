package domain.client.interpreter;

import domain.client.ClientCommunicationHandler;
import domain.client.UIUpdater;
import domain.util.GameInfo;
import network.client.clientFacade.ClientFacade;

/**
 * The class responsible for interpreting add player response from server.
 * This interpreter is for players who are already in the lobby to add new player to their list.
 */
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
