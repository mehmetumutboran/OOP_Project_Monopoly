package domain.server.board.specialSquares;

import domain.server.board.SpecialSquareStrategy;
import domain.server.board.Square;

public class FreeParkingSquare extends Square implements SpecialSquareStrategy /*extends Square ?? */ {
    public FreeParkingSquare(String name, int layer, int index) {
        super(name, layer, index);
    }

    @Override
    public boolean doAction() {
        return false;
    }
}
