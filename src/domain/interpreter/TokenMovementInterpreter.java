package domain.interpreter;

import domain.MessageConverter;
import domain.UIUpdater;

public class TokenMovementInterpreter implements Interpreter {
    @Override
    public void interpret(String[] message) {
        String name = message[1];
        int[] newLoc = MessageConverter.convertStringToIntArray(message[2]);
        UIUpdater.getInstance().setTokenLocation(name, newLoc[0], newLoc[1]);

    }
}
