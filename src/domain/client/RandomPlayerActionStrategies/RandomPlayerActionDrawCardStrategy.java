package domain.client.RandomPlayerActionStrategies;

import domain.client.ClientCommunicationHandler;
import domain.server.board.Board;
import domain.server.board.Square;
import domain.server.board.specialSquares.Chance;
import domain.server.board.specialSquares.CommunityChest;
import domain.util.Flags;
import domain.util.GameInfo;

public class RandomPlayerActionDrawCardStrategy implements RandomPlayerActionStrategy {
    @Override
    public void doRandomPlayerAction() {
        if (mustDrawCard()) {
            ClientCommunicationHandler.getInstance().sendRequest(Flags.getFlag("Draw"), GameInfo.getInstance().getCurrentPlayerName());
        }
    }

    private boolean mustDrawCard() {
        int[] loc = GameInfo.getInstance().getCurrentPlayer().getToken().getLocation().clone();
        Square sq = Board.getInstance().getSquare(loc[0], loc[1]);
        return (sq instanceof Chance || sq instanceof CommunityChest);
    }
}
