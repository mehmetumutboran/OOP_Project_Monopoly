package domain.server.board.specialSquares;

import domain.server.board.SpecialSquareStrategy;
import domain.server.board.Square;

public class GoSquare extends Square implements SpecialSquareStrategy /*extends Square ?? */ {

    public GoSquare(String name, int layer, int index) {
        super(name, layer, index);
    }

    @Override
    public boolean doAction() {
        return false;
    }

}
