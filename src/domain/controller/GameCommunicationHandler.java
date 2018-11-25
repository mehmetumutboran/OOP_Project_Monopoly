package domain.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.GameLogic;
import domain.GameState;
import domain.RequestInterpreter;
import network.client.clientFacade.ClientFacade;
import network.listeners.ReceivedChangedListener;
import network.server.serverFacade.ServerFacade;

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

    public synchronized void sendResponse(char flag, String name) {
        ServerFacade.getInstance().send(name,
                GameState.getInstance().generateCurrentAction(flag, name));
    }

    public synchronized void sendResponse(char flag, String name, String buildName) {
        ServerFacade.getInstance().send(name,
                GameState.getInstance().generateCurrentAction(flag, name, buildName));
    }

    public synchronized void sendResponse(char flag, String name, int changedMoney) {
        ServerFacade.getInstance().send(name,
                GameState.getInstance().generateCurrentAction(flag, name, changedMoney));
    }

    @Override
    public void onReceivedChangedEvent() {
        RequestInterpreter.getInstance().interpret(ClientFacade.getInstance().getMessage());
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
