package domain.client.interpreter;

import domain.server.player.RandomPlayer;
import domain.server.util.GameInfo;

public class AddBotResponseInterpreter implements ResponseInterpretable {
    @Override
    public void interpret(String[] message) {
        String[] arr = message[1].split("[,]");
        RandomPlayer randomPlayer = new RandomPlayer(arr[0]);
        randomPlayer.getToken().setColor(arr[1]);

        GameInfo.getInstance().addPlayer(randomPlayer);
    }
}
