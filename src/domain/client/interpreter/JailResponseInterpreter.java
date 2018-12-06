package domain.client.interpreter;

import domain.client.UIUpdater;
import domain.util.Flags;
import domain.util.GameInfo;

public class JailResponseInterpreter implements ResponseInterpretable {

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
