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
        Interpreter startInterpreter = new StartInterpreter();

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
        interpreterMap.put(Flags.getFlag("Start"), startInterpreter);
    }

    public static RequestInterpreter getInstance() {
        if (instance == null)
            instance = new RequestInterpreter();
        return instance;
    }


    public synchronized void interpret(String m) {
        System.out.println("\n\nREqInterpreter: interpret\n\n");
        char flag = m.charAt(0);

        if (interpreterMap.keySet().contains(flag))
            interpreterMap.get(flag).interpret(m.split("[|]"));
//
//        switch (flag) {
//            case GameLogic.finishTurnFlag:
//                interpretFinishTurn();
//                break;
//            case GameLogic.closeFlag:
//                interpretClose();
//                break;
//            case GameLogic.removeFlag:
//                interpretRemove(m.substring(1));
//                break;
//            default:
//                break;
//        }
    }

    private void interpretRemove(String name) {
        GameLogic.getInstance().removePlayer(name);
        UIUpdater.getInstance().removeUpdate(name);
    }

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
