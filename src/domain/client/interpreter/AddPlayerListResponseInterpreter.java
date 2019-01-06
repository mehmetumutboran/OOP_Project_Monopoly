package domain.client.interpreter;

import domain.client.ClientCommunicationHandler;
import domain.util.GameInfo;

/**
 * The class responsible for interpreting player List response from server.
 * This interpreter is for players who joined lobby to add existing players to their list.
 */
public class AddPlayerListResponseInterpreter implements ResponseInterpretable {
    @Override
    public void interpret(String[] message) {
        String[] arr;
        for (int i = 1; i < message.length; i++) {
            arr = message[i].split("[,]");
            if (arr[2].equals("Bot"))
                GameInfo.getInstance().addPlayer(arr[0], arr[1], arr[2], arr[3]);
            else
                GameInfo.getInstance().addPlayer(arr[0], arr[1], arr[2]);
        }

        ClientCommunicationHandler.getInstance().sendReceived();

    }
}
