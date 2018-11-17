package domain.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.GameLogic;
import domain.GameState;
import domain.MessageInterpreter;
import domain.board.DeedSquare;
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

    public void sendAction(char flag) {
        ClientFacade.getInstance().send(GameState.getInstance().generateCurrentAction(flag));
    }

    public void sendupdowngradeAction(char flag, DeedSquare square) {
        ClientFacade.getInstance().send(GameState.getInstance().generateupdownGradeAction(flag, square));

    }

    public void sendTokenMovementAction (char flag, int [] llocation){
        ClientFacade.getInstance().send(GameState.getInstance().generatetokenMovementAction(flag, llocation));
    }

    @Override
    public void onReceivedChangedEvent() {
        MessageInterpreter.getInstance().interpret(ClientFacade.getInstance().getMessage());
    }


    public void sendQueue() {
        ObjectMapper objectMapper = new ObjectMapper();
        String s = null;
        try {
            s = objectMapper.writeValueAsString(GameLogic.getInstance().getPlayers());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        ClientFacade.getInstance().send(GameLogic.queueFlag + s);
    }
}
