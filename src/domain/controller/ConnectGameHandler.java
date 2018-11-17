package domain.controller;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.GameLogic;
import domain.MessageInterpreter;
import domain.RandomPlayer;
import domain.player.Player;
import gui.baseFrame.buttons.hostJoinButtons.MultiplayerConnectionButton;
import network.client.clientFacade.ClientFacade;
import network.listeners.ReceivedChangedListener;
import network.server.serverFacade.ServerFacade;

import java.io.IOException;

/**
 * Singleton Class to handle communication between UI and Network during initialization
 */
public class ConnectGameHandler implements ReceivedChangedListener {

    private static ConnectGameHandler connectGameHandler;

    private ConnectGameHandler() {
        ClientFacade.getInstance().addReceivedChangedListener(this);
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
    public String connectHost(String username, int port, MultiplayerConnectionButton mcb) {
        if (ServerFacade.getInstance().createServer(port)) {
            connectClient(username, "localhost", port, true, mcb); // Connects the host as a client after it creates server
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
    public String connectClient(String username, String ip, int port, boolean isHost, MultiplayerConnectionButton mcb) {
        Player player = new Player(username);
        if (isHost) player.setReadiness("Host");
        ClientFacade.getInstance().addConnectionFailedListener(mcb);

        if (ClientFacade.getInstance().createClient(username, ip, port)) {
            System.out.println("\n\n Added via Connect Client");
            MonopolyGameController.getInstance().addPlayer(player);
            System.out.println("\n\n");
            sendClientInfo(username);
            sendChange(player);
            return "Successful";
        } else {
            return "Failed";
        }

    }

    private void sendClientInfo(String username) {
        ClientFacade.getInstance()
                .send(username, username);
    }

    public void sendChange(Player p) {
        ClientFacade.getInstance().send(p.getName(), p.toJSON());
    }

    /**
     * Normally it would transfer received message to the {@link MessageInterpreter}
     * Right now it assumes Received message is new {@link Player} and adds it to the {@link MonopolyGameController#getPlayerList()}
     */
    @Override
    public synchronized void onReceivedChangedEvent() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        String message = ClientFacade.getInstance().getMessage();

        if (message == null) {
            System.out.println("\nMessage Null");
            return;
        }

        if (message.charAt(0) != '{') {
            if (message.charAt(0) == 'E') {
                MonopolyGameController.getInstance().informClosed();
            } else if (message.charAt(0) == 'X') {
                MonopolyGameController.getInstance().removePlayer(message.substring(1));
            }
            return;
        }

        try {
            Player player;
            if (mapper.readValue(message, Player.class).getReadiness().equals("Bot")) {
                player = mapper.readValue(message, RandomPlayer.class); // If player is Bot initialize it as RandomPlayer
                // To prevent ClassCastException
            } else {
                player = mapper.readValue(message, Player.class);
            }

            if (!MonopolyGameController.getInstance().getPlayerList().contains(player)) { //New PLayer
                ClientFacade.getInstance().send(MonopolyGameController.getInstance().getPlayerList().get(0).getName(),
                        MonopolyGameController.getInstance().getPlayerList().get(0).toJSON());
                if(MonopolyGameController.getInstance().getPlayerList().get(0).getReadiness().equals("Host")) {
                    MonopolyGameController.getInstance().getPlayerList().stream().filter(p -> p.getReadiness().equals("Bot"))
                            .forEach(p -> ClientFacade.getInstance().send(p.getName(), p.toJSON()));
                }
                MonopolyGameController.getInstance().addPlayer(player);
            } else if (!MonopolyGameController.getInstance().getPlayerList().get(MonopolyGameController.getInstance(). //Color changed
                    getPlayerList().indexOf(player)).getToken().getColor().equals(player.getToken().getColor())) {
                MonopolyGameController.getInstance().changePlayerColor(MonopolyGameController.getInstance().getPlayerList().indexOf(player), player.getToken().getColor());
            } else if (!MonopolyGameController.getInstance().getPlayerList().get(MonopolyGameController.getInstance().  // Readiness changed
                    getPlayerList().indexOf(player)).getReadiness().equals(player.getReadiness())) {
                MonopolyGameController.getInstance().changePlayerReadiness(MonopolyGameController.getInstance().getPlayerList().indexOf(player));
            } else if (player.isStarted()) {  // Game started
                MonopolyGameController.getInstance().getPlayerList().get(MonopolyGameController.getInstance().
                        getPlayerList().indexOf(player)).setStarted(true);
                if (!MonopolyGameController.getInstance().getPlayerList().get(0).isStarted()) {
                    MonopolyGameController.getInstance().checkReadiness();
                }
                ClientFacade.getInstance().removeReceivedChangedListener(this);
                ClientFacade.getInstance().removeAllConnectionFailedListeners();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendChange(Player player, char e) {
        ClientFacade.getInstance().send(GameLogic.getInstance().getPlayers().peekFirst().getName(), e + player.toJSON());
    }

    public void connectBot(String s, String color) {
        RandomPlayer randomPlayer = new RandomPlayer(s);
        randomPlayer.setReadiness("Bot");
        randomPlayer.getToken().setColor(color);

        if (ClientFacade.getInstance().createBotClient(s, "localhost", ServerFacade.getInstance().getServer().getSs().getLocalPort())) {
            MonopolyGameController.getInstance().addPlayer(randomPlayer);
            sendClientInfo(s);
            sendChange(randomPlayer);
        }
    }
}
