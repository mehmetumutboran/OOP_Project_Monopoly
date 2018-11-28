package domain.util;

import java.util.HashMap;

public class Flags {

    private static final char buyFlag = 'B';
    private static final char rollFlag = 'R';
    private static final char moneyFlag = 'S';
    private static final char payRentFlag = 'P';
    private static final char drawCardFlag = 'C';
    private static final char payDayFlag = 'Y';
    private static final char bonusFlag = 'O';
    private static final char jailFlag = 'J';
    private static final char finishTurnFlag = 'F';
    private static final char queueFlag = 'Q';
    private static final char closeFlag = 'E';
    private static final char upgradeFlag = 'U';
    private static final char downgradeFlag = 'Z';
    private static final char moveFlag = 'M';
    private static final char kickFlag = 'X';
    private static final char specialSquareFlag = 'A';
    private static final char poolFlag = 'H';
    private static final char tokenFlag = 'T';
    private static final char goOutJailFlag = 'G';
    private static final char startFlag = 'D';
    private static final char addPlayerFlag = 'I';

    private static HashMap<String, Character> flagMap;
    static
    {
        flagMap = new HashMap<>();
        flagMap.put("Roll", rollFlag);
        flagMap.put("Start", startFlag);
        flagMap.put("AddPlayer", addPlayerFlag);
        flagMap.put("Kick", kickFlag);
        flagMap.put("Close", closeFlag);
    }

    public static char getFlag(String action){
        return flagMap.get(action);
    }

}
