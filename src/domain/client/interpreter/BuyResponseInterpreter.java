package domain.client.interpreter;

import domain.client.UIUpdater;
import domain.server.board.Board;
import domain.server.board.DeedSquare;
import domain.server.board.Square;
import domain.util.GameInfo;

public class BuyResponseInterpreter implements ResponseInterpretable {

    @Override
    public void interpret(String[] message) {

        String name = message[1];
        int finalMoney = Integer.parseInt(message[2]);
        String squareName = message[3];



        Square boughtSquare = ((DeedSquare)Board.getInstance().getNameGivenSquare(squareName));
        GameInfo.getInstance().getPlayer(name).addDeed(boughtSquare);
        GameInfo.getInstance().getPlayer(name).setBalance(finalMoney);
        ((DeedSquare)boughtSquare).setOwner(name);

        UIUpdater.getInstance().setMessage(name + " bought " + squareName);

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
