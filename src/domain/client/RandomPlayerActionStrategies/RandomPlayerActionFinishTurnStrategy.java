package domain.client.RandomPlayerActionStrategies;

import domain.client.ClientCommunicationHandler;
import domain.util.Flags;
import domain.util.GameInfo;

public class RandomPlayerActionFinishTurnStrategy implements RandomPlayerActionStrategy {
    private boolean isSecondMove;

    public RandomPlayerActionFinishTurnStrategy(boolean isSecondMove) {
        this.isSecondMove = isSecondMove;
    }

    @Override
    public void doRandomPlayerAction() {
        if (GameInfo.getInstance().getCurrentPlayer().getFaceValues()[2] == 7 && isSecondMove) {
            ClientCommunicationHandler.getInstance().sendRequest(Flags.getFlag("Finish"), GameInfo.getInstance().getCurrentPlayerName());
        } else if (GameInfo.getInstance().getCurrentPlayer().getFaceValues()[2] != 7) {
            ClientCommunicationHandler.getInstance().sendRequest(Flags.getFlag("Finish"), GameInfo.getInstance().getCurrentPlayerName());
        }
    }
}