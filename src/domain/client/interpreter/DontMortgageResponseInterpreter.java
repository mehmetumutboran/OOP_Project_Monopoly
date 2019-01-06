package domain.client.interpreter;

import domain.client.ClientCommunicationHandler;
import domain.client.UIUpdater;
import domain.util.MessageConverter;

public class DontMortgageResponseInterpreter implements ResponseInterpretable {
    @Override
    public void interpret(String[] message) {
        UIUpdater.getInstance().showPrompt(message[0].charAt(0), MessageConverter.convertStringToIntArray(message[2], ','));
        ClientCommunicationHandler.getInstance().sendReceived();
    }
}
