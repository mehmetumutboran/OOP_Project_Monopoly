package domain.client.interpreter;

import domain.client.ClientCommunicationHandler;
import domain.client.UIUpdater;
import domain.server.player.Player;
import domain.util.GameInfo;

public class MoneyChangeResponseInterpreter implements ResponseInterpretable {

    /**
     * This method changes the given player's balance by the given changed money.
     * @param message String array of the form [Flag, args...] Generated in {@link domain.server.util.GameState}
     * args include player's name and changed money.
     */
    @Override
    public void interpret(String[] message) {
        // @requires: message!=null
        // @effects: Changes player's balance field.
        String name = message[1];
        int changedMoney = Integer.parseInt(message[2].substring(message[2].indexOf("@")+1));

        Player player = GameInfo.getInstance().getPlayer(name);

        player.increaseMoney(changedMoney);

        if(message[2].substring(0,message[2].indexOf("@")).equals("Go")) UIUpdater.getInstance().setMessage(name + " passed Go Square and his/her money increased by " + changedMoney);
        else if(message[2].substring(0,message[2].indexOf("@")).equals("BonusP")) UIUpdater.getInstance().setMessage(name + " passed Bonus Square and his/her money increased by " + changedMoney);
        else if(message[2].substring(0,message[2].indexOf("@")).equals("BonusL")) UIUpdater.getInstance().setMessage(name + " landed Bonus Square and his/her money increased by " + changedMoney);
        else if (changedMoney > 0) UIUpdater.getInstance().setMessage(name + " 's money increased by " + changedMoney);
        else UIUpdater.getInstance().setMessage(name + " 's money decreased by " + (-changedMoney));
        ClientCommunicationHandler.getInstance().sendReceived();

    }
}
