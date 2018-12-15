package domain.client.interpreter;

import domain.server.board.Board;
import domain.server.board.DeedSquare;
import domain.server.board.Property;
import domain.server.player.Player;
import domain.util.GameInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuyResponseInterpreterTest {

    private Player buyer;

    @BeforeEach
    void setUp() {
        GameInfo.getInstance().reset();
        buyer = new Player("Buyer");
        GameInfo.getInstance().addPlayer(buyer);
    }

    //@Test
    void interpret() {
        String squareName = "Ventura Boulevard";
        int buyValue = ((DeedSquare)Board.getInstance().getNameGivenSquare(squareName)).getBuyValue();
        String [] message = {"Flag", buyer.getName(), "" + (buyer.getBalance() - buyValue), squareName};
        new BuyResponseInterpreter().interpret(message);
        assertEquals(3200-480,GameInfo.getInstance().getPlayer("Buyer").getBalance());
        assertTrue(((DeedSquare)Board.getInstance().getNameGivenSquare(squareName)).isOwned());
        assertEquals(buyer, GameInfo.getInstance().getPlayer(((DeedSquare)Board.getInstance().getNameGivenSquare(squareName)).getOwner()));
        assertTrue(GameInfo.getInstance().getPlayer("Buyer").getOwnedProperties().contains(Board.getInstance().getNameGivenSquare(squareName)));
    }
}