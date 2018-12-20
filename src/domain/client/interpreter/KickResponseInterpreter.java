package domain.client.interpreter;

import domain.client.UIUpdater;
import domain.util.GameInfo;
import network.client.clientFacade.ClientFacade;

public class KickResponseInterpreter implements ResponseInterpretable {
    @Override
    public void interpret(String[] message) {
        ClientFacade.getInstance().terminate();
        GameInfo.getInstance().reset();
        UIUpdater.getInstance().changePanel("Join");
        UIUpdater.getInstance().showPrompt(message[0].charAt(0));
    }
}
