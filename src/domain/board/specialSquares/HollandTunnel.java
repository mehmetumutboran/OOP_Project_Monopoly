package domain.board.specialSquares;

import domain.board.SpecialSquareStrategy;
import domain.board.Square;


public class HollandTunnel extends Square implements SpecialSquareStrategy{
    public HollandTunnel(String name, int layer, int index) {
        super(name, layer, index);
    }

    @Override
    public boolean doAction() {
        return false;
    }
}
