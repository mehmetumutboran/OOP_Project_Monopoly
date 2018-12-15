package domain.client.interpreter;

import domain.server.board.Board;
import domain.server.board.DeedSquare;
import domain.util.GameInfo;

public class MortgageResponseInterpreter implements ResponseInterpretable {
    /**
     * This method sets the given player's given square mortgage.
     * @param message String array of the form [Flag, args...] Generated in {@link domain.server.util.GameState}
     * args includes player's name and the square that will be mortgaged.
     */
    @Override
    public void interpret(String[] message) {
        String name = message[1];
        String sqName = message[2];

        ((DeedSquare) Board.getInstance().getNameGivenSquare(sqName)).setMortgaged(true);
        GameInfo.getInstance().getPlayer(name).addMortgagedSquare((DeedSquare) Board.getInstance().getNameGivenSquare(sqName));
        GameInfo.getInstance().getPlayer(name).increaseMoney(((DeedSquare) Board.getInstance().getNameGivenSquare(sqName)).getMortgageValue());
    }
}
