package domain.interpreter;

import domain.GameLogic;
import domain.UIUpdater;
import domain.board.Board;
import domain.board.DeedSquare;
import domain.board.Square;
import domain.player.Player;

public class BuyInterpreter implements Interpreter {

    @Override
    public void interpret(String[] message) {
        String name = message[0];
        String sqName = message[1];

        Player player = GameLogic.getInstance().getPlayer(name);
        Square square = Board.getInstance().getNameGivenSquare(sqName);

        player.decreaseMoney(((DeedSquare) square).getBuyValue());
        player.addDeed(square);

        ((DeedSquare) square).setOwner(player);

        UIUpdater.getInstance().setMessage(name + " bought " + sqName);
    }

}
