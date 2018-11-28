package domain.client;

import domain.server.listeners.*;
import gui.UIFacade;

import java.util.ArrayList;

public class UIUpdater {
    private static UIUpdater ourInstance;

    private ArrayList<MessageChangedListener> messageChangedListeners;
    private ArrayList<TurnChangedListener> turnChangedListeners;
    private ArrayList<CloseButtonListener> closeButtonListeners;
    private ArrayList<PlayerQuitEventListener> playerQuitEventListeners;
    private ArrayList<TokenMovementListener> tokenMovementListeners;

    private String message;

    public static UIUpdater getInstance() {
        if (ourInstance == null)
            ourInstance = new UIUpdater();
        return ourInstance;
    }

    private UIUpdater() {
        messageChangedListeners = new ArrayList<>();
        turnChangedListeners = new ArrayList<>();
        closeButtonListeners = new ArrayList<>();
        playerQuitEventListeners = new ArrayList<>();
        tokenMovementListeners = new ArrayList<>();
    }

    public void addMessageChangedListener(MessageChangedListener mcl) {
        messageChangedListeners.add(mcl);
    }

    private void publishMessageChangedEvent() {
        messageChangedListeners.forEach(MessageChangedListener::onMessageChangedEvent);
    }

    public void addTokenMovementListeners(TokenMovementListener tml) {
        tokenMovementListeners.add(tml);
    }


    public void addTurnChangedListener(TurnChangedListener tcl) {
        turnChangedListeners.add(tcl);
    }

    public void addCloseButtonListener(CloseButtonListener cbl) {
        closeButtonListeners.add(cbl);
    }


    private void publishTokenMovementEvent(String name, int x, int y) {
        for (TokenMovementListener tml : tokenMovementListeners) {
            tml.onTokenMovement(name, x, y);
        }
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

//    public void turnUpdate() {
//        publishTurnChangedEvent(GameLogic.getInstance().getCurrentPlayer()
//                .equals(GameLogic.getInstance().getPlayerList().get(0)));
//
//    }

    void close() {
        publishCloseButtonEvent();
    }

    void removeUpdate(String name) {
        publishPlayerQuitEvent(name);
    }

    public void addPlayerQuitEventListener(PlayerQuitEventListener pqel) {
        playerQuitEventListeners.add(pqel);
    }

    private void publishPlayerQuitEvent(String name) {
        for (PlayerQuitEventListener pqel : playerQuitEventListeners) {
            if (pqel == null) continue;
            pqel.onPlayerQuitEvent(name);
        }
    }

    public void setTokenLocation(String name, int x, int y) {
        this.publishTokenMovementEvent(name, x, y);
    }

    public void showPrompt(char flag) {
        UIFacade.getInstance().generatePrompt(flag);
    }

    public void showPrompt(char flag, int count) {
        UIFacade.getInstance().generatePrompt(flag, count);
    }

    public void changePanel(String panel) { // Changes the panel to lobby for now
        UIFacade.getInstance().changePanel(panel);
    }
}
