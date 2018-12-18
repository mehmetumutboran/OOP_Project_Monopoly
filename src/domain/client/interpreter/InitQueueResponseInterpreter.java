package domain.client.interpreter;

import domain.client.ClientCommunicationHandler;
import domain.client.UIUpdater;
import domain.util.GameInfo;
import domain.util.MessageConverter;

import java.util.ArrayList;
import java.util.Arrays;

public class InitQueueResponseInterpreter implements ResponseInterpretable {

    @Override
    public void interpret(String[] message) {
        System.out.println("\n\nInitQResponse" + Arrays.toString(message));
        GameInfo.getInstance().setPlayerQueue(MessageConverter.convertStringToDeque(message[1]));
        UIUpdater.getInstance().startTokens(new ArrayList<String>(MessageConverter.convertStringToDeque(message[1])));
        ClientCommunicationHandler.getInstance().sendReceived();
    }
}
