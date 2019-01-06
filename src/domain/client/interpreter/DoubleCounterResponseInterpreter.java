package domain.client.interpreter;

import domain.client.ClientCommunicationHandler;
import domain.util.GameInfo;

public class DoubleCounterResponseInterpreter implements ResponseInterpretable {

    /**
     * This method resets or increments given player's double counter field corresponding to message.
     *
     * @param message String array of the form [Flag, args...] Generated in {@link domain.server.util.GameState}
     */
    @Override
    public void interpret(String[] message) {
        // @requires: message!=null
        // @effects: Changes the double counter field of player.
        if (message[2].equals("1")) GameInfo.getInstance().getPlayer(message[1]).incrementDoubleCounter();
        else if (message[2].equals("0")) GameInfo.getInstance().getPlayer(message[1]).resetDoubleCounter();

        ClientCommunicationHandler.getInstance().sendReceived();
    }
}
