package domain.util;

import domain.server.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

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
    void getInstance() {
    }

    @Test
    void getPlayerQueue() {
    }

    @Test
    void getPlayerList() {
    }

    @Test
    void getPlayerListName() {
    }

    @Test
    void getPlayerListColor() {
    }

    @Test
    void setPlayerQueue() {
    }

    @Test
    void setPlayerList() {
    }

    @Test
    void getCurrentPlayer() {
    }

    @Test
    void getPlayer() {
    }

    @Test
    void getMyself() {
    }

    @Test
    void getPeek() {
    }

    @Test
    void isMyselfHost() {
    }

    @Test
    void hasPlayer() {
        Player player = new Player("Test");
        GameInfo.getInstance().addPlayer(player);
        assertTrue(GameInfo.getInstance().hasPlayer(player.getName()));
        assertFalse(GameInfo.getInstance().hasPlayer("1"));
    }

    @Test
    void isListEmpty() {
        assertTrue(GameInfo.getInstance().isListEmpty());
        Player player = new Player("Test");
        GameInfo.getInstance().addPlayer(player);
        assertFalse(GameInfo.getInstance().isListEmpty());
    }

    @Test
    void addPlayer() {
    }

    @Test
    void addPlayer1() {
    }

    @Test
    void addPlayer2() {
    }

    @Test
    void hasColor() {
    }

    @Test
    void setPlayerColor() {
    }

    @Test
    void getPlayersAsString() {
    }

    @Test
    void addPlayerListChangedListener() {
    }

    @Test
    void getPlayerConnectAttributes() {
    }

    @Test
    void setReadiness() {
    }

    @Test
    void checkReadiness() {
    }

    @Test
    void generateSaveInfo() {
    }

    @Test
    void loadPlayer() {
    }

    @Test
    void getNameFromIndex() {
    }

    @Test
    void getLayerFromIndex() {
    }

    @Test
    void getLocationFromIndex() {
    }

    @Test
    void getBalanceFromIndex() {
    }

    @Test
    void getPropertyFromIndex() {
    }

    @Test
    void getUtilityFromIndex() {
    }

    @Test
    void getRailRoadFromIndex() {
    }

    @Test
    void getPlayerListSize() {
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
    void removePlayer() {
    }

    @Test
    void isCurrentPlayer() {
    }

    @Test
    void isBot() {
    }

    @Test
    void getColorFromIndex() {
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

    @Test
    void isPeekBot() {
    }

    @Test
    void getCurrentPlayerName() {
    }

    @Test
    void getLocationFromName() {
    }

    @Test
    void isCurrentPlayerFromIndex() {
    }

    @Test
    void getMortgagedFromIndex() {
    }
}