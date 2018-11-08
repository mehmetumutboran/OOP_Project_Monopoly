package domain.controller;

import domain.GameState;
import domain.MessageInterpreter;
import domain.listeners.MessageChangedListener;
import gui.controlDisplay.MessagePanel;
import network.ClientFacade;
import network.listeners.ReceivedChangedListener;

public class GameCommunicationHandler implements ReceivedChangedListener {
    private static GameCommunicationHandler ourInstance = new GameCommunicationHandler();

    private String message;


    private GameCommunicationHandler() {
        ClientFacade.getInstance().addReceivedChangedListener(this);
    }

    public static GameCommunicationHandler getInstance() {
        return ourInstance;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void sendAction(char flag) {
        ClientFacade.getInstance().send(GameState.getInstance().generateGameState(flag));
    }


    @Override
    public void onReceivedChangedEvent(ClientFacade clientFacade) {
        MessageInterpreter.getInstance().interpret(clientFacade.getMessage());
    }

    public void addMessageChangedListener(MessageChangedListener mcl) {

    }
}
