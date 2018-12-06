package domain.client.interpreter;

import domain.server.board.Board;
import domain.util.GameInfo;

public class BuyResponseInterpreter implements ResponseInterpretable {

    @Override
    public void interpret(String[] message) {

        String name = message[0];
        int finalMoney = Integer.parseInt(message[1]);

        GameInfo.getInstance().getPlayer(name).setBalance(finalMoney);


//        String name = message[1];
//        String sqName = message[2];
//
//        Player player = GameLogic.getInstance().getPlayer(name);
//        Square square = Board.getInstance().getNameGivenSquare(sqName);
//
//        player.decreaseMoney(((DeedSquare) square).getBuyValue());
//        player.addDeed(square);
//
//        ((DeedSquare) square).setOwner(player);
//
//        UIUpdater.getInstance().setMessage(name + " bought " + sqName);
    }

}
