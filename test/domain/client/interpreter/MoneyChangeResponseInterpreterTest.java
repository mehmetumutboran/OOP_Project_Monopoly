package domain.client.interpreter;

import domain.client.PlayerActionController;
import domain.server.player.Player;
import domain.util.GameInfo;
import network.server.serverFacade.ServerFacade;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoneyChangeResponseInterpreterTest {

    private Player rich;

    @BeforeEach
    void setUp() {
        GameInfo.getInstance().reset();
        PlayerActionController.getInstance().host("Rich", 2222, false);
        while (GameInfo.getInstance().getPlayer("Rich") == null) {
            continue;
        }
        rich = GameInfo.getInstance().getPlayer("Rich");
    }

    @Test
    void interpret() {
        String [] message = {"Flag", rich.getName(), "Go@200"};
        new MoneyChangeResponseInterpreter().interpret(message);
        assertEquals(3400,rich.getBalance());
        rich.setBalance(3200);
        String [] message2 = {"Flag", rich.getName(), "BonusL@350"};
        new MoneyChangeResponseInterpreter().interpret(message2);
        assertEquals(3550,rich.getBalance());
        rich.setBalance(3200);
        String [] message3 = {"Flag", rich.getName(), "@48"};
        new MoneyChangeResponseInterpreter().interpret(message3);
        assertEquals(3248,rich.getBalance());
        rich.setBalance(3200);
        String [] message4 = {"Flag", rich.getName(), "@-50"};
        new MoneyChangeResponseInterpreter().interpret(message4);
        assertEquals(3150,rich.getBalance());
    }

    @AfterEach
    void shutDown(){
        ServerFacade.getInstance().shutDown();
    }
}