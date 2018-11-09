package domain;

import domain.controller.PlayerActionController;
import domain.listeners.MessageChangedListener;
import domain.listeners.TurnChangedListener;

import java.util.ArrayList;

public class UIUpdater {
    private static UIUpdater ourInstance;

    private ArrayList<MessageChangedListener> messageChangedListeners;
    private ArrayList<TurnChangedListener> turnChangedListeners;

    String message;

    public static UIUpdater getInstance() {
        if(ourInstance == null)
            ourInstance = new UIUpdater();
        return ourInstance;
    }

    private UIUpdater() {
        messageChangedListeners = new ArrayList<>();
        turnChangedListeners = new ArrayList<>();
    }

    public void addMessageChangedListener(MessageChangedListener mcl) {
        messageChangedListeners.add(mcl);
    }

    private void publishMessageChangedEvent() {
        messageChangedListeners.forEach(MessageChangedListener::onMessageChangedEvent);
    }

    public void addTurnChangedLListener(TurnChangedListener tcl){
        turnChangedListeners.add(tcl);
    }

    private void publishTurnChangedEvent(boolean isEnabled){
        for(TurnChangedListener tcl : turnChangedListeners) {
            tcl.onTurnChangedEvent(isEnabled);
        }
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
        publishMessageChangedEvent();
    }

    public void turnUpdate() {
        publishTurnChangedEvent(GameLogic.getInstance().getPlayers().peekFirst()
                .equals(GameLogic.getInstance().getPlayerList().get(0)));

    }
}
