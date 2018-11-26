package domain.server.board.specialSquares;

import domain.server.board.SpecialSquareStrategy;
import domain.server.board.Square;


public class BusTicket extends Square implements SpecialSquareStrategy {
    public BusTicket(String name, int layer, int index) {
        super(name, layer, index);
    }

    @Override
    public boolean doAction() {
        return false;
    }
}
