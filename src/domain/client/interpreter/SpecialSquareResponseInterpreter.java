package domain.client.interpreter;

import domain.client.UIUpdater;
import domain.server.GameLogic;
import domain.server.board.Board;
import domain.server.board.specialSquares.Chance;
import domain.server.board.specialSquares.CommunityChest;

public class SpecialSquareResponseInterpreter implements ResponseInterpretable {
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
