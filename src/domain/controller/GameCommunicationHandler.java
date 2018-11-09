package domain.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.GameLogic;
import domain.GameState;
import domain.MessageInterpreter;
import domain.listeners.MessageChangedListener;
import gui.controlDisplay.MessagePanel;
import network.ClientFacade;
import network.listeners.ReceivedChangedListener;

import java.util.ArrayList;

public class GameCommunicationHandler implements ReceivedChangedListener {
    private static GameCommunicationHandler ourInstance;

    private String message;


    private GameCommunicationHandler() {
        ClientFacade.getInstance().addReceivedChangedListener(this);
    }

    public static GameCommunicationHandler getInstance() {
        if(ourInstance == null)
             ourInstance = new GameCommunicationHandler();
        return ourInstance;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void sendAction(char flag) {
        ClientFacade.getInstance().send(GameState.getInstance().generateCurrentAction(flag));
    }


    @Override
    public void onReceivedChangedEvent(ClientFacade clientFacade) {
        MessageInterpreter.getInstance().interpret(clientFacade.getMessage());
    }


    public void sendQueue() {
        ObjectMapper objectMapper = new ObjectMapper();
        String s = null;
        try {
            s = objectMapper.writeValueAsString(GameLogic.getInstance().getPlayers());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        ClientFacade.getInstance().send(GameLogic.queueFlag + s);
    }
}
