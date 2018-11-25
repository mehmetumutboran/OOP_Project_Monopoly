package domain.interpreter;

import domain.GameLogic;
import domain.UIUpdater;
import domain.board.Board;
import domain.board.DeedSquare;
import domain.player.Player;

public class PayRentInterpreter implements Interpreter {
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
