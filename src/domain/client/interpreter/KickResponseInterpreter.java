package domain.client.interpreter;

import domain.client.ClientCommunicationHandler;
import domain.client.UIUpdater;
import domain.util.GameInfo;
import network.client.clientFacade.ClientFacade;

public class KickResponseInterpreter implements ResponseInterpretable {
    @Override
    public void interpret(String[] message) {
        ClientCommunicationHandler.getInstance().sendReceived();
        GameInfo.getInstance().getMyself().setReadiness("Not Ready");
        ClientFacade.getInstance().terminate();
        UIUpdater.getInstance().changePanel("Join");
        UIUpdater.getInstance().publishReadinessChangedEvent(false);
        UIUpdater.getInstance().showPrompt(message[0].charAt(0));
    }
}
