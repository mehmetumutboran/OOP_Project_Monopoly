package domain.client.interpreter;

import domain.client.UIUpdater;
import domain.util.Flags;
import domain.util.GameInfo;

public class JailResponseInterpreter implements ResponseInterpretable {
    /**
     * Tjis method takes a player name. Then gets this player from name. And sends him/her to jail or release from jail coresponding to the flag of the message.
     * @param message String array of the form [Flag, args...] Generated in {@link domain.server.util.GameState}.
     * args include the name of the player which goes to jail or go out of jail.
     */
    @Override
    public void interpret(String[] message) {
        if(message[0].charAt(0) == Flags.getFlag("GoToJail")){
            GameInfo.getInstance().getPlayer(message[1]).setInJail(true);
            UIUpdater.getInstance().setMessage(message[1] + " goes to jail!!!");
        }else if (message[0].charAt(0) == Flags.getFlag("GoOutOfJail")){
            GameInfo.getInstance().getPlayer(message[1]).setInJail(false);
            UIUpdater.getInstance().setMessage(message[1] + " is out of jail!!!");
        }
    }
}
