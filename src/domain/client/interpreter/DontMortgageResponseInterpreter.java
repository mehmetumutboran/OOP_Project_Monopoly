package domain.client.interpreter;

import domain.client.ClientCommunicationHandler;
import domain.client.UIUpdater;

public class DontMortgageResponseInterpreter implements ResponseInterpretable {
    @Override
    public void interpret(String[] message) {
        int count = Integer.parseInt(message[1]);
        UIUpdater.getInstance().showPrompt(message[0].charAt(0), count);
        ClientCommunicationHandler.getInstance().sendReceived();
    }
}
