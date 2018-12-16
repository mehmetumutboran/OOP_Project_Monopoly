package domain.server.player;

import domain.util.GameInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Player bigger;
    private Player smaller;
    private Player equal;

    @BeforeEach
    void setUp() {
        GameInfo.getInstance().reset();
        bigger = new Player("Bigger");
        smaller = new Player("Smaller");
        equal = new Player("Equal");
        bigger.setFaceValues(new int[]{6 , 6, 3});
        smaller.setFaceValues(new int[]{1 , 2, 3});
    }

    @Test
    void compareTo() {
        //if this object is bigger it should return -1.
        assertEquals(-1,bigger.compareTo(smaller));
        //if this object is smaller it should return 1.
        assertEquals(1,smaller.compareTo(bigger));
        //if this object is equal to other it should return -1.
        equal.setFaceValues(new int[]{6 , 6, 3});
        assertEquals(-1,equal.compareTo(bigger));
    }
}