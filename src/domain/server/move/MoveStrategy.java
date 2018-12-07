package domain.server.move;

public interface MoveStrategy {
    int [] move(int[] lastLoc, int roll, int layerSQNumber);
}
