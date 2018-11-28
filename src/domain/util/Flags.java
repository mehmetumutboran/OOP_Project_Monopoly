package domain.util;

import java.util.HashMap;

public class Flags {

    private static final char buyFlag = 'A';
    private static final char rollFlag = 'B';
    private static final char moneyFlag = 'C';
    private static final char payRentFlag = 'D';
    private static final char drawCardFlag = 'E';
    private static final char payDayFlag = 'F';
    private static final char bonusFlag = 'G';
    private static final char jailFlag = 'H';
    private static final char finishTurnFlag = 'I';
    private static final char queueFlag = 'J';
    private static final char closeFlag = 'K';
    private static final char upgradeFlag = 'L';
    private static final char downgradeFlag = 'M';
    private static final char moveFlag = 'N';
    private static final char kickFlag = 'O';
    private static final char specialSquareFlag = 'P';
    private static final char poolFlag = 'Q';
    private static final char tokenFlag = 'R';
    private static final char goOutJailFlag = 'S';
    private static final char startFlag = 'T';
    private static final char addPlayerFlag = 'U';
    private static final char addPlayerListFlag = 'V';
    private static final char addBotFlag = 'W';
    private static final char colorFlag = 'X';
    private static final char dontChangeColorFlag = 'Y';
    private static final char readinessFlag = 'Z';

    private static HashMap<String, Character> flagMap;
    static
    {
        flagMap = new HashMap<>();
        flagMap.put("Roll", rollFlag);
        flagMap.put("Start", startFlag);
        flagMap.put("AddPlayer", addPlayerFlag);
        flagMap.put("Kick", kickFlag);
        flagMap.put("Close", closeFlag);
        flagMap.put("AddPlayerList", addPlayerListFlag);
        flagMap.put("AddBot", addBotFlag);
        flagMap.put("Color", colorFlag);
        flagMap.put("DontChangeColor", dontChangeColorFlag);
        flagMap.put("Readiness", readinessFlag);
    }

    public static char getFlag(String action){
        return flagMap.get(action);
    }

}
