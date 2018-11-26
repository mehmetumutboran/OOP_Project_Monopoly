package domain.server.interpreter;

import domain.server.GameLogic;
import domain.client.UIUpdater;
import domain.server.player.Player;

public class MoneyChangeRequestInterpreter implements RequestInterpretable {
    @Override
    public void interpret(String[] message) {
        String name = message[1];
        int changedMoney = Integer.parseInt(message[2]);

        Player player = GameLogic.getInstance().getPlayer(name);

        player.increaseMoney(changedMoney);

        if (changedMoney > 0) UIUpdater.getInstance().setMessage(name + " 's money increased by " + changedMoney);
        else UIUpdater.getInstance().setMessage(name + " 's money decreased by " + (-changedMoney));
    }
}
