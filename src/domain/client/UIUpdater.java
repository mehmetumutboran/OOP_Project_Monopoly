package domain.client;

import domain.server.listeners.*;
import domain.util.Flags;
import gui.UIFacade.UIFacade;

import java.util.ArrayList;

public class UIUpdater {
    private static UIUpdater ourInstance;

    private ArrayList<MessageChangedListener> messageChangedListeners;
    private ArrayList<TurnChangedListener> turnChangedListeners;
    private ArrayList<TurnUpdateListener> turnUpdateListeners;
    private ArrayList<CloseButtonListener> closeButtonListeners;
    private ArrayList<PlayerQuitEventListener> playerQuitEventListeners;
    private ArrayList<TokenMovementListener> tokenMovementListeners;
    private ArrayList<GameStartedListener> gameStartedListeners;
    private ArrayList<ButtonChangeListener> buttonChangeListeners;
    private ArrayList<LabelChangeListener> labelChangeListeners;
    private ArrayList<DiceRolledListener> diceRolledListeners;
    private String message;
    private String buttonLayout = "000000000000";
    private String defaultLayout = "000000000000";

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
        gameStartedListeners = new ArrayList<>();
        buttonChangeListeners = new ArrayList<>();
        turnUpdateListeners = new ArrayList<>();
        labelChangeListeners = new ArrayList<>();
        diceRolledListeners = new ArrayList<>();
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

    public void addButtonChangeListener(ButtonChangeListener bcl) {
        buttonChangeListeners.add(bcl);
    }

    public void addTurnUpdateListener(TurnUpdateListener tul) {
        turnUpdateListeners.add(tul);
    }

    public void addLabelChangeListener(LabelChangeListener lcl) {
        labelChangeListeners.add(lcl);
    }

    public void addDiceRolledListener(DiceRolledListener drl) {
        diceRolledListeners.add(drl);
    }

    public void publishGameStartedEvent() {
        for (GameStartedListener gls : gameStartedListeners) {
            if (gls == null) continue;
            gls.onGameStartedEvent();
        }
    }

    public void addGameStartedListener(GameStartedListener gsl) {
        gameStartedListeners.add(gsl);
    }


    private void publishTokenMovementEvent(String name, int x, int y) {
        for (TokenMovementListener tml : tokenMovementListeners) {
            tml.onTokenMovement(name, x, y);
        }
    }

    private void publishTurnChangedEvent(String enable) {
        for (TurnChangedListener tcl : turnChangedListeners) {
            tcl.onTurnChangedEvent(enable);
        }
    }

    public void publishTurnUpdateEvent() {
        for (TurnUpdateListener tul : turnUpdateListeners) {
            tul.onTurnUpdateEvent();
        }
    }

    private void publishButtonChangeEvent() {
        for (ButtonChangeListener bcl : buttonChangeListeners) {
            bcl.onButtonChangeEvent();
        }
    }

    private void publishCloseButtonEvent() {
        for (CloseButtonListener cbl : closeButtonListeners) {
            cbl.onCloseClickedEvent();
        }
    }

    private void publishLabelChangeEvent(ArrayList<int[]> location, String actionType, int i) {
        for (LabelChangeListener lcl : labelChangeListeners) {
            lcl.onLabelChangeEvent(location, actionType, i);
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
        publishTurnUpdateEvent();
        publishTurnChangedEvent(defaultLayout);
    }

    public void pauseUpdate(boolean b, String name) {
        publishTurnChangedEvent(defaultLayout);
        UIFacade.getInstance().generatePrompt(Flags.getFlag("Pause"), b, name);
    }

    void close() {
        publishCloseButtonEvent();
    }

    public void removeUpdate(String name) {
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

    public void showPrompt(char flag, int[] location) {
        UIFacade.getInstance().generatePrompt(flag, location);
    }

    public void changePanel(String panel) { // Changes the panel to lobby for now
        UIFacade.getInstance().changePanel(panel);
    }

    public void startGame() {
        publishGameStartedEvent();
    }

    public void setTitle(String username) {
        UIFacade.getInstance().setTitle(username);
    }

    public void setButtons(String enable) {
        this.buttonLayout = enable;
        publishTurnChangedEvent(enable);
    }

    public void resumeUpdate() {
        UIFacade.getInstance().closePrompt();
        publishTurnChangedEvent(buttonLayout);
    }

//    public void setupPlayerLabels(ArrayList<String> playerListName, ArrayList<String> playerListColor) {
//        publishGameStartedEvent(playerListName, playerListColor);
//    }


    //    public void showList() {
//        UIFacade.getInstance().generateList(GameInfo.getInstance().getPlayerListName(),GameInfo.getInstance().getPlayerListColor());
//    }
    public void updateLabels(ArrayList<int[]> locationlst, String actionType, int i) {
        publishLabelChangeEvent(locationlst, actionType, i);
    }

    public void publishDiceRolledEvent(int[] faces) {
        for (DiceRolledListener drl : diceRolledListeners) {
            drl.onDiceRolledEvent(faces);
        }
    }

    public void setDices(int[] faceValues) {
        publishDiceRolledEvent(faceValues);
    }
}
