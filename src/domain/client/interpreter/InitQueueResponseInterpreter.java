package domain.client.interpreter;

import domain.util.GameInfo;
import domain.util.MessageConverter;

public class InitQueueResponseInterpreter implements ResponseInterpretable {

    @Override
    public void interpret(String[] message) {
        GameInfo.getInstance().setPlayerQueue(MessageConverter.convertStringToDeque(message[1]));
        System.out.println("In init queue res int" + MessageConverter.convertQueueToString(GameInfo.getInstance().getPlayerQueue()));
    }
}
