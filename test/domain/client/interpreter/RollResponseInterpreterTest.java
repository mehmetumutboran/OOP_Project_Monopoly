package domain.client.interpreter;

import domain.client.PlayerActionController;
import domain.server.player.Player;
import domain.util.Flags;
import domain.util.GameInfo;
import domain.util.MessageConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RollResponseInterpreterTest {
    private Player player;

    @BeforeEach
    void setUp() {
        GameInfo.getInstance().reset();
        PlayerActionController.getInstance().host("Test", 2222, true);
        while (GameInfo.getInstance().getPlayer("Test") == null) continue;
        player = GameInfo.getInstance().getPlayer("Test");
    }

    @Test
    void interpret() {
        // Test1
        int[] roll1 = new int[]{2, 3, 1};
        new RollResponseInterpreter().interpret(new String[]{Flags.getFlag("Roll") +"",
                player.getName(),
                MessageConverter.convertArrayToString(roll1)});
        assertArrayEquals(roll1, GameInfo.getInstance().getPlayer(player.getName()).getFaceValues());

        // Test2
        int[] roll2 = new int[]{1, 3};
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> new RollResponseInterpreter().interpret(
                        new String[]{Flags.getFlag("Roll") +"",
                                player.getName(),
                                MessageConverter.convertArrayToString(roll2)}));
    }
}