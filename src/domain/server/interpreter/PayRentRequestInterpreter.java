package domain.server.interpreter;

import java.io.DataInputStream;

public class PayRentRequestInterpreter implements RequestInterpretable {
    @Override
    public void interpret(DataInputStream dis, String[] message, int index) {
//        String name = message[1];
//        String sqName = message[2];
//
//        Player player = GameInfo.getInstance().getPlayer(name);
//        DeedSquare square = (DeedSquare) Board.getInstance().getNameGivenSquare(sqName);
//
//        player.decreaseMoney(square.getCurrentRent());
//
//        GameInfo.getInstance().getPlayer(square.getOwner()).increaseMoney(square.getCurrentRent());
//        UIUpdater.getInstance().setMessage(name + " paid rent " + square.getCurrentRent() + " dollars to " + square.getOwner());

    }
}
