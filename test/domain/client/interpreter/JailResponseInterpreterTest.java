package domain.client.interpreter;

import domain.server.player.Player;
import domain.util.Flags;
import domain.util.GameInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class JailResponseInterpreterTest {

    private Player test1;
    private Player test2;

    @BeforeEach
    void setUp() {
        GameInfo.getInstance().reset();
        test1 = new Player("Test1");
        test2 = new Player("Test2");
        GameInfo.getInstance().addPlayer(test1);
        GameInfo.getInstance().addPlayer(test2);
        test2.setInJail(true);
    }

    @Test
    void interpret() {
        //Go to Jail
        char flag = Flags.getFlag("GoToJail");
        String [] message = {"" + flag , test1.getName()};
        new JailResponseInterpreter().interpret(message);
        assertTrue(GameInfo.getInstance().getPlayer("Test1").isInJail());
        //Go out of Jail
        flag = Flags.getFlag("GoOutOfJail");
        String [] message2 = {"" + flag , test2.getName()};
        new JailResponseInterpreter().interpret(message2);
        assertFalse(GameInfo.getInstance().getPlayer("Test2").isInJail());
    }
}