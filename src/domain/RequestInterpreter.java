package domain;

import domain.interpreter.*;

import java.util.HashMap;

/**
 * Planned as a Class that interprets received Message then updates game state
 */
public class RequestInterpreter {
    private static RequestInterpreter instance;

    private HashMap<Character, Interpreter> interpreterMap;

    private RequestInterpreter() {
        Interpreter moveInterpreter = new MoveInterpreter();
        Interpreter moneyChangeInterpreter = new MoneyChangeInterpreter();
        Interpreter buyInterpreter = new BuyInterpreter();
        Interpreter payRentInterpreter = new PayRentInterpreter();
        Interpreter queueInterpreter = new QueueInterpreter();
        Interpreter upDownInterpreter = new UpDownInterpreter();
        Interpreter tokenMovementInterpreter = new TokenMovementInterpreter();
        Interpreter rollInterpreter = new RollInterpreter();
        Interpreter specialSquareInterpreter = new SpecialSquareInterpreter();
        Interpreter jailInterpreter = new JailInterpreter();

        interpreterMap = new HashMap<>();
        interpreterMap.put(GameLogic.moveFlag, moveInterpreter);
        interpreterMap.put(GameLogic.moneyFlag, moneyChangeInterpreter);
        interpreterMap.put(GameLogic.buyFlag, buyInterpreter);
        interpreterMap.put(GameLogic.payRentFlag, payRentInterpreter);
        interpreterMap.put(GameLogic.queueFlag, queueInterpreter);
        interpreterMap.put(GameLogic.upgradeFlag, upDownInterpreter);
        interpreterMap.put(GameLogic.downgradeFlag, upDownInterpreter);
        interpreterMap.put(GameLogic.tokenFlag, tokenMovementInterpreter);
        interpreterMap.put(GameLogic.rollFlag, rollInterpreter);
        interpreterMap.put(GameLogic.specialSquareFlag, specialSquareInterpreter);
        interpreterMap.put(GameLogic.jailFlag, jailInterpreter);
        interpreterMap.put(GameLogic.goOutJailFlag,jailInterpreter);
    }

    public static RequestInterpreter getInstance() {
        if (instance == null)
            instance = new RequestInterpreter();
        return instance;
    }


    public synchronized void interpret(String m) {
        char flag = m.charAt(0);

        if (interpreterMap.keySet().contains(flag))
            interpreterMap.get(flag).interpret(m.split("[|]"));

        switch (flag) {
            case GameLogic.finishTurnFlag:
                interpretFinishTurn();
                break;
            case GameLogic.closeFlag:
                interpretClose();
                break;
            case GameLogic.removeFlag:
                interpretRemove(m.substring(1));
                break;
            default:
                break;
        }
    }

