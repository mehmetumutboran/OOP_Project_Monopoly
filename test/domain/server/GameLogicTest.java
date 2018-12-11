package domain.server;

import domain.server.player.Player;
import domain.util.GameInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GameLogicTest {
    private Player player = new Player("Test");
    private Deque<String> deque;


    private static boolean setUpIsDone = false;

    void setUp() {
        if (setUpIsDone) {
            return;
        }
        GameInfo.getInstance().addPlayer(player);
        deque = new LinkedList<>();
        if (!deque.contains(player.getName()))
            deque.addLast(player.getName());
        GameInfo.getInstance().setPlayerQueue(deque);
        setUpIsDone = true;
    }

    @BeforeEach
    void init() throws Exception {
        setUp();
        if (setUpIsDone) return;
        throw new Exception();
    }

    @Test
    void roll() {
    }

    @Test
    void getTotalRoll() {
        player.setFaceValues(new int[]{5, 5, 7});
        assertEquals(10, GameLogic.getInstance().getTotalRoll(player.getName()));

        player.setFaceValues(new int[]{5, 5, 1});
        assertEquals(11, GameLogic.getInstance().getTotalRoll(player.getName()));

        player.setFaceValues(new int[]{1, 3, 8});
        assertEquals(4, GameLogic.getInstance().getTotalRoll(player.getName()));

        player.setFaceValues(new int[]{2, 2, 4});
        assertEquals(4, GameLogic.getInstance().getTotalRoll(player.getName()));
    }

    @Test
    void checkMrMonopoly() {
    }

    @Test
    void checkSecondTurn() {
    }

    @Test
    void updateDoubleCounter() {
    }

    @Test
    void checkMoveConditions() {
    }

    @Test
    void move() {
        player.setFaceValues(new int[]{5, 5, 1});
        System.out.println(Arrays.toString(player.getFaceValues()));
        GameLogic.getInstance().move(player.getName());
        assertArrayEquals(new int[]{1, 11}, player.getToken().getLocation());
    }
}