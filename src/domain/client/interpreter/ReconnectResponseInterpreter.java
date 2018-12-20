package domain.client.interpreter;

import domain.client.ClientCommunicationHandler;
import domain.client.UIUpdater;
import domain.util.GameInfo;

public class ReconnectResponseInterpreter implements ResponseInterpretable {
    @Override
    public void interpret(String[] message) {
        String newHost = message[1];
        String oldHost = message[2];

        GameInfo.getInstance().removePlayer(oldHost);

        UIUpdater.getInstance().setMessage(oldHost + "'s connection failed!, "+newHost+" is now the new host!");

        UIUpdater.getInstance().removeUpdate(oldHost);

        ClientCommunicationHandler.getInstance().sendReceived();
    }
}
