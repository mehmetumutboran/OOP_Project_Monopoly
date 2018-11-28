package domain.server.interpreter;

import domain.server.GameLogic;
import domain.client.UIUpdater;
import domain.server.board.Board;
import domain.server.board.DeedSquare;
import domain.server.player.Player;

public class PayRentRequestInterpreter implements RequestInterpretable {
    @Override
    public void interpret(String[] message, int index) {
        String name = message[1];
        String sqName = message[2];

        Player player = GameLogic.getInstance().getPlayer(name);
        DeedSquare square = (DeedSquare) Board.getInstance().getNameGivenSquare(sqName);

        player.decreaseMoney(square.getRent());

        square.getOwner().increaseMoney(square.getRent());
        UIUpdater.getInstance().setMessage(name + " paid rent " + square.getRent() + " dollars to " + square.getOwner().getName());


    }
}
