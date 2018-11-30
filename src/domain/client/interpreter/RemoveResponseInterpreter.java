package domain.client.interpreter;

import domain.util.GameInfo;

public class RemoveResponseInterpreter implements ResponseInterpretable {
    @Override
    public void interpret(String[] message) {
        String username = message[1];
        GameInfo.getInstance().removePlayer(username);
    }
}
