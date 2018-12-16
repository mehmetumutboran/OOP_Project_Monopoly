package domain.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlagsTest {

    private char test1;
    private char test2;

    private String action1;
    private String action2;

    @BeforeEach
    void setUp() {
        test1 = 'B';//Roll
        test2 = 'N';//Move
        action1 = "Roll";
        action2 = "Move";
    }

    @Test
    void getFlag() {
        assertEquals(test1, Flags.getFlag(action1));
        assertEquals(test2, Flags.getFlag(action2));
    }
}