    private void interpretRemove(String name) {
        GameLogic.getInstance().removePlayer(name);
        UIUpdater.getInstance().removeUpdate(name);
    }


//    private void interpretTokenMovement(String message) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
//        String name = null;
//        Token token = null;
//        Player p = null;
//        try {
//            name = objectMapper.readValue(message, Player.class).getName();
//            token = objectMapper.readValue(message, Player.class).getToken();
//            p = objectMapper.readValue(message, Player.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//        int xLoc = 0;
//        int yLoc = 0;
//        int[] newLoc = token.getLocation();
//
//        int pIndex = GameLogic.getInstance().getPlayerList().indexOf(p);
//
//        if (newLoc[0] == 0) {
//            if (newLoc[1] >= 0 && newLoc[1] < 14) {
//                if (pIndex > 6) {
//                    yLoc = 90 + newLoc[1] * 59 + 15;
//                    xLoc = 1240 + (pIndex - 6) * 15;
//                } else {
//                    yLoc = 90 + newLoc[1] * 59;
//                    xLoc = 1240 + pIndex * 15;
//                }
//            } else if (newLoc[1] >= 14 && newLoc[1] < 28) {
//                if (pIndex > 6) {
//                    yLoc = 905 + (pIndex - 6) * 15;
//                    xLoc = (1240 - (newLoc[1] - 14) * 89) + 44 + 15; //14
//                } else {
//                    yLoc = 905 + pIndex * 15;
//                    xLoc = (1240 - (newLoc[1] - 14) * 89) + 44;
//                }//14
//            } else if (newLoc[1] >= 28 && newLoc[1] < 42) {
//                if (pIndex > 6) {
//                    yLoc = 905 - (newLoc[1] - 28) * 59 + 15;
//                    xLoc = 30 + (pIndex - 6) * 15;
//                } else {
//                    yLoc = 905 - (newLoc[1] - 28) * 59;
//                    xLoc = 30 + pIndex * 15;
//                }
//            } else if (newLoc[1] >= 42 && newLoc[1] < 56) {
//                if (pIndex > 6) {
//                    yLoc = 90 - (pIndex - 6) * 15;
//                    xLoc = (30 + (newLoc[1] - 42) * 89) + 44 + 15; //42
//                } else {
//                    yLoc = 90 - pIndex * 15;
//                    xLoc = (30 + (newLoc[1] - 42) * 89) + 44; //42
//                }
//            }
//        } else if (newLoc[0] == 1) {
//            if (newLoc[1] >= 0 && newLoc[1] < 10) {
//                if (pIndex > 6) {
//                    yLoc = 210 + newLoc[1] * 57 + 15;
//                    xLoc = 1070 + (pIndex - 6) * 15;
//                } else {
//                    yLoc = 210 + newLoc[1] * 57;
//                    xLoc = 1070 + pIndex * 15;
//                }
//            } else if (newLoc[1] >= 10 && newLoc[1] < 20) {
//                if (pIndex > 6) {
//                    yLoc = 790 + (pIndex - 6) * 15;
//                    xLoc = (1070 - (newLoc[1] - 10) * 78) + 39 + 15; //10
//                } else {
//                    yLoc = 790 + +pIndex * 15;
//                    xLoc = (1070 - (newLoc[1] - 10) * 78) + 39; //10
//                }
//            } else if (newLoc[1] >= 20 && newLoc[1] < 30) {
//                if (pIndex > 6) {
//                    yLoc = 790 - (newLoc[1] - 20) * 57 + 15;
//                    xLoc = 200 + (pIndex - 6) * 15;
//                } else {
//                    yLoc = 790 - (newLoc[1] - 20) * 57;
//                    xLoc = 200 + pIndex * 15;
//                }
//            } else if (newLoc[1] >= 30 && newLoc[1] < 40) {
//                if (pIndex > 6) {
//                    yLoc = 210 - (pIndex - 6) * 15;
//                    xLoc = (200 + (newLoc[1] - 30) * 78) + 39 + 15; //30
//                } else {
//                    yLoc = 210 - pIndex * 15;
//                    xLoc = (200 + (newLoc[1] - 30) * 78) + 39; //30
//                }
//            }
//        } else if (newLoc[0] == 2) {
//            if (newLoc[1] >= 0 && newLoc[1] < 6) {
//                if (pIndex > 6) {
//                    yLoc = 325 + newLoc[1] * 58 + 15;
//                    xLoc = 905 + (pIndex - 6) * 15;
//                } else {
//                    yLoc = 325 + newLoc[1] * 58;
//                    xLoc = 905 + pIndex * 15;
//                }
//            } else if (newLoc[1] >= 6 && newLoc[1] < 12) {
//                if (pIndex > 6) {
//                    yLoc = 675 + (pIndex - 6) * 15;
//                    xLoc = (905 - (newLoc[1] - 6) * 75) + 37 + 15; //6
//                } else {
//                    yLoc = 675 + pIndex * 15;
//                    xLoc = (905 - (newLoc[1] - 6) * 75) + 37; //6
//                }
//            } else if (newLoc[1] >= 12 && newLoc[1] < 18) {
//                if (pIndex > 6) {
//                    yLoc = 675 - (newLoc[1] - 12) * 58 + 15;
//                    xLoc = 370 + (pIndex - 6) * 15;
//                } else {
//                    yLoc = 675 - (newLoc[1] - 12) * 58;
//                    xLoc = 370 + pIndex * 15;
//                }
//            } else if (newLoc[1] >= 18 && newLoc[1] < 24) {
//                if (pIndex > 6) {
//                    yLoc = 325 - (pIndex - 6) * 15;
//                    xLoc = (370 + (newLoc[1] - 18) * 75) + 37 + 15; //18
//                } else {
//                    yLoc = 325 - pIndex * 15;
//                    xLoc = (370 + (newLoc[1] - 18) * 75) + 37; //18
//                }
//            }
//        }
//        UIUpdater.getInstance().setTokenLocation(name, xLoc, yLoc);
//    }

//    private void interpretUpDownGrade(String message) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
//        String name = null;
//        DeedSquare square = null;
//        int balance = 0;
//
//        String playerMessage = message.substring(1, message.indexOf('~'));
//        String squareMessage = message.substring(message.indexOf('~') + 1);
//
//        try {
//            name = objectMapper.readValue(playerMessage, Player.class).getName();
//            square = objectMapper.readValue(squareMessage, DeedSquare.class);
//            balance = objectMapper.readValue(playerMessage, Player.class).getBalance();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        GameLogic.getInstance().getPlayer(name).setBalance(balance);
//        if (Board.getInstance().getNameGivenSquare(square.getName()) instanceof Property) {
//            try {
//                square = objectMapper.readValue(squareMessage, Property.class);
//                ((Property) Board.getInstance().getNameGivenSquare(square.getName())).setBuildingList(((Property) square).getBuildingList());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else {
//            try {
//                square = objectMapper.readValue(squareMessage, Railroad.class);
//                ((Railroad) Board.getInstance().getNameGivenSquare(square.getName())).setHasDepot(true);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        int index;
//        if (square instanceof Railroad) {
//            index = GameLogic.getInstance().getPlayer(name).getOwnedRailroads().indexOf(square);
//            GameLogic.getInstance().getPlayer(name).getOwnedRailroads().get(index).setHasDepot(true);
//
//        } else if (square instanceof Property) {
//            index = GameLogic.getInstance().getPlayer(name).getOwnedProperties().indexOf(square);
//            GameLogic.getInstance().getPlayer(name).getOwnedProperties().get(index).setBuildingList(((Property) square).getBuildingList());
//        }
//        if (message.charAt(0) == GameLogic.upgradeFlag)
//            UIUpdater.getInstance().setMessage(name + " upgraded " + square);
//        else
//            UIUpdater.getInstance().setMessage(name + " downgraded " + square);
//
//    }

    private void interpretClose() {
        UIUpdater.getInstance().close();
    }

    private void interpretFinishTurn() {
        GameLogic.getInstance().switchTurn();
        UIUpdater.getInstance().turnUpdate();
    }


}
