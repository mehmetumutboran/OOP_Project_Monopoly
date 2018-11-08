package domain.board.specialSquares;

import domain.board.SpecialSquareStrategy;
import domain.board.Square;

public class FreeParkingSquare extends Square  implements SpecialSquareStrategy /*extends Square ?? */{
    public FreeParkingSquare(String name, int layer, int index) {
        super(name, layer, index);
    }

    @Override
    public boolean doAction() {
        return false;
    }
}
