package domain.client.interpreter;

import domain.client.UIUpdater;
import domain.util.GameInfo;

public class AddPlayerResponseInterpreter implements ResponseInterpretable {
    @Override
    public void interpret(String[] message) {
        UIUpdater.getInstance().changePanel("Lobby");
        GameInfo.getInstance().addPlayer(message[1]);
    }
}
