package domain.client.interpreter;

import domain.client.ClientCommunicationHandler;
import domain.util.GameInfo;
import domain.util.MessageConverter;

import java.util.Arrays;

public class InitQueueResponseInterpreter implements ResponseInterpretable {

    @Override
    public void interpret(String[] message) {
        System.out.println("\n\nInitQResponse" + Arrays.toString(message));
        GameInfo.getInstance().setPlayerQueue(MessageConverter.convertStringToDeque(message[1]));
        ClientCommunicationHandler.getInstance().sendReceived();
    }
}
