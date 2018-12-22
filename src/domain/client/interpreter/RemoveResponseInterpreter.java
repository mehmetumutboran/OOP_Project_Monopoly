package domain.client.interpreter;

import domain.client.UIUpdater;
import domain.util.GameInfo;
import network.client.clientFacade.ClientFacade;

public class RemoveResponseInterpreter implements ResponseInterpretable {
    @Override
    public void interpret(String[] message) {
        String username = message[1];
        //Lobby

        GameInfo.getInstance().removePlayer(username);

        UIUpdater.getInstance().removeUpdate(username);
    }
}
