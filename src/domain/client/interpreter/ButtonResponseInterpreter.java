package domain.client.interpreter;

import domain.client.ClientCommunicationHandler;
import domain.client.UIUpdater;

/**
 * The class responsible for interpreting Button response from server.
 */
public class ButtonResponseInterpreter implements ResponseInterpretable {
    @Override
    public void interpret(String[] message) {
        String enable = message[1];

        UIUpdater.getInstance().setButtons(enable);

        ClientCommunicationHandler.getInstance().sendReceived();

    }
}
