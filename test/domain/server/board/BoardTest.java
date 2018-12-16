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

    @Test
    void getSameColoredSquares() {

        //Puts the all squares from the colour pink, calls the method, equal
        Property sq1 = new Property("Lake Street", 0, 1, 30, new int[]{1,5,15,45,80,125,625,0,15,50}, "PINK");
        Property sq2 = new Property("Nicollet Avenue", 0, 3, 30, new int[]{1,5,15,45,80,125,625,0,15,50}, "PINK");
        Property sq3 = new Property("Hennepin Avenue", 0, 4, 60, new int[]{1,5,15,45,120,240,350,850,0,30,50}, "PINK");
        assertArrayEquals(new Property[]{sq1,sq2,sq3} ,Board.getInstance().getSameColoredSquares("PINK"));

        //The squares from one color set pink, with the method call of purple, not equal
        assertNotEquals(new Property[]{sq1,sq2,sq3} ,Board.getInstance().getSameColoredSquares("PURPLE"));



        //Puts the all squares but one from the colour red, calls the method, not equal
        Property sq4 = new Property("Kentucky Avenue", 1, 21, 220, new int[]{18, 90, 250, 700, 875, 1050, 2050,0,100,150}, "RED");
        Property sq5 = new Property("Indiana Avenue", 1, 23, 220, new int[]{18, 90, 250, 700, 875, 1050, 2050,0,100,150}, "RED");
        assertNotEquals(new Property[]{sq4,sq5}, Board.getInstance().getSameColoredSquares("RED"));


    }
}