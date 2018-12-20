package domain.server.board.specialSquares;

import domain.server.board.Board;
import domain.server.board.SpecialSquareStrategy;
import domain.server.board.Square;


public class CommunityChest extends Square implements SpecialSquareStrategy {
    public CommunityChest(String name, int layer, int index) {
        super(name, layer, index);
    }

    @Override
    public boolean doAction() {
    //    Board.getInstance().getCommunityDeckList()[0].doAction();
        return true;

    }
}
