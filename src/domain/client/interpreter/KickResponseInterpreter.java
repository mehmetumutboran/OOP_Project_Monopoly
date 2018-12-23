package domain.client.interpreter;

import domain.client.UIUpdater;
import network.client.clientFacade.ClientFacade;

public class KickResponseInterpreter implements ResponseInterpretable {
    @Override
    public void interpret(String[] message) {
        ClientFacade.getInstance().terminate();
        UIUpdater.getInstance().changePanel("Join");
        UIUpdater.getInstance().publishReadinessChangedEvent(false);
        UIUpdater.getInstance().showPrompt(message[0].charAt(0));
    }
}
