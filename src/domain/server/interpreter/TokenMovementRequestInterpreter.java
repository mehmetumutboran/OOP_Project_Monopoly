package domain.server.interpreter;

import domain.client.UIUpdater;
import domain.util.MessageConverter;

import java.io.DataInputStream;

public class TokenMovementRequestInterpreter implements RequestInterpretable {
    @Override
    public void interpret(DataInputStream dis, String[] message, int index) {
        String name = message[1];
        int[] newLoc = MessageConverter.convertStringToIntArray(message[2]);
        UIUpdater.getInstance().setTokenLocation(name, newLoc[0], newLoc[1]);

    }
}
