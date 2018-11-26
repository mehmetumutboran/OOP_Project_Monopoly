package domain.client.interpreter;

import domain.client.UIUpdater;
import domain.server.GameLogic;
import domain.server.board.Board;
import domain.server.board.DeedSquare;
import domain.server.board.Square;
import domain.server.player.Player;

public class BuyResponseInterpreter implements ResponseInterpretable {

    @Override
    public void interpret(String[] message) {
        String name = message[1];
        String sqName = message[2];

        Player player = GameLogic.getInstance().getPlayer(name);
        Square square = Board.getInstance().getNameGivenSquare(sqName);

        player.decreaseMoney(((DeedSquare) square).getBuyValue());
        player.addDeed(square);

        ((DeedSquare) square).setOwner(player);

        UIUpdater.getInstance().setMessage(name + " bought " + sqName);
    }

}
