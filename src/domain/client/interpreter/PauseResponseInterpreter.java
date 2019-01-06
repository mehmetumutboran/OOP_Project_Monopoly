package domain.client.interpreter;

import domain.client.ClientCommunicationHandler;
import domain.client.UIUpdater;
import network.client.clientFacade.ClientFacade;

public class PauseResponseInterpreter implements ResponseInterpretable {
    @Override
    public void interpret(String[] message) {
        String username = message[1];
        UIUpdater.getInstance().pauseUpdate(username.equals(ClientFacade.getInstance().getUsername()),
                username);
        UIUpdater.getInstance().setAnimationPause(true);
        ClientCommunicationHandler.getInstance().sendReceived();
    }
}
