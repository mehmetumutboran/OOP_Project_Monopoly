package domain.client.interpreter;

import domain.client.PlayerActionController;
import domain.server.board.Board;
import domain.server.board.DeedSquare;
import domain.server.board.Upgradable;
import domain.server.player.Player;
import domain.util.GameInfo;
import network.server.serverFacade.ServerFacade;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DowngradeResponseInterpreterTest {

    private Player test;
    String squareName;
    int buyValue;
    int buildingCost;
    String buildingToDownFrom;

    @BeforeEach
    void setUp() {
        GameInfo.getInstance().reset();
        PlayerActionController.getInstance().host("Test", 2222, false);
        while (GameInfo.getInstance().getPlayer("Test") == null) {
            continue;
        }
        test = GameInfo.getInstance().getPlayer("Test");
        squareName = "Nicollet Avenue";
        buyValue = ((DeedSquare) Board.getInstance().getNameGivenSquare(squareName)).getBuyValue();
        buildingCost =((Upgradable) Board.getInstance().getNameGivenSquare(squareName)).getBuildingCost();
        test.addDeed((Board.getInstance().getNameGivenSquare(squareName)));
        ((DeedSquare) Board.getInstance().getNameGivenSquare(squareName)).setOwner(test.getName());
        ((Upgradable) Board.getInstance().getNameGivenSquare(squareName)).upgrade();
        test.setBalance(test.getBalance() - buyValue-buildingCost);

        buildingToDownFrom = "House";
       // test.setBalance(test.getBalance()+buildingCost/2);

    }
    //sets the message of interpreter, balance is equal, building in that square is downgraded no such building on that square
    //number of buildings on the square is equal to zero since there was one and it is downgraded.
    @Test
    void interpret() {
        String [] message = {"Flag", test.getName(), squareName, buildingToDownFrom};
        new DowngradeResponseInterpreter().interpret(message);
        assertEquals(3200-buyValue-buildingCost+buildingCost/2,test.getBalance());
        assertFalse(((Upgradable) Board.getInstance().getNameGivenSquare(squareName)).isUpgraded());
        assertTrue((((Upgradable) Board.getInstance().getNameGivenSquare(squareName)).getBuildingCount()==0));
    }

    @AfterEach
    void shutDown(){
        ServerFacade.getInstance().shutDown();
    }

}