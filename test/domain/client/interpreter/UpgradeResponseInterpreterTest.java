package domain.client.interpreter;

import domain.client.PlayerActionController;
import domain.server.board.Board;
import domain.server.board.DeedSquare;
import domain.server.board.Property;
import domain.server.board.Upgradable;
import domain.server.player.Player;
import domain.util.GameInfo;
import network.server.serverFacade.ServerFacade;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpgradeResponseInterpreterTest {

    private Player test;
    String squareName;
    int buyValue;
    int buildingCost;
    String buildingToUp;
    @BeforeEach
    void setUp() {
        GameInfo.getInstance().reset();
        PlayerActionController.getInstance().host("Test", 2222, false);
        while (GameInfo.getInstance().getPlayer("Test") == null) {
            continue;
        }
        test = GameInfo.getInstance().getPlayer("Test");
        squareName = "Lake Street";
        buyValue = ((DeedSquare) Board.getInstance().getNameGivenSquare(squareName)).getBuyValue();
        buildingCost =((Upgradable) Board.getInstance().getNameGivenSquare(squareName)).getBuildingCost();
        buildingToUp = "House";
        test.setBalance(test.getBalance() - buyValue-buildingCost);
        test.addDeed((Board.getInstance().getNameGivenSquare(squareName)));
        ((DeedSquare) Board.getInstance().getNameGivenSquare(squareName)).setOwner(test.getName());
    }
    @Test
    void interpret() {
        String [] message = {"Flag", test.getName(), squareName, buildingToUp};
        new UpgradeResponseInterpreter().interpret(message);
        assertEquals(3200-buyValue-buildingCost - ((Upgradable)Board.getInstance().getNameGivenSquare(squareName)).getBuildingCost(),test.getBalance());
        assertTrue(((Upgradable) Board.getInstance().getNameGivenSquare(squareName)).isUpgraded());
        assertTrue((((Upgradable) Board.getInstance().getNameGivenSquare(squareName)).getBuildingCount()!=0));
    }
    @AfterEach
    void shutDown(){
        ServerFacade.getInstance().shutDown();
    }
}