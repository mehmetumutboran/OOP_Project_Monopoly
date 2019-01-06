package domain.server.controller;

import domain.client.ClientCommunicationHandler;
import domain.server.listeners.PlayerKickedListener;
import domain.util.Flags;
import network.client.clientFacade.ClientFacade;
import network.server.serverFacade.ServerFacade;

import java.util.ArrayList;

/**
 * Singleton Class to handle communication between UI and Network during initialization
 */
public class ConnectGameHandler {
//implements ReceivedChangedListener

    private static ConnectGameHandler connectGameHandler;

    private ArrayList<PlayerKickedListener> playerKickedListeners;

    private ConnectGameHandler() {
//        ClientFacade.getInstance().addReceivedChangedListener(this);
        playerKickedListeners = new ArrayList<>();
    }

    public static ConnectGameHandler getInstance() {
        if (connectGameHandler == null) {
            connectGameHandler = new ConnectGameHandler();
        }
        return connectGameHandler;
    }

    /**
     * //username Username from the {@link gui.baseFrame.buttons.hostJoinButtons.HostButton} usernameField
     *
     * @param port Port number for the server connection from {@link gui.baseFrame.buttons.hostJoinButtons.HostButton}
     */
    public boolean connectHost(int port, boolean isMulti) {
        return ServerFacade.getInstance().createServer(port, isMulti);
    }

    /**
     * @param username Username for the player
     * @param ip       IP for server connection
     * @param port     for server Connection
     */
    public boolean connectClient(String username, String ip, int port) {
        if (!ip.equals("localhost")) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return ClientFacade.getInstance().createClient(username, ip, port);
    }

//    private void sendClientInfo(String username) {
//        ClientFacade.getInstance()
//                .send(username);
//    }

//    public void sendPlayerAddChange(Player p) {
//        ClientFacade.getInstance().send("P+" + p.getName() + "+" + p.getReadiness() + "+" + p.getToken().getColor());
//    }
//
//    public void sendGameStartedChange(Player p) {
//        ClientFacade.getInstance().send("S+" + p.getName());
//    }
//
//    public void sendColorChange(Player p) {
//        ClientFacade.getInstance().send("C+" + p.getName() + "+" + p.getToken().getColor());
//    }
//
//    public void sendReadinessChange(Player p) {
//        ClientFacade.getInstance().send("R+" + p.getName() + "+" + p.getReadiness());
//    }


//    @Override
//    public synchronized void onReceivedChangedEvent() {
//        String message = ClientFacade.getInstance().getMessage();
//
//        if (message == null || message.isEmpty()) return;
//
//        if (message.equals("You are kicked!")) {
//            ClientFacade.getInstance().terminate();
//            publishPlayerKickedEvent();
//            return;
//        }
//
//
//        System.out.println("Message received in cgh: " + message);
//        String flag = ConnectGameInterpreter.getInstance().messageHandler(message);
//
//        if (flag == null) return;
//
//        if ("E".equals(flag)) {
//            ClientFacade.getInstance().terminate();
//            publishPlayerKickedEvent();
//        } else if ("XH".equals(flag)) {
//            ClientFacade.getInstance().terminate(); //TODO player is kicked if host exits
//            publishPlayerKickedEvent();
//        } else if ("XC".equals(flag)) {
//            MonopolyGameController.getInstance().removePlayer(message.substring(1));
//        } else if ("S".equals(flag)) {
//            ClientFacade.getInstance().removeReceivedChangedListener(this);
//            ClientFacade.getInstance().removeAllConnectionFailedListeners();
//        }
//    }

//    public void addPlayerKickedListener(PlayerKickedListener pkl) {
//        playerKickedListeners.add(pkl);
//    }
//
//    private void publishPlayerKickedEvent() {
//        for (PlayerKickedListener playerKickedListener : playerKickedListeners) {
//            playerKickedListener.onPlayerKickedEvent();
//        }
//    }

//    public void sendChange(Player player, char e) {
//        ClientFacade.getInstance().send(e + player.getName());
//    }

    public void connectBot(String name, String color, String difficulty) {
        ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("AddBot"), name + "," + color + "," + difficulty);
    }

    public void kickPlayer(String username) {
        ClientCommunicationHandler.getInstance().sendRequest(Flags.getFlag("Remove"), username);
    }
}
