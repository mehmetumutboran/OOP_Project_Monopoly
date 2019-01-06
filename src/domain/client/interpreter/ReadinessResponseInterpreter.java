package domain.client.interpreter;

import domain.client.UIUpdater;
import domain.util.GameInfo;
import network.client.clientFacade.ClientFacade;

public class ReadinessResponseInterpreter implements ResponseInterpretable {
    @Override
    public void interpret(String[] message) {
        String username = message[1];
        GameInfo.getInstance().setReadiness(username);
        if (ClientFacade.getInstance().getUsername().equals(username))
            UIUpdater.getInstance().changeReadiness(GameInfo.getInstance().isReady(username));
    }
}
