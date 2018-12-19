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

import static org.junit.jupiter.api.Assertions.*;

class MortgageResponseInterpreterTest {

    private Player test;
    String squareName;
    int buyValue;

    @BeforeEach
    void setUp() {
        GameInfo.getInstance().reset();
        PlayerActionController.getInstance().host("Test", 2222, false);
        while (GameInfo.getInstance().getPlayer("Test") == null) {
            continue;
        }
        test = GameInfo.getInstance().getPlayer("Test");
        GameInfo.getInstance().addPlayer(test);
        squareName = "Wacker Drive";
        buyValue = ((DeedSquare) Board.getInstance().getNameGivenSquare(squareName)).getBuyValue();
        test.setBalance(test.getBalance() - buyValue);
        test.addDeed((Board.getInstance().getNameGivenSquare(squareName)));
        ((DeedSquare) Board.getInstance().getNameGivenSquare(squareName)).setOwner(test.getName());
    }

    @Test
    void interpret() {
        String [] message = {"Flag", test.getName(), squareName};
        new MortgageResponseInterpreter().interpret(message);
        assertEquals(3200 - buyValue + ((DeedSquare) Board.getInstance().getNameGivenSquare(squareName)).getMortgageValue(),test.getBalance());
        assertTrue(((DeedSquare) Board.getInstance().getNameGivenSquare(squareName)).isMortgaged());
        assertTrue(test.getMortgagedSquares().contains((Board.getInstance().getNameGivenSquare(squareName))));
        assertTrue(test.repOK());
        assertTrue(Board.getInstance().getNameGivenSquare(squareName).repOK());
    }
    @AfterEach
    void shutDown(){
        ServerFacade.getInstance().shutDown();
    }
}