package domain.interpreter;

import domain.client.UIUpdater;
import domain.server.GameLogic;
import domain.server.board.Board;
import domain.server.board.DeedSquare;
import domain.server.interpreter.RequestInterpretable;
import domain.server.player.Player;
import domain.util.GameInfo;

public class PayRentInterpreter implements RequestInterpretable {
    @Override
    public void interpret(String[] message, int index) {
        String name = message[1];
        String sqName = message[2];

        Player player = GameInfo.getInstance().getPlayer(name);
        DeedSquare square = (DeedSquare) Board.getInstance().getNameGivenSquare(sqName);

        player.decreaseMoney(square.getCurrentRent());

        GameInfo.getInstance().getPlayer(square.getOwner()).increaseMoney(square.getCurrentRent());
        UIUpdater.getInstance().setMessage(name + " paid rent " + square.getCurrentRent() + " dollars to " + square.getOwner());


    }
}
