package domain.board.specialSquares;

import domain.board.SpecialSquareStrategy;


public class Jail implements SpecialSquareStrategy{
    @Override
    public boolean doAction() {
        return false;
    }
}
