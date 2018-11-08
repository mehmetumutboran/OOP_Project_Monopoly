package domain.board.specialSquares;

import domain.board.SpecialSquareStrategy;
import domain.board.Square;

public class FreeParkingSquare implements SpecialSquareStrategy /*extends Square ?? */{
    @Override
    public boolean doAction() {
        return false;
    }
}
