package domain.client.interpreter;

import domain.server.util.GameInfo;

public class ColorChangeResponseInterpreter implements ResponseInterpretable {
    @Override
    public void interpret(String[] message) {
        String name = message[1];
        String color = message[2];
        GameInfo.getInstance().setPlayerColor(name, color);
    }
}
