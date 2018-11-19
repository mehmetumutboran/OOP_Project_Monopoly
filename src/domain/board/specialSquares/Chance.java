package domain.board.specialSquares;

import domain.board.Board;
import domain.board.SpecialSquareStrategy;
import domain.board.Square;


public class Chance extends Square implements SpecialSquareStrategy {
    public Chance(String name, int layer, int index) {
        super(name, layer, index);
    }

    @Override
    public boolean doAction() {


        Board.getInstance().getChanceDeckList()[0].doAction();
        return true;
    }

}
