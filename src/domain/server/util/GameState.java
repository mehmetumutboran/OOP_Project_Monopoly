package domain.server.util;

import domain.util.Flags;
import domain.util.GameInfo;
import domain.util.MessageConverter;

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
//        if (flag == Flags.getFlag("AddPlayer")) {
//            return flag + name;
//        } else if (flag == Flags.getFlag("AddPlayer")) {
//            return flag + name;
//        } else if (flag == Flags.getFlag("Close")) {
//            return flag + name;
//        }
        //else if (flag == buyFlag) {
//            return generateBuyAction(buyFlag, name);
//        } else if (flag == payRentFlag) {
//            return generateRentAction(payRentFlag, name);
//        } else if (flag == finishTurnFlag) {
//            return generateFinishTurnAction(finishTurnFlag);
// }
//        else if (flag == Flags.queueFlag) {
//            return generateQueueAction(Flags.queueFlag);
//        }
//        else if (flag == Flags.initQueueFlag) {
//            return generateInitQueueAction(Flags.initQueueFlag);
//        }
        return flag + "|" + name;
//        else if (flag == removeFlag) {
//            return generateRemoveAction(removeFlag, name);
//        } else if (flag == specialSquareFlag) {
//            return generateSpecialSquareAction(specialSquareFlag, name);
//        } else if (flag == jailFlag) {
//            return generateJailAction(jailFlag, name);
//        } else if (flag == goOutJailFlag) {
//            return generateJailAction(goOutJailFlag, name);
//        }
//        return generateQueueAction(queueFlag); // TODO This is for writing return it does not changes anything.
    }

    private String generateInitQueueAction(char flag, String qString) {
        return flag + "|" + qString;
    }

    public String generateCurrentAction(char flag) {
        return Character.toString(flag);
    }

//    private String generateInitQueueAction(char flag, int money , String name) {
//        return flag + "|" + money + "|" + name ;
//    }

    public String generateCurrentAction(char flag, String name, String message) {
        if (flag == Flags.getFlag("InitQueue")) {
            return generateInitQueueAction(Flags.getFlag("InitQueue"), message);
        }
        if (flag == Flags.getFlag("Roll")) {
            return generateRollAction(Flags.getFlag("Roll"), name, message);
        }
        return flag + "|" + name + "|" + message;
    }

    public String generateCurrentAction(char flag, int count, String message) {
        return flag + "|" + count + "|" + message;
    }

    public String generateCurrentAction(char flag, String name, int money1, int money2, String squareName) {
        return flag + "|" + name + "|" + money1 + "|" + money2 + "|" + squareName;

    }

    public String generateCurrentAction(char flag, String name, int money, String squareName) {
        return flag + "|" + name + "|" + money + "|" + squareName;

    }
//    public String generateCurrentAction(char flag, String name, String buildNameOrLoc) {
//        if (flag == upgradeFlag) {
//            return generateUpgradeAction(flag, name, buildNameOrLoc);
//        } else if (flag == downgradeFlag) {
//            return generateDowngradeAction(flag, name, buildNameOrLoc);
//        } else if (flag == moveFlag) {
//            return generateMoveAction(moveFlag, name, buildNameOrLoc);
//        } else if (flag == tokenFlag) {
//            return generateTokenMovementAction(tokenFlag, name, buildNameOrLoc);
//        }
//        return generateQueueAction(queueFlag);
//    }

//    public String generateCurrentAction(char flag, String name, int changedMoney) {
//        switch (flag) {
//            case moneyFlag:
//                return generateMoneyAction(flag, name, changedMoney);
//            case poolFlag:
//                return generatePoolAction(flag, changedMoney);
//        }
//        return generateQueueAction(queueFlag);
//    }

    //    private String generateTokenMovementAction(char flag, String name, String loc) {
//        return flag + "|" + name + "|" + loc;
//    }
//
//    private String generateSpecialSquareAction(char flag, String name) {
//        return flag + "|" + name;
//    }
//
//    private String generateRemoveAction(char flag, String name) {
//        return flag + "|" + name;
//    }
//
//    private String generateMoneyAction(char flag, String name, int changedMoney) {
//        return flag + "|" + name + "|" + changedMoney;
//    }
//
//    private String generateMoveAction(char flag, String name, String loc) {
//        return flag + "|" + name + "|" + loc;
//    }
//
//    private String generateDowngradeAction(char flag, String name, String buildName) {
//        int[] loc = GameLogic.getInstance().getPlayer(name).getToken().getLocation();
//        return flag + "|" + name + "|" + Board.getInstance().getSquare(loc[0], loc[1]) + "|" + buildName;
//    }
//
//    private String generateUpgradeAction(char flag, String name, String buildName) {
//        int[] loc = GameLogic.getInstance().getPlayer(name).getToken().getLocation();
//        return flag + "|" + name + "|" + Board.getInstance().getSquare(loc[0], loc[1]) + "|" + buildName;
//    }
//
//    private String generateCloseAction(char flag) {
//        return flag + "";
//    }
//
    private String generateQueueAction(char flag) {
        return flag + "|" + MessageConverter.convertQueueToString(GameInfo.getInstance().getPlayerQueue());
    }

    //
//    private String generateFinishTurnAction(char flag) {
//        return flag + "";
//    }
//
//    private String generateRentAction(char flag, String name) {
//        int[] loc = GameLogic.getInstance().getPlayer(name).getToken().getLocation();
//        return flag + "|" + name + "|" + Board.getInstance().getSquare(loc[0], loc[1]);
//    }
//
//    private String generateBuyAction(char flag, String name) {
//        int[] loc = GameLogic.getInstance().getPlayer(name).getToken().getLocation();
//        return flag + "|" + name + "|" + Board.getInstance().getSquare(loc[0], loc[1]);
//    }
//
    private String generateRollAction(char flag, String name, String message) {
        return flag + "|" + name + "|" + message;
    }

    public String generateCurrentAction(char flag, String player, String square, String buildingToUporDown) {
        return flag + "|" + player + "|" + square + "|" + buildingToUporDown;
    }
//
//    private String generateJailAction(char flag, String name) {
//        return flag + "|" + name;
//    }
//
//    private String generatePoolAction(char flag, int changedMoney) {
//        return flag + "|" + changedMoney;
//    }

}
