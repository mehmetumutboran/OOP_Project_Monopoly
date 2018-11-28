package domain.client.interpreter;

import domain.client.UIUpdater;

public class CloseResponseInterpreter implements ResponseInterpretable {
    @Override
    public void interpret(String[] message) {
        UIUpdater.getInstance().showPrompt(message[0].charAt(0));
    }
}
