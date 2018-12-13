package domain.client.interpreter;

import domain.client.UIUpdater;
import domain.server.board.Board;
import domain.server.board.DeedSquare;
import domain.util.GameInfo;

public class PayRentResponseInterpreter implements ResponseInterpretable {
    @Override
    public void interpret(String[] message) {

        String name = message[1];
        int customerFinalMoney = Integer.parseInt(message[2]);
        int ownerFinalMoney = Integer.parseInt(message[3]);
        String squareName = message[4];



        DeedSquare boughtSquare = ((DeedSquare) Board.getInstance().getNameGivenSquare(squareName));
        GameInfo.getInstance().getPlayer(name).setBalance(customerFinalMoney);
        GameInfo.getInstance().getPlayer(boughtSquare.getOwner()).setBalance(ownerFinalMoney);
//        String ownerName = boughtSquare.getOwner();
//        Board.getInstance().getNameGivenSquare(ownerName);


        UIUpdater.getInstance().setMessage(name + " paid rent for " + squareName);
//        String name = message[1];
//        String sqName = message[2];
//
//        Player player = GameLogic.getInstance().getPlayer(name);
//        DeedSquare square = (DeedSquare) Board.getInstance().getNameGivenSquare(sqName);
//
//        player.decreaseMoney(square.getRent());
//
//        square.getOwner().increaseMoney(square.getRent());
//        UIUpdater.getInstance().setMessage(name + " paid rent " + square.getRent() + " dollars to " + square.getOwner().getName());


    }
}
