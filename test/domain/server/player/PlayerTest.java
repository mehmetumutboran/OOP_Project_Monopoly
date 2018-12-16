package domain.server.player;

import domain.server.board.Board;
import domain.server.board.Property;
import domain.util.GameInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Player bigger;
    private Player smaller;
    private Player equal;
    private Player player;

    @BeforeEach
    void setUp() {
        GameInfo.getInstance().reset();
        bigger = new Player("Bigger");
        smaller = new Player("Smaller");
        equal = new Player("Equal");

        bigger.setFaceValues(new int[]{6 , 6, 3});
        smaller.setFaceValues(new int[]{1 , 2, 3});



        player = new Player("Player");

        player.getOwnedProperties().add((Property) Board.getInstance().getNameGivenSquare("South Street"));
        player.getOwnedProperties().add((Property) Board.getInstance().getNameGivenSquare("Broad Street"));
        player.getOwnedProperties().add((Property) Board.getInstance().getNameGivenSquare("Walnut Street"));

        player.getOwnedProperties().add((Property) Board.getInstance().getNameGivenSquare("Esplanade Avenue"));

        player.getOwnedProperties().add((Property) Board.getInstance().getNameGivenSquare("South Temple"));
        player.getOwnedProperties().add((Property) Board.getInstance().getNameGivenSquare("West Temple"));
        player.getOwnedProperties().add((Property) Board.getInstance().getNameGivenSquare("North Temple"));
        player.getOwnedProperties().add((Property) Board.getInstance().getNameGivenSquare("Temple Square"));
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

    @Test
    void checkMajority() {
        //since bigger has 3 out of 4 from same colour it should return true
      assertTrue(player.checkMajority((Property) Board.getInstance().getNameGivenSquare("South Street")));
        //since bigger has 1 out of 4 from same colour it should return false
      assertFalse(player.checkMajority((Property) Board.getInstance().getNameGivenSquare("Esplanade Avenue")));
    }

    @Test
    void checkMonopoly() {
        //since bigger has 4 out of 4 from same colour it should return true
        assertTrue(player.checkMonopoly((Property) Board.getInstance().getNameGivenSquare("North Temple")));
        //since bigger has 3 out of 4 from same colour it should return false
        assertFalse(player.checkMonopoly((Property) Board.getInstance().getNameGivenSquare("South Street")));
    }
}