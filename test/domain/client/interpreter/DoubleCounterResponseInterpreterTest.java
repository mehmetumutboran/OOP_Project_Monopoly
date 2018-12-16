package domain.client.interpreter;

import domain.client.PlayerActionController;
import domain.server.player.Player;
import domain.util.GameInfo;
import network.server.serverFacade.ServerFacade;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoubleCounterResponseInterpreterTest {

    private Player test;

    @BeforeEach
    void setUp() {
        GameInfo.getInstance().reset();
        PlayerActionController.getInstance().host("Test", 2222, false);
        while (GameInfo.getInstance().getPlayer("Test") == null) {
            continue;
        }
        test = GameInfo.getInstance().getPlayer("Test");
        test.resetDoubleCounter();
    }

    @Test
    void interpret() {
        test.incrementDoubleCounter();
        String [] message = {"Flag", test.getName(), "1"};
        new DoubleCounterResponseInterpreter().interpret(message);
        assertEquals(2,test.getDoubleCounter());
        String [] message2 = {"Flag", test.getName(), "0"};
        new DoubleCounterResponseInterpreter().interpret(message2);
        assertEquals(0,test.getDoubleCounter());
        test.resetDoubleCounter();
        String [] message3 = {"Flag", test.getName(), "1"};
        new DoubleCounterResponseInterpreter().interpret(message3);
        assertEquals(1,test.getDoubleCounter());
        test.resetDoubleCounter();
        String [] message4 = {"Flag", test.getName(), "0"};
        new DoubleCounterResponseInterpreter().interpret(message4);
        assertEquals(0,test.getDoubleCounter());
    }

    @AfterEach
    void shutDown(){
        ServerFacade.getInstance().shutDown();
    }
}