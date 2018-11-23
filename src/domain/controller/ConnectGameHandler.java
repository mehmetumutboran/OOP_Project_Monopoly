package domain.controller;

import domain.MessageInterpreter;
import domain.RandomPlayer;
import domain.listeners.PlayerKickedListener;
import domain.player.Player;
import network.client.clientFacade.ClientFacade;
import network.listeners.ReceivedChangedListener;
import network.server.serverFacade.ServerFacade;

import java.util.ArrayList;

/**
 * Singleton Class to handle communication between UI and Network during initialization
 */
public class ConnectGameHandler implements ReceivedChangedListener {

    private static ConnectGameHandler connectGameHandler;

    private ArrayList<PlayerKickedListener> playerKickedListeners;

    private ConnectGameHandler() {
        ClientFacade.getInstance().addReceivedChangedListener(this);
        playerKickedListeners = new ArrayList<>();
    }

    public static ConnectGameHandler getInstance() {
        if (connectGameHandler == null) {
            connectGameHandler = new ConnectGameHandler();
        }
        return connectGameHandler;
    }

    /**
     * @param username Username from the {@link gui.baseFrame.buttons.hostJoinButtons.HostButton} usernameField
     * @param port     Port number for the server connection from {@link gui.baseFrame.buttons.hostJoinButtons.HostButton}
     */
    public String connectHost(String username, int port, boolean isMulti) {
        if (ServerFacade.getInstance().createServer(port, isMulti)) {
            connectClient(username, "localhost", port, true); // Connects the host as a client after it creates server
        } else {
            return "Server cannot be created!!";
        }
        return "Successful";
    }

    /**
     * @param username Username for the player
     * @param ip       IP for server connection
     * @param port     for server Connection
     */
    public String connectClient(String username, String ip, int port, boolean isHost) {
        Player player = new Player(username);
        if (isHost) player.setReadiness("Host");

        if (ClientFacade.getInstance().createClient(username, ip, port)) {
            System.out.println("\n\n Added via Connect Client");
            MonopolyGameController.getInstance().addPlayer(player);
            System.out.println("\n\n");
            sendClientInfo(username);
            sendPlayerAddChange(player);
            return "Successful";
        } else {
            return "Failed";
        }
    }

    private void sendClientInfo(String username) {
        ClientFacade.getInstance()
                .send(username, username);
    }

    public void sendPlayerAddChange(Player p) {
        ClientFacade.getInstance().send(p.getName(), "P+"+p.getName()+"+"+p.getReadiness()+"+"+p.getToken().getColor());
    }

    public void sendGameStartedChange(Player p){
        ClientFacade.getInstance().send(p.getName(), "S+"+p.getName());
    }

    public void sendColorChange(Player p){
        ClientFacade.getInstance().send(p.getName(), "C+"+p.getName()+"+"+p.getToken().getColor());
    }

    public void sendReadinessChange(Player p){
        ClientFacade.getInstance().send(p.getName(), "R+"+p.getName()+"+"+p.getReadiness());
    }

    /**
     * Normally it would transfer received message to the {@link MessageInterpreter}
     * Right now it assumes Received message is new {@link Player} and adds it to the {@link MonopolyGameController#getPlayerList()}
     */
    @Override
    public synchronized void onReceivedChangedEvent() {
        String message = ClientFacade.getInstance().getMessage();

        if(message==null || message.isEmpty()) return;

        if (message.equals("You are kicked!")) {
            ClientFacade.getInstance().terminate();
            publishPlayerKickedEvent();
            return;
        }

        if(!message.contains("+")) return;

        System.out.println("Message received in cgh: "+message);
        String flag = ConnectGameInterpreter.getInstance().messageHandler(message);

        if(flag == null) return;

        if ("E".equals(flag)) {
            ClientFacade.getInstance().terminate();
            publishPlayerKickedEvent();
        } else if ("XH".equals(flag)) {
            ClientFacade.getInstance().terminate(); //TODO player is kicked if host exits
            publishPlayerKickedEvent();
        } else if ("XC".equals(flag)) {
            MonopolyGameController.getInstance().removePlayer(message.substring(1));
        } else if ("S".equals(flag)) {
            ClientFacade.getInstance().removeReceivedChangedListener(this);
            ClientFacade.getInstance().removeAllConnectionFailedListeners();
        }
    }

    public void addPlayerKickedListener(PlayerKickedListener pkl) {
        playerKickedListeners.add(pkl);
    }

    private void publishPlayerKickedEvent() {
        for (PlayerKickedListener playerKickedListener : playerKickedListeners) {
            playerKickedListener.onPlayerKickedEvent();
        }
    }

    public void sendChange(Player player, char e) {
        ClientFacade.getInstance().send(player.getName(), e + player.getName());
    }

    public void connectBot(String s, String color) {
        RandomPlayer randomPlayer = new RandomPlayer(s);
        randomPlayer.setReadiness("Bot");
        randomPlayer.getToken().setColor(color);

        if (ClientFacade.getInstance().createBotClient(s, "localhost", ServerFacade.getInstance().getServer().getSs().getLocalPort())) {
            MonopolyGameController.getInstance().addPlayer(randomPlayer);
            sendClientInfo(s);
            sendPlayerAddChange(randomPlayer);
        }
    }

    public void kickPlayer(String username) {
        ServerFacade.getInstance().kick(username);
    }
}
