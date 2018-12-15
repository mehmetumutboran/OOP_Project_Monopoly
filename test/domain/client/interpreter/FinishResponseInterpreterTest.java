package domain.client.interpreter;

import domain.client.PlayerActionController;
import domain.server.ReceivedChecker;
import domain.server.player.Player;
import domain.util.Flags;
import domain.util.GameInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Deque;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FinishResponseInterpreterTest {
    private Player player1;
    private Player player2;
    private Player player3;
    private Deque<String> deque = new LinkedList<>();

    @BeforeEach
    void setUp() throws InterruptedException {
        PlayerActionController.getInstance().host("test1", 2222, true);

        Thread.sleep(200);

        PlayerActionController.getInstance().join("test2", "localhost", 2222);
        Thread.sleep(200);
        PlayerActionController.getInstance().join("test3", "localhost", 2222);


        player1 = GameInfo.getInstance().getPlayer("test1");
        player2 = GameInfo.getInstance().getPlayer("test2");
        player3 = GameInfo.getInstance().getPlayer("test3");

        deque.addLast(player1.getName());
        deque.addLast(player2.getName());
        deque.addLast(player3.getName());
        GameInfo.getInstance().setPlayerQueue(deque);
    }

    @Test
    void interpret() {
//        System.out.println(GameInfo.getInstance().getPlayerQueue());
        new FinishResponseInterpreter().interpret(new String[]{Flags.getFlag("Finish")+"", "test1"});
        assertEquals(player2, GameInfo.getInstance().getCurrentPlayer());
        assertEquals("test2", GameInfo.getInstance().getCurrentPlayerName());


    }
}