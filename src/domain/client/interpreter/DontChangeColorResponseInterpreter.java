package domain.client.interpreter;

import domain.client.UIUpdater;

public class DontChangeColorResponseInterpreter implements ResponseInterpretable {
    @Override
    public void interpret(String[] message) {
        UIUpdater.getInstance().showPrompt(message[0].charAt(0));
    }
}
