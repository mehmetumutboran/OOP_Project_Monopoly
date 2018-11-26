package domain.server.board.specialSquares;

import domain.server.board.SpecialSquareStrategy;
import domain.server.board.Square;


public class RollThree extends Square implements SpecialSquareStrategy {
    public RollThree(String name, int layer, int index) {
        super(name, layer, index);
    }

    @Override
    public boolean doAction() {
        return false;
    }
}
