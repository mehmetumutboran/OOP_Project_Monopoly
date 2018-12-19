package domain.server.move;

import domain.server.controller.ServerCommunicationHandler;
import domain.util.Flags;
import domain.util.GameInfo;

public class NormalMove implements MoveStrategy {

    private static NormalMove instance;
    private static final int GO_COLLECT = 200;
    private static final int BONUSP_COLLECT = 250;
    private static final int BONUSL_COLLECT = 300;

    private NormalMove(){}

    public static NormalMove getInstance(){
        if(instance == null) instance = new NormalMove();
        return instance;
    }

    /**
     * This method calculates the final location of the player after move if move is in the same layer.
     * @param lastLoc The location of the player before move.
     * @param roll This is the total roll.
     * @param layerSQNumber This is the square number of the layer that player is in.
     * @return an int array that is the final location of the player.
     */
    @Override
    public int [] move(int[] lastLoc, int roll, int layerSQNumber) {
        int[] newLoc = new int[2];
        if (lastLoc[1] + roll > layerSQNumber - 1) {
            newLoc[0] = lastLoc[0];
            newLoc[1] = lastLoc[1] + roll - layerSQNumber;
            if (lastLoc[0] == 1) {
                System.out.println("Player passed above Go Square");
                ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Money"), GameInfo.getInstance().getCurrentPlayer().getName(),"Go" + "@" + GO_COLLECT);
            }
            if(lastLoc[0] == 2){
                if(lastLoc[1] + roll - layerSQNumber > 6 && lastLoc[1] > 15) {//Bug
                    System.out.println("Player passed above Bonus Square");
                    ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Money"), GameInfo.getInstance().getCurrentPlayer().getName(),"BonusP" + "@" + BONUSP_COLLECT);
                }else if(lastLoc[1] + roll - layerSQNumber == 6){
                    System.out.println("Player landed on Bonus Square");
                    ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Money"), GameInfo.getInstance().getCurrentPlayer().getName(),"BonusL" + "@" + BONUSL_COLLECT);
                }
            }

        } else {
            if(lastLoc[0] == 2){
                if(lastLoc[1] + roll > 6 && lastLoc[1] < 6) {
                    System.out.println("Player passed above Bonus Square");
                    ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Money"), GameInfo.getInstance().getCurrentPlayer().getName(),"BonusP" + "@" + BONUSP_COLLECT);
                }else if(lastLoc[1] + roll == 6){
                    System.out.println("Player landed on Bonus Square");
                    ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Money"), GameInfo.getInstance().getCurrentPlayer().getName(),"BonusL" + "@" + BONUSL_COLLECT);
                }
            }
            newLoc[0] = lastLoc[0];
            newLoc[1] = lastLoc[1] + roll;
        }
        return newLoc;
    }
}
