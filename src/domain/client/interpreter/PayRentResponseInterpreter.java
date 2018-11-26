package domain.client.interpreter;

import domain.client.UIUpdater;
import domain.server.GameLogic;
import domain.server.board.Board;
import domain.server.board.DeedSquare;
import domain.server.player.Player;

public class PayRentResponseInterpreter implements ResponseInterpretable {
    @Override
    public void interpret(String[] message) {
        String name = message[1];
        String sqName = message[2];

        Player player = GameLogic.getInstance().getPlayer(name);
        DeedSquare square = (DeedSquare) Board.getInstance().getNameGivenSquare(sqName);

        player.decreaseMoney(square.getRent());

        square.getOwner().increaseMoney(square.getRent());
        UIUpdater.getInstance().setMessage(name + " paid rent " + square.getRent() + " dollars to " + square.getOwner().getName());


    }
}
