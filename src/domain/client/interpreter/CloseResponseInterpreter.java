package domain.client.interpreter;

import domain.client.ClientCommunicationHandler;
import domain.client.UIUpdater;
import domain.util.GameInfo;
import network.client.clientFacade.ClientFacade;

public class CloseResponseInterpreter implements ResponseInterpretable {
    @Override
    public void interpret(String[] message) {
        UIUpdater.getInstance().showPrompt(message[0].charAt(0));
        ClientCommunicationHandler.getInstance().sendReceived();
        ClientFacade.getInstance().terminate();
    }
}
