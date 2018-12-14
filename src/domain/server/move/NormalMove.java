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
        } else {
            if(lastLoc[0] == 2){
                if(lastLoc[1] + roll > 6) {
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
