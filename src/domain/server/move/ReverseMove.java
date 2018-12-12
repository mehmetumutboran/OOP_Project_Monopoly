package domain.server.move;

public class ReverseMove implements MoveStrategy {

    private static ReverseMove instance;

    private ReverseMove(){}

    public static ReverseMove getInstance(){
        if(instance == null) instance = new ReverseMove();
        return instance;
    }
    @Override
    public int [] move(int[] lastLoc, int roll, int layerSQNumber) {
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
