package domain.server.util;

import domain.util.Flags;

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
        return flag + "|" +name;
//        if (flag == rollFlag) {
//            return generateRollAction(rollFlag, name);
//        } else if (flag == buyFlag) {
//            return generateBuyAction(buyFlag, name);
//        } else if (flag == payRentFlag) {
//            return generateRentAction(payRentFlag, name);
//        } else if (flag == finishTurnFlag) {
//            return generateFinishTurnAction(finishTurnFlag);
//        } else if (flag == queueFlag) {
//            return generateQueueAction(queueFlag);
//        }

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

    public String generateCurrentAction(char flag, String name, String message) {
        return flag + "|" + name + "|" + message;
    }

    public String generateCurrentAction(char flag, int count, String message) {
        return flag + "|" + count + "|" + message;
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
//    private String generateQueueAction(char flag) {
//        return flag + "|" + MessageConverter.convertQueueToString(GameLogic.getInstance().getPlayers());
//    }
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
//    private String generateRollAction(char flag, String name) {
//        int[] faceVal = GameLogic.getInstance().getPlayer(name).getFaceValues();
//        return flag + "|" + name + "|" + MessageConverter.convertArrayToString(faceVal);
//    }
//
//    private String generateJailAction(char flag, String name) {
//        return flag + "|" + name;
//    }
//
//    private String generatePoolAction(char flag, int changedMoney) {
//        return flag + "|" + changedMoney;
//    }

}
