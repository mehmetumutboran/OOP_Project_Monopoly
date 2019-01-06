package domain.client.interpreter;

import domain.client.UIUpdater;

public class ChangeOneButtonResponseInterpreter implements ResponseInterpretable {
    @Override
    public void interpret(String[] message) {
        int index = Integer.parseInt(message[1]);
        String val = message[2];

        UIUpdater.getInstance().changeButton(index, val);
    }
}
