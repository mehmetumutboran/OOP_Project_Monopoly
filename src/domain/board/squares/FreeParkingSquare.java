package domain.board.squares;

import domain.board.SpecialSquareStrategy;

public class FreeParkingSquare implements SpecialSquareStrategy {
    @Override
    public boolean doAction() {
        return false;
    }
}
