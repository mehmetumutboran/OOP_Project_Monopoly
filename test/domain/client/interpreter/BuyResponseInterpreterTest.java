package domain.client.interpreter;

import domain.client.PlayerActionController;
import domain.server.board.Board;
import domain.server.board.DeedSquare;
import domain.server.player.Player;
import domain.util.GameInfo;
import network.server.serverFacade.ServerFacade;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BuyResponseInterpreterTest {

    private Player buyer;

    @BeforeEach
    void setUp() {
        GameInfo.getInstance().reset();
        PlayerActionController.getInstance().host("Buyer", 2222, false);
        while (GameInfo.getInstance().getPlayer("Buyer") == null) {
            continue;
        }
        buyer = GameInfo.getInstance().getPlayer("Buyer");
    }

    @Test
    void interpret() {
        String squareName = "Ventura Boulevard";
        int buyValue = ((DeedSquare) Board.getInstance().getNameGivenSquare(squareName)).getBuyValue();
        String[] message = {"Flag", buyer.getName(), "" + (buyer.getBalance() - buyValue), squareName};
        new BuyResponseInterpreter().interpret(message);
        assertEquals(3200 - 480, GameInfo.getInstance().getPlayer("Buyer").getBalance());
        assertTrue(((DeedSquare) Board.getInstance().getNameGivenSquare(squareName)).isOwned());
        assertEquals(buyer, GameInfo.getInstance().getPlayer(((DeedSquare) Board.getInstance().getNameGivenSquare(squareName)).getOwner()));
        assertTrue(GameInfo.getInstance().getPlayer("Buyer").getOwnedProperties().contains(Board.getInstance().getNameGivenSquare(squareName)));
        assertTrue(buyer.repOK());
    }

    @AfterEach
    void shutDown(){
        ServerFacade.getInstance().shutDown();
    }
}