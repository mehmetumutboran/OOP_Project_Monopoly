package domain.client.interpreter;

import domain.server.board.Board;
import domain.server.board.DeedSquare;
import domain.server.board.Property;
import domain.server.player.Player;
import domain.util.GameInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PayRentResponseInterpreterTest {

    private Player payer;
    private Player owner;

    @BeforeEach
    void setUp() {
        GameInfo.getInstance().reset();
        payer = new Player("Payer");
        owner = new Player("Owner");
        GameInfo.getInstance().addPlayer(payer);
        GameInfo.getInstance().addPlayer(owner);
    }

    @Test
    void interpret() {
        String locName = "Kirby Drive";
        ((DeedSquare)Board.getInstance().getNameGivenSquare(locName)).setOwner("Owner");
        int rent = ((Property)Board.getInstance().getNameGivenSquare(locName)).getCurrentRent();//rent = 14
        String [] message = {"Flag" , "Payer" ,""+(payer.getBalance()-rent) , ""+(owner.getBalance()+rent), locName};
        new PayRentResponseInterpreter().interpret(message);
        assertEquals(3186,payer.getBalance());
        assertEquals(3214,owner.getBalance());
        assertTrue(payer.repOK());
        assertTrue(owner.repOK());
        assertTrue(Board.getInstance().getNameGivenSquare(locName).repOK());
    }
}