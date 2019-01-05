package domain.client.RandomPlayerActionStrategies;

import domain.client.ClientCommunicationHandler;
import domain.server.board.Board;
import domain.server.board.DeedSquare;
import domain.server.board.Square;
import domain.util.Flags;
import domain.util.GameInfo;

import java.util.Random;

public class RandomPlayerActionBuyStrategy implements RandomPlayerActionStrategy {
    float botChance;

    public RandomPlayerActionBuyStrategy(float botChance) {
        this.botChance = botChance;
    }

    @Override
    public void doRandomPlayerAction() {
        if (isBuyable() && lucky()) {
            ClientCommunicationHandler.getInstance().sendRequest(Flags.getFlag("Buy"), GameInfo.getInstance().getCurrentPlayerName());
        }
    }

    private boolean isBuyable() {
        int[] loc = GameInfo.getInstance().getCurrentPlayer().getToken().getLocation().clone();
        Square sq = Board.getInstance().getSquare(loc[0], loc[1]);
        return sq instanceof DeedSquare && ((DeedSquare) sq).getOwner() == null;
    }

    private boolean lucky() {
        return botChance > new Random().nextFloat();
    }
}
