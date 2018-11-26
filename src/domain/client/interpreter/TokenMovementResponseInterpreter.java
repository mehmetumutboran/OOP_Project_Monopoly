package domain.client.interpreter;

import domain.client.UIUpdater;
import domain.util.MessageConverter;

public class TokenMovementResponseInterpreter implements ResponseInterpretable {
    @Override
    public void interpret(String[] message) {
        String name = message[1];
        int[] newLoc = MessageConverter.convertStringToIntArray(message[2]);
        UIUpdater.getInstance().setTokenLocation(name, newLoc[0], newLoc[1]);

    }
}
