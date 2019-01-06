package domain.client.RandomPlayerActionStrategies;

import domain.client.ClientCommunicationHandler;
import domain.util.Flags;
import domain.util.GameInfo;

public class RandomPlayerActionFinishTurnStrategy implements RandomPlayerActionStrategy {

    @Override
    public void doRandomPlayerAction() {
        ClientCommunicationHandler.getInstance().sendRequest(Flags.getFlag("Finish"), GameInfo.getInstance().getCurrentPlayerName());
    }
}