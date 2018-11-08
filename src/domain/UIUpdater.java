package domain;

import domain.listeners.MessageChangedListener;

import java.util.ArrayList;

public class UIUpdater {
    private static UIUpdater ourInstance;

    private ArrayList<MessageChangedListener> messageChangedListeners;

    String message;

    public static UIUpdater getInstance() {
        if(ourInstance == null)
            ourInstance = new UIUpdater();
        return ourInstance;
    }

    private UIUpdater() {
        messageChangedListeners = new ArrayList<>();
    }

    public void addMessageChangedListener(MessageChangedListener mcl) {
        messageChangedListeners.add(mcl);
    }

    private void publishMessageChangedEvent() {
        messageChangedListeners.forEach(MessageChangedListener::onMessageChangedEvent);
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
        publishMessageChangedEvent();
    }
}
