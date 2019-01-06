package domain.client.RandomPlayerActionStrategies;

import domain.client.ClientCommunicationHandler;
import domain.server.board.Board;
import domain.server.board.DeedSquare;
import domain.server.board.Square;
import domain.util.Flags;
import domain.util.GameInfo;

public class RandomPlayerActionPayRentStrategy implements RandomPlayerActionStrategy {
    @Override
    public void doRandomPlayerAction() {
        if (mustPayRent()) {
            System.out.println("Bot payRent");
            ClientCommunicationHandler.getInstance().sendRequest(Flags.getFlag("PayRent"), GameInfo.getInstance().getCurrentPlayerName());
        }
    }

    private boolean mustPayRent() {
        int[] loc = GameInfo.getInstance().getCurrentPlayer().getToken().getLocation().clone();
        Square sq = Board.getInstance().getSquare(loc[0], loc[1]);
        return sq instanceof DeedSquare &&
                ((DeedSquare) sq).getOwner() != null &&
                !((DeedSquare) sq).getOwner().equals(GameInfo.getInstance().getCurrentPlayer().getName());
    }
}
