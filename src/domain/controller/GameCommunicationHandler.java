package domain.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.GameLogic;
import domain.GameState;
import domain.MessageInterpreter;
import network.client.clientFacade.ClientFacade;
import network.listeners.ReceivedChangedListener;

public class GameCommunicationHandler implements ReceivedChangedListener {
    private static GameCommunicationHandler ourInstance;

    private String message;


    private GameCommunicationHandler() {
        ClientFacade.getInstance().addReceivedChangedListener(this);
    }

    public static GameCommunicationHandler getInstance() {
        if (ourInstance == null)
            ourInstance = new GameCommunicationHandler();
        return ourInstance;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public synchronized void sendAction(char flag, String name) {
        ClientFacade.getInstance().send(GameLogic.getInstance().getCurrentPlayer().getName(),
                GameState.getInstance().generateCurrentAction(flag, name));
    }

    public synchronized void sendAction(char flag, String name, String buildName) {
        ClientFacade.getInstance().send(GameLogic.getInstance().getCurrentPlayer().getName(),
                GameState.getInstance().generateCurrentAction(flag, name, buildName));
    }

    public synchronized void sendAction(char flag, String name, int changedMoney) {
        ClientFacade.getInstance().send(GameLogic.getInstance().getCurrentPlayer().getName(),
                GameState.getInstance().generateCurrentAction(flag, name, changedMoney));
    }

    @Override
    public void onReceivedChangedEvent() {
        MessageInterpreter.getInstance().interpret(ClientFacade.getInstance().getMessage());
    }


    void sendQueue() {
        ObjectMapper objectMapper = new ObjectMapper();
        String s = null;
        try {
            s = objectMapper.writeValueAsString(GameLogic.getInstance().getPlayers());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        ClientFacade.getInstance().send(GameLogic.getInstance().getCurrentPlayer().getName(), GameLogic.queueFlag + s);
    }

}
