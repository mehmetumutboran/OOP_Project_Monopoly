package domain.server.board.specialSquares;

import domain.server.board.SpecialSquareStrategy;
import domain.server.board.Square;


public class HollandTunnel extends Square implements SpecialSquareStrategy {
    public HollandTunnel(String name, int layer, int index) {
        super(name, layer, index);
    }

    @Override
    public boolean doAction() {
        return false;
    }
}
