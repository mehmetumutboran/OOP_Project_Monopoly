package domain.client.interpreter;

import domain.client.UIUpdater;
import domain.server.board.Board;
import domain.server.board.DeedSquare;
import domain.util.GameInfo;

public class MortgageResponseInterpreter implements ResponseInterpretable {
    @Override
    public void interpret(String[] message) {
        String name = message[1];
        String sqName = message[2];

        ((DeedSquare) Board.getInstance().getNameGivenSquare(sqName)).setMortgaged(true);
        GameInfo.getInstance().getPlayer(name).addMortgagedSquare((DeedSquare) Board.getInstance().getNameGivenSquare(sqName));
        GameInfo.getInstance().getPlayer(name).increaseMoney(((DeedSquare) Board.getInstance().getNameGivenSquare(sqName)).getMortgageValue());

        UIUpdater.getInstance().setMessage(name + " mortgaged " + sqName);
    }
}
