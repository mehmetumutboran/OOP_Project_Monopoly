package domain.server.board;

import domain.util.GameInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    private int [] location;
    private String railroad;
    private int roll;

    @BeforeEach
    void setUp() {
        GameInfo.getInstance().reset();
        location = new int[]{1, 13}; //"States Avenue"
        railroad = "Pennsylvania Railroad"; //The closest railroad to States Avenue(2 square away)
        roll = 5;
    }

    @Test
    void railRoadFind() {
        assertEquals(railroad, Board.getInstance().railRoadFind(location, roll)[0].getName()); //If there are railroad roll number away.
        assertNull(Board.getInstance().railRoadFind(location, 1)[0]);//If there are not railroad roll number away.
        location = new int[]{1,5};// "Reading Railroad"
        railroad = "Reading Railroad";
        roll = 8;
        assertEquals(railroad, Board.getInstance().railRoadFind(location, roll)[0].getName());// If the location itself is a railroad.
    }

}