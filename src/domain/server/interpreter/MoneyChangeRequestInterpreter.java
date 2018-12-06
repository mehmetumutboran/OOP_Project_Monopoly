package domain.server.interpreter;

import java.io.DataInputStream;

public class MoneyChangeRequestInterpreter implements RequestInterpretable {
    @Override
    public void interpret(DataInputStream dis, String[] message, int index) {
//        String name = message[1];
//        int changedMoney = Integer.parseInt(message[2]);
//
//        Player player = GameLogic.getInstance().getPlayer(name);
//
//        player.increaseMoney(changedMoney);
//
//        if (changedMoney > 0) UIUpdater.getInstance().setMessage(name + " 's money increased by " + changedMoney);
//        else UIUpdater.getInstance().setMessage(name + " 's money decreased by " + (-changedMoney));
    }
}
