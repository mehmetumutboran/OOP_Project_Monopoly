package domain.server.move;

public class ReverseMove implements MoveStrategy {

    private static ReverseMove instance;

    private ReverseMove() {
    }

    public static ReverseMove getInstance() {
        if (instance == null) instance = new ReverseMove();
        return instance;
    }

    /**
     * This method calculates the final location of the player after move if move is in the same layer and the player is coming from reverse direction square.
     *
     * @param lastLoc       The location of the player before move.
     * @param roll          This is the total roll.
     * @param layerSQNumber This is the square number of the layer that player is in.
     * @return an int array that is the final location of the player.
     */
    @Override
    public int[] move(int[] lastLoc, int roll, int layerSQNumber) {
        int[] newLoc = new int[2];
        if (lastLoc[1] - roll < 0) {
            newLoc[0] = lastLoc[0];
            newLoc[1] = lastLoc[1] - roll + layerSQNumber;
            if (lastLoc[0] == 1) {
                System.out.println("Player passed above Go Square");
                //ServerCommunicationHandler.getInstance().sendResponse(moneyFlag, getCurrentPlayerName().getName(), GO_COLLECT);
            }
        } else {
            newLoc[0] = lastLoc[0];
            newLoc[1] = lastLoc[1] - roll;
        }
        return newLoc;
    }
}
