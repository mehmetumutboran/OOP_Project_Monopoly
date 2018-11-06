package domain.controller;

import domain.listeners.MessageChangedListener;

import java.util.ArrayList;

public class GamePlayHandler {
    private static GamePlayHandler ourInstance = new GamePlayHandler();

    private String message;
    private ArrayList<MessageChangedListener> messageChangedListeners;

    private GamePlayHandler() {
        messageChangedListeners = new ArrayList<>();
    }

    public static GamePlayHandler getInstance() {
        return ourInstance;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        publishMessageChangedEvent();
    }

    private void publishMessageChangedEvent() {
        for(MessageChangedListener ml : messageChangedListeners) {
            if(messageChangedListeners == null) continue;
            ml.onMessageChangedEvent();
        }
    }


    public boolean addMessageChangedListener(MessageChangedListener messageChangedListener) {
        return messageChangedListeners.add(messageChangedListener);
    }
}
