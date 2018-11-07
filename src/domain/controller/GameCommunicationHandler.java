package domain.controller;

import domain.GameState;
import domain.MessageInterpreter;
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

    public void turnFinished() {
        ClientFacade.getInstance().send(GameState.getInstance().generateGameState());
    }


    @Override
    public void onReceivedChangedEvent(ClientFacade clientFacade) {
        MessageInterpreter.getInstance().interpret(clientFacade.getMessage());
    }
}
