package domain.server.move;

import domain.server.board.Board;

public class UpDownMove implements MoveStrategy {

    private static UpDownMove instance;
    private static final int SECOND_LAYER_SQ = 24;
    private static final int FIRST_LAYER_SQ = 40;
    private static final int ZEROTH_LAYER_SQ = 56;

    private UpDownMove() {
    }

    public static UpDownMove getInstance() {
        if (instance == null) instance = new UpDownMove();
        return instance;
    }

    /**
     * This method calculates the final location of the player after move if the player will be in railroad and will change layer.
     *
     * @param lastLoc       The location of the player before move.
     * @param roll          This is the total roll.
     * @param layerSQNumber This is the square number of the layer that player is in before.
     * @return an int array that is the final location of the player.
     */
    @Override
    public int[] move(int[] lastLoc, int roll, int layerSQNumber) {
        int railroad;
        if (Board.getInstance().railRoadFind(lastLoc, roll)[0].getLocation()[1] - lastLoc[1] < 0)
            railroad = Board.getInstance().railRoadFind(lastLoc, roll)[0].getLocation()[1] - lastLoc[1] + layerSQNumber;
        else railroad = Board.getInstance().railRoadFind(lastLoc, roll)[0].getLocation()[1] - lastLoc[1];
        roll = roll - railroad;
        lastLoc = NormalMove.getInstance().move(lastLoc, railroad, layerSQNumber);
        return upDownMoveRec(lastLoc, roll);
    }

    private int[] upDownMoveRec(int[] lastLoc, int roll) {
        if (Board.getInstance().railRoadFind(lastLoc, roll)[0] != null) {
            String sqName = Board.getInstance().railRoadFind(lastLoc, roll)[0].getName();
            int[] tryLoc = new int[2];
            switch (sqName) {
                case "Reading Railroad":
                    if (lastLoc[0] == 0) {
                        return railRoadHelper(lastLoc, 1, 5, tryLoc, roll, FIRST_LAYER_SQ);
                    } else if (lastLoc[0] == 1) {
                        return railRoadHelper(lastLoc, 0, 7, tryLoc, roll, ZEROTH_LAYER_SQ);
                    }
                    break;
                case "B.&O. Railroad":
                    if (lastLoc[0] == 0) {
                        return railRoadHelper(lastLoc, 1, 25, tryLoc, roll, FIRST_LAYER_SQ);
                    } else if (lastLoc[0] == 1) {
                        return railRoadHelper(lastLoc, 0, 35, tryLoc, roll, ZEROTH_LAYER_SQ);
                    }
                    break;
                case "Pennsylvania Railroad":
                    if (lastLoc[0] == 1) {
                        return railRoadHelper(lastLoc, 2, 9, tryLoc, roll, SECOND_LAYER_SQ);
                    } else if (lastLoc[0] == 2) {
                        return railRoadHelper(lastLoc, 1, 15, tryLoc, roll, FIRST_LAYER_SQ);
                    }
                    break;
                case "Short Line Railroad":
                    if (lastLoc[0] == 1) {
                        return railRoadHelper(lastLoc, 2, 21, tryLoc, roll, SECOND_LAYER_SQ);
                    } else if (lastLoc[0] == 2) {
                        return railRoadHelper(lastLoc, 1, 35, tryLoc, roll, FIRST_LAYER_SQ);
                    }
                    break;
                default:
                    break;
            }
        }
        return lastLoc;
    }

    private int[] railRoadHelper(int[] lastLoc, int layer, int railSq, int[] tryLoc, int roll, int layerSQNumber) {
        lastLoc[0] = layer;
        lastLoc[1] = railSq;
        tryLoc[0] = lastLoc[0];
        tryLoc[1] = lastLoc[1] + 1;
        if (Board.getInstance().railRoadFind(tryLoc, roll)[0] == null || Board.getInstance().railRoadFind(tryLoc, roll)[0].getLocation()[1] - lastLoc[1] > roll) {
            lastLoc = NormalMove.getInstance().move(lastLoc, roll, layerSQNumber);
            return lastLoc;
        }
        int railroad;
        if (Board.getInstance().railRoadFind(tryLoc, roll)[0].getLocation()[1] - lastLoc[1] < 0)
            railroad = Board.getInstance().railRoadFind(tryLoc, roll)[0].getLocation()[1] - lastLoc[1] + layerSQNumber;
        else railroad = Board.getInstance().railRoadFind(tryLoc, roll)[0].getLocation()[1] - lastLoc[1];
        roll = roll - railroad;
        lastLoc = NormalMove.getInstance().move(lastLoc, railroad, layerSQNumber);
        return upDownMoveRec(lastLoc, roll);
    }
}
