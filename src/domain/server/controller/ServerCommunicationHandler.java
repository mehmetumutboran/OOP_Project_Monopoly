package domain.server.controller;

import domain.server.util.GameState;
import network.server.serverFacade.ServerFacade;


public class ServerCommunicationHandler {
    private static ServerCommunicationHandler ourInstance;

    private String message;


    private ServerCommunicationHandler() {
    }

    public static ServerCommunicationHandler getInstance() {
        if (ourInstance == null)
            ourInstance = new ServerCommunicationHandler();
        return ourInstance;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public synchronized void sendResponse(char flag, String message) {
        ServerFacade.getInstance()
                .send(GameState.getInstance().generateCurrentAction(flag, message));
    }

    public synchronized void sendResponse(char flag, int index, String message) {
        ServerFacade.getInstance()
                .send(index, GameState.getInstance().generateCurrentAction(flag, message));
    }

    public synchronized void sendResponse(char flag, String name ,  int money) {
        ServerFacade.getInstance()
                .send(GameState.getInstance().generateCurrentAction( flag , money , name));
    }

    public synchronized void sendResponse(char flag, String name, String args) {
        ServerFacade.getInstance()
                .send(GameState.getInstance().generateCurrentAction(flag, name, args));
    }

    public synchronized void sendResponse(char flag, int index, int count, String name) {
        ServerFacade.getInstance()
                .send(index, GameState.getInstance().generateCurrentAction(flag, count, name));
    }

//    public synchronized void sendResponse(char flag, String name, String buildName) {
//        ServerFacade.getInstance()
//                .send(GameState.getInstance().generateCurrentAction(flag, name, buildName));
//    }
//
//    public synchronized void sendResponse(char flag, String name, int changedMoney) {
//        ServerFacade.getInstance()
//                .send(GameState.getInstance().generateCurrentAction(flag, name, changedMoney));
//    }


}
