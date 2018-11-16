package domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.board.Board;
import domain.player.Player;

import java.io.IOException;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Planned as a Class that interprets received Message then updates game state
 */
public class MessageInterpreter {
    private static MessageInterpreter instance;


    private MessageInterpreter() {

    }

    public static MessageInterpreter getInstance() {
        if (instance == null)
            instance = new MessageInterpreter();
        return instance;
    }


    public synchronized void interpret(String m) {
        char flag = m.charAt(0);
        switch (flag) {
            case GameLogic.rollFlag:
                interpretRoll(m.substring(1));
                break;
            case GameLogic.buyFlag:
                break;
            case GameLogic.payRentFlag:
                break;
            case GameLogic.finishTurnFlag:
                interpretFinishTurn();
                break;
            case GameLogic.bonusFlag:
                break;
            case GameLogic.queueFlag:
                interpretQueue(m.substring(1));
                break;
            case GameLogic.closeFlag:
                interpretClose();
                break;
            case GameLogic.moveFlag:
                interpretMove(m.substring(1));
                break;
            case GameLogic.increaseMoneyFlag:
                interpretMoneyChange(m.substring(1));
                break;
            default:
                break;
        }
    }

    private void interpretMoneyChange(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        int money = 0;
        String name = null;
        try {
            money = objectMapper.readValue(message, Player.class).getBalance();
            name = objectMapper.readValue(message, Player.class).getName();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int changedMoney = money - GameLogic.getInstance().getPlayer(name).getBalance();

        GameLogic.getInstance().getPlayer(name).setBalance(money);
        if(changedMoney > 0) UIUpdater.getInstance().setMessage(name + " 's money increased by " + changedMoney);
        else UIUpdater.getInstance().setMessage(name + " 's money decreased by " + changedMoney);
    }

    private void interpretMove(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        int[] location = new int[2];
        String name = null;
        try {
            location = objectMapper.readValue(message, Player.class).getToken().getLocation();
            name = objectMapper.readValue(message, Player.class).getName();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(name + " in the location " + Arrays.toString(location));

        GameLogic.getInstance().getPlayer(name).getToken().setLocation(location);

        UIUpdater.getInstance().setMessage(name + " moved to " + Board.getInstance().getSquare(location[0],location[1]).getName()); //TODO Mrmonopoly
    }

    private void interpretClose() {
        UIUpdater.getInstance().close();
    }

    private void interpretQueue(String q) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        try {
            Player[] players = objectMapper.readValue(q, Player[].class);
//            System.out.println(Arrays.toString(objectMapper.readValue(q, Player[].class)));
            Deque<Player> temp = new LinkedList<>();
            for (int i = 0; i < players.length; i++) {
                temp.addLast(players[i]);
            }
            GameLogic.getInstance().setPlayers(temp);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Bots play on only host's program
        if(GameLogic.getInstance().getPlayerList().get(0).getReadiness().equals("Host")) {

            // Check for bots if they starts the game.
            for (Player player : GameLogic.getInstance().getPlayerList()) {
                if (player.getReadiness().equals("Bot") && ((RandomPlayer) player).checkTurn()) {
                    break;
                }
            }
        }
        UIUpdater.getInstance().turnUpdate();
    }

    private void interpretFinishTurn() {
        GameLogic.getInstance().switchTurn();

        UIUpdater.getInstance().turnUpdate();
    }

    private void interpretRoll(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        int[] location = new int[2];
        String name = null;
        int[] faceValues = new int[3];
        try {
            location = objectMapper.readValue(message, Player.class).getToken().getLocation();
            name = objectMapper.readValue(message, Player.class).getName();
            faceValues = objectMapper.readValue(message, Player.class).getFaceValues();
        } catch (IOException e) {
            e.printStackTrace();
        }

        GameLogic.getInstance().getPlayer(name).getToken().setLocation(location);

        UIUpdater.getInstance().setMessage(name + " rolled " + faceValues[0] + " " + faceValues[1] + " " + faceValues[2]); //TODO Mrmonopoly
    }


}
