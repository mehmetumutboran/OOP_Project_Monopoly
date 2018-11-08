package domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.player.Player;

import java.io.IOException;

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
                break;
            case GameLogic.bonusFlag:
                break;
            default:
                break;
        }
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
