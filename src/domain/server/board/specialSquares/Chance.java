package domain.server.board.specialSquares;

import domain.server.board.Board;
import domain.server.board.SpecialSquareStrategy;
import domain.server.board.Square;


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
