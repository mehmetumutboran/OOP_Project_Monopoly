package domain.board.specialSquares;

import domain.board.SpecialSquareStrategy;
import domain.board.Square;


public class BusTicket extends Square implements SpecialSquareStrategy{
    public BusTicket(String name, int layer, int index) {
        super(name, layer, index);
    }
    @Override
    public boolean doAction() {
        return false;
    }
}
