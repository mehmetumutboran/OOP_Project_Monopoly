package domain.interpreter;

import domain.GameLogic;
import domain.UIUpdater;
import domain.board.Board;
import domain.board.specialSquares.Chance;
import domain.board.specialSquares.CommunityChest;

public class SpecialSquareInterpreter implements Interpreter {
    @Override
    public void interpret(String[] message) {
        String name = message[1];

        int[] loc = GameLogic.getInstance().getPlayer(name).getToken().getLocation();
        if (Board.getInstance().getSquare(loc[0], loc[1]) instanceof Chance) {
            UIUpdater.getInstance().setMessage(name + " picked " + Board.getInstance().getChanceDeckList()[0].getName());
        } else if (Board.getInstance().getSquare(loc[0], loc[1]) instanceof CommunityChest) {
            UIUpdater.getInstance().setMessage(name + " picked " + Board.getInstance().getCommunityDeckList()[0].getName());
        }

    }
}
