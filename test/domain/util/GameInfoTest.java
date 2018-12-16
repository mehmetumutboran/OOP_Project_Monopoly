package domain.util;

import domain.server.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class GameInfoTest {

    @BeforeEach
    void setUp(){
        GameInfo.getInstance().reset();
    }

    @Test
    void addPlayer1() {
        String name = "Hasan";
        ArrayList<Player> list = new ArrayList<>();
        Player player = new Player(name);
        list.add(player);
        GameInfo.getInstance().addPlayer(name);
        assertEquals(list, GameInfo.getInstance().getPlayerList());
    }
    @Test
    void addPlayer2() {
        String name = "Hasan";
        ArrayList<Player> list = new ArrayList<>();
        Player player = new Player(name);
        list.add(player);
        GameInfo.getInstance().addPlayer(player);
        assertEquals(list, GameInfo.getInstance().getPlayerList());

        GameInfo.getInstance().setPlayerList(new ArrayList<>());


    }

    @Test
    void hasPlayer() {
        Player player = new Player("Test");
        GameInfo.getInstance().addPlayer(player);
        // Test1
        assertTrue(GameInfo.getInstance().hasPlayer(player.getName()));
        // Test2
        assertFalse(GameInfo.getInstance().hasPlayer("1"));
    }

    @Test
    void isListEmpty() {
        // Test1
        assertTrue(GameInfo.getInstance().isListEmpty());
        Player player = new Player("Test");
        GameInfo.getInstance().addPlayer(player);
        // Test2
        assertFalse(GameInfo.getInstance().isListEmpty());
    }

    @Test
    void checkReadiness() {
        // Test1
        GameInfo.getInstance().addPlayer("0", "White", "Host");
        GameInfo.getInstance().addPlayer("1", "White", "Not Ready");
        GameInfo.getInstance().addPlayer("2", "White", "Not Ready");

        assertEquals(2, GameInfo.getInstance().checkReadiness());

        // Test2
        GameInfo.getInstance().getPlayer("1").setReadiness();
        assertEquals(1, GameInfo.getInstance().checkReadiness());

        // Test3
        GameInfo.getInstance().getPlayer("2").setReadiness();
        assertEquals(0, GameInfo.getInstance().checkReadiness());

    }

    @Test
    void reset() {
        // Test1
        GameInfo.getInstance().addPlayer("Test");
        Deque<String> deque = new LinkedList<>();
        deque.addLast("Test");
        GameInfo.getInstance().setPlayerQueue(deque);

        assertFalse(GameInfo.getInstance().isListEmpty());
        assertFalse(GameInfo.getInstance().getPlayerQueue().isEmpty());

        GameInfo.getInstance().reset();

        assertTrue(GameInfo.getInstance().isListEmpty());
        assertTrue(GameInfo.getInstance().getPlayerQueue().isEmpty());

    }

    @Test
    void isCurrentPlayer() {
        // Test1
        Deque<String> deque = new LinkedList<>();
        Player player1 = new Player("Test1");
        Player player2 = new Player("Test2");
        deque.addLast(player1.getName());
        deque.addLast(player2.getName());
        GameInfo.getInstance().setPlayerQueue(deque);

        assertTrue(GameInfo.getInstance().isCurrentPlayer(player1.getName()));

        // Test2
        assertFalse(GameInfo.getInstance().isCurrentPlayer(player2.getName()));


    }

    @Test
    void isFull() {
        // Test1
        GameInfo.getInstance().addPlayer("1");
        GameInfo.getInstance().addPlayer("2");
        GameInfo.getInstance().addPlayer("3");
        GameInfo.getInstance().addPlayer("4");
        GameInfo.getInstance().addPlayer("5");

        assertFalse(GameInfo.getInstance().isFull());

        // Test2
        GameInfo.getInstance().addPlayer("6");
        GameInfo.getInstance().addPlayer("7");
        GameInfo.getInstance().addPlayer("8");
        GameInfo.getInstance().addPlayer("9");
        GameInfo.getInstance().addPlayer("10");
        GameInfo.getInstance().addPlayer("11");
        GameInfo.getInstance().addPlayer("12");

        assertTrue(GameInfo.getInstance().isFull());
    }

    @Test
    void nextTurn() {
        // Test1
        Deque<String> deque = new LinkedList<>();
        Player player1 = new Player("Test1");
        Player player2 = new Player("Test2");
        Player player3 = new Player("Test3");
        deque.addLast(player1.getName());
        deque.addLast(player2.getName());
        deque.addLast(player3.getName());
        GameInfo.getInstance().setPlayerQueue(deque);
        assertEquals(player1.getName(), GameInfo.getInstance().getCurrentPlayerName());
        GameInfo.getInstance().nextTurn();
        assertEquals(player2.getName(), GameInfo.getInstance().getCurrentPlayerName());
        GameInfo.getInstance().nextTurn();
        assertEquals(player3.getName(), GameInfo.getInstance().getCurrentPlayerName());

        //-------
        GameInfo.getInstance().reset();
        //-------

        // Test2
        Executable executable = () -> GameInfo.getInstance().nextTurn();
        assertThrows(NoSuchElementException.class, executable);

    }

}