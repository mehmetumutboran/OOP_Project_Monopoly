package domain.client.interpreter;

import domain.client.UIUpdater;

public class StartResponseInterpreter implements ResponseInterpretable {
    @Override
    public void interpret(String[] message) {
        UIUpdater.getInstance().changePanel("Game");
    }
}
