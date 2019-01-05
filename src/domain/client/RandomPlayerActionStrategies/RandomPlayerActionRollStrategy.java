package domain.client.RandomPlayerActionStrategies;

import domain.client.ClientCommunicationHandler;
import domain.util.Flags;
import domain.util.GameInfo;

public class RandomPlayerActionRollStrategy implements RandomPlayerActionStrategy {
    @Override
    public void doRandomPlayerAction() {
        System.out.println("Bot roll");
        ClientCommunicationHandler.getInstance().sendRequest(Flags.getFlag("Roll"), GameInfo.getInstance().getCurrentPlayerName());
    }
}
