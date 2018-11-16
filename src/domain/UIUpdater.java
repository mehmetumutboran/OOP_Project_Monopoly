package domain;

import domain.listeners.CloseButtonListener;
import domain.listeners.MessageChangedListener;
import domain.listeners.TurnChangedListener;

import java.util.ArrayList;

public class UIUpdater {
    private static UIUpdater ourInstance;

    private ArrayList<MessageChangedListener> messageChangedListeners;
    private ArrayList<TurnChangedListener> turnChangedListeners;
    private ArrayList<CloseButtonListener> closeButtonListeners;

    String message;

    public static UIUpdater getInstance() {
        if (ourInstance == null)
            ourInstance = new UIUpdater();
        return ourInstance;
    }

    private UIUpdater() {
        messageChangedListeners = new ArrayList<>();
        turnChangedListeners = new ArrayList<>();
        closeButtonListeners = new ArrayList<>();
    }

    public void addMessageChangedListener(MessageChangedListener mcl) {
        messageChangedListeners.add(mcl);
    }

    private void publishMessageChangedEvent() {
        messageChangedListeners.forEach(MessageChangedListener::onMessageChangedEvent);
    }

    public void addTurnChangedListener(TurnChangedListener tcl) {
        turnChangedListeners.add(tcl);
    }

    public void addCloseButtonListener(CloseButtonListener cbl) {
        closeButtonListeners.add(cbl);
    }

    private void publishTurnChangedEvent(boolean isEnabled) {
        for (TurnChangedListener tcl : turnChangedListeners) {
            tcl.onTurnChangedEvent(isEnabled);
        }
    }

    private void publishCloseButtonEvent() {
        for (CloseButtonListener cbl : closeButtonListeners) {
            cbl.onCloseClickedEvent();
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

    public void close() {
        publishCloseButtonEvent();
    }
}
