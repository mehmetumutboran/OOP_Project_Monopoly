package domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.player.Player;
import gui.controlDisplay.PlayerLabel;

import java.io.IOException;
import java.lang.reflect.Array;
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
        if(instance == null)
            instance = new MessageInterpreter();
        return instance;
    }


    public void interpret(String m) {
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
            default:
                break;
        }
    }

    private void interpretQueue(String q) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        try {
            Player[] players = objectMapper.readValue(q, Player[].class);
            System.out.println(Arrays.toString(objectMapper.readValue(q, Player[].class)));
            Deque<Player> temp = new LinkedList<>();
            for (int i = 0; i < players.length; i++) {
                temp.addLast(players[i]);
            }
            GameLogic.getInstance().setPlayers(temp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        UIUpdater.getInstance().turnUpdate();
    }

    private void interpretFinishTurn() {
        GameLogic.getInstance().switchTurn();
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
