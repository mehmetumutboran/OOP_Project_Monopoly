package domain.client.interpreter;

import domain.client.ClientCommunicationHandler;
import domain.server.player.RandomPlayer;
import domain.util.GameInfo;

/**
 * The class responsible for interpreting Bot add response from server.
 */
public class AddBotResponseInterpreter implements ResponseInterpretable {
    @Override
    public void interpret(String[] message) {
        String[] arr = message[1].split("[,]");
        RandomPlayer randomPlayer = new RandomPlayer(arr[0],arr[2]);
        randomPlayer.getToken().setColor(arr[1]);

        GameInfo.getInstance().addPlayer(randomPlayer);

        ClientCommunicationHandler.getInstance().sendReceived();

    }
}
