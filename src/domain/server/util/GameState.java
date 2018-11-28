package domain.server.util;

import domain.server.GameLogic;
import domain.util.MessageConverter;
import domain.server.board.Board;

import static domain.server.GameLogic.*;

public class GameState {
    private static GameState gs;


    private GameState() {
    }

    public static GameState getInstance() {
        if (gs == null) {
            gs = new GameState();
        }

        return gs;
    }

    public String generateCurrentAction(char flag, String name) {
        //TODO
        switch (flag) {
            case rollFlag:
                return generateRollAction(rollFlag, name);
            case buyFlag:
                return generateBuyAction(buyFlag, name);
            case payRentFlag:
                return generateRentAction(payRentFlag, name);
            case finishTurnFlag:
                return generateFinishTurnAction(finishTurnFlag);
            case queueFlag:
                return generateQueueAction(queueFlag);
            case closeFlag:
                return generateCloseAction(closeFlag);
            case removeFlag:
                return generateRemoveAction(removeFlag, name);
            case specialSquareFlag:
                return generateSpecialSquareAction(specialSquareFlag, name);
            case jailFlag:
                return generateJailAction(jailFlag,name);
            case goOutJailFlag:
                return generateJailAction(goOutJailFlag,name);
            default:
                break;
        }
        return generateQueueAction(queueFlag); // TODO This is for writing return it does not changes anything.
    }

    public String generateCurrentAction(char flag, String name, String buildNameOrLoc) {
        switch (flag) {
            case upgradeFlag:
                return generateUpgradeAction(flag, name, buildNameOrLoc);
            case downgradeFlag:
                return generateDowngradeAction(flag, name, buildNameOrLoc);
            case moveFlag:
                return generateMoveAction(moveFlag, name, buildNameOrLoc);
            case tokenFlag:
                return generateTokenMovementAction(tokenFlag, name, buildNameOrLoc);
        }
        return generateQueueAction(queueFlag);
    }

    public String generateCurrentAction(char flag, String name, int changedMoney) {
        switch (flag) {
            case moneyFlag:
                return generateMoneyAction(flag, name, changedMoney);
            case poolFlag:
                return generatePoolAction(flag, changedMoney);
        }
        return generateQueueAction(queueFlag);
    }

    private String generateTokenMovementAction(char flag, String name, String loc) {
        return flag + "|" + name + "|" + loc;
    }

    private String generateSpecialSquareAction(char flag, String name) {
        return flag + "|" + name;
    }

    private String generateRemoveAction(char flag, String name) {
        return flag + "|" + name;
    }

    private String generateMoneyAction(char flag, String name, int changedMoney) {
        return flag + "|" + name + "|" + changedMoney;
    }

    private String generateMoveAction(char flag, String name, String loc) {
        return flag + "|" + name + "|" + loc;
    }

    private String generateDowngradeAction(char flag, String name, String buildName) {
        int[] loc = GameLogic.getInstance().getPlayer(name).getToken().getLocation();
        return flag + "|" + name + "|" + Board.getInstance().getSquare(loc[0], loc[1]) + "|" + buildName;
    }

    private String generateUpgradeAction(char flag, String name, String buildName) {
        int[] loc = GameLogic.getInstance().getPlayer(name).getToken().getLocation();
        return flag + "|" + name + "|" + Board.getInstance().getSquare(loc[0], loc[1]) + "|" + buildName;
    }

    private String generateCloseAction(char flag) {
        return flag + "";
    }

    private String generateQueueAction(char flag) {
        return flag + "|" + MessageConverter.convertQueueToString(GameLogic.getInstance().getPlayers());
    }

    private String generateFinishTurnAction(char flag) {
        return flag + "";
    }

    private String generateRentAction(char flag, String name) {
        int[] loc = GameLogic.getInstance().getPlayer(name).getToken().getLocation();
        return flag + "|" + name + "|" + Board.getInstance().getSquare(loc[0], loc[1]);
    }

    private String generateBuyAction(char flag, String name) {
        int[] loc = GameLogic.getInstance().getPlayer(name).getToken().getLocation();
        return flag + "|" + name + "|" + Board.getInstance().getSquare(loc[0], loc[1]);
    }

    private String generateRollAction(char flag, String name) {
        int[] faceVal = GameLogic.getInstance().getPlayer(name).getFaceValues();
        return flag + "|" + name + "|" + MessageConverter.convertArrayToString(faceVal);
    }

    private String generateJailAction(char flag, String name) {
        return flag + "|" + name;
    }

    private String generatePoolAction(char flag, int changedMoney) {
        return flag + "|" + changedMoney;
    }

}