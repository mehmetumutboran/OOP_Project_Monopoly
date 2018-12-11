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
    private static final char doubleCounterFlag = 'S';
    private static final char startFlag = 'T';
    private static final char addPlayerFlag = 'U';
    private static final char addPlayerListFlag = 'V';
    private static final char addBotFlag = 'W';
    private static final char colorFlag = 'X';
    private static final char dontChangeColorFlag = 'Y';
    private static final char readinessFlag = 'Z';
    private static final char dontStartFlag = 'a';
    private static final char initQueueFlag = 'b';
    private static final char dontBuyFlag = 'c';
    private static final char dontPayRentFlag = 'd';
    private static final char saveFlag = 'e';
    private static final char pauseFlag = 'f';
    private static final char loadFlag = 'g';
    private static final char resumeFlag = 'h';
    private static final char removeFlag = 'i';
    private static final char fullFlag = 'j';
    private static final char finishFlag = 'k';
    private static final char buttonFlag = 'l';
    private static final char receivedFlag = 'z';

    private static HashMap<String, Character> flagMap;

    static {
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
        flagMap.put("DontStart", dontStartFlag);
        flagMap.put("Queue", queueFlag);
        flagMap.put("InitQueue", initQueueFlag);
        flagMap.put("Save", saveFlag);
        flagMap.put("Pause", pauseFlag);
        flagMap.put("Resume", resumeFlag);
        flagMap.put("Load", loadFlag);
        flagMap.put("Remove", removeFlag);
        flagMap.put("Full", fullFlag);
        flagMap.put("Move", moveFlag);
        flagMap.put("GoToJail", jailFlag);
        flagMap.put("GoOutOfJail", jailFlag);
        flagMap.put("DoubleCounter", doubleCounterFlag);
        flagMap.put("Buy", buyFlag);
        flagMap.put("DontBuy", dontBuyFlag);
        flagMap.put("PayRent", payRentFlag);
        flagMap.put("DontPayRent", dontPayRentFlag);
        flagMap.put("Finish", finishFlag);
        flagMap.put("Button", buttonFlag);
        flagMap.put("Received", receivedFlag);
        flagMap.put("Money", moneyFlag);
        flagMap.put("Upgrade", upgradeFlag);
        flagMap.put("Downgrade", downgradeFlag);
        flagMap.put("Draw", drawCardFlag);
        flagMap.put("PayDay", payDayFlag);
        flagMap.put("Bonus", bonusFlag);
        flagMap.put("Jail", jailFlag);
        flagMap.put("Special", specialSquareFlag);
        flagMap.put("Pool", poolFlag);
        flagMap.put("Token", tokenFlag);

    }

    public static char getFlag(String action) {
//        System.out.println(action);
        return flagMap.get(action);
    }

}
