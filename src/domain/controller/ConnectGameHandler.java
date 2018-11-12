package domain.controller;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.MessageInterpreter;
import domain.RandomPlayer;
import domain.player.Player;
import network.ClientFacade;
import network.ServerFacade;
import network.listeners.ReceivedChangedListener;

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
    public void connectHost(String username, int port) {
        if (ServerFacade.getInstance().createServer(port)) {
//            MonopolyGameController.getInstance().addPlayer(username);
//            System.out.println("In the Connection game handler Class\n");
            connectClient(username, "localhost", port,true); // Connects the host as a client after it creates server
        }
    }

    /**
     * @param username Username for the player
     * @param ip       IP for server connection
     * @param port     for server Connection
     */
    public void connectClient(String username, String ip, int port,boolean isHost) {
        Player player = new Player(username);
        if(isHost) player.setReadiness("Host");

        if (ClientFacade.getInstance().createClient(ip, port)) {
            MonopolyGameController.getInstance().addPlayer(player);
            sendChange(player);
        }
    }

    public void sendChange(Player p) {
        ClientFacade.getInstance().send(p.toJSON());
    }

    /**
     * Normally it would transfer received message to the {@link MessageInterpreter}
     * Right now it assumes Received message is new {@link Player} and adds it to the {@link MonopolyGameController#getPlayerList()}
     */
    @Override
    public synchronized void onReceivedChangedEvent(ClientFacade clientFacade) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        String message = clientFacade.getMessage();
        if (message == null || message.charAt(0) != '{') {
             if (message.charAt(0) == 'E') {
                System.out.println("OnReceived: " + message);
                MonopolyGameController.getInstance().informClosed();
            }else return;
        }

        try {
            Player player = mapper.readValue(message, Player.class);
            if (!MonopolyGameController.getInstance().getPlayerList().contains(player)) { //New PLayer
                clientFacade.send(MonopolyGameController.getInstance().getPlayerList().get(0).toJSON());
                MonopolyGameController.getInstance().addPlayer(player);
            } else if (!MonopolyGameController.getInstance().getPlayerList().get(MonopolyGameController.getInstance(). //Color changed
                    getPlayerList().indexOf(player)).getToken().getColor().equals(player.getToken().getColor())) {
                MonopolyGameController.getInstance().changePlayerColor(MonopolyGameController.getInstance().getPlayerList().indexOf(player), player.getToken().getColor());
            } else if (!MonopolyGameController.getInstance().getPlayerList().get(MonopolyGameController.getInstance().  // Readiness changed
                    getPlayerList().indexOf(player)).getReadiness().equals(player.getReadiness())) {
                MonopolyGameController.getInstance().changePlayerReadiness(MonopolyGameController.getInstance().getPlayerList().indexOf(player));
            } else if (player.isStarted()){  // Game started
                MonopolyGameController.getInstance().getPlayerList().get(MonopolyGameController.getInstance().
                        getPlayerList().indexOf(player)).setStarted(true);
                if(!MonopolyGameController.getInstance().getPlayerList().get(0).isStarted()) {
                    MonopolyGameController.getInstance().checkReadiness();
                    clientFacade.removeReceivedChangedListener(this);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendChange(Player player, char e) {
        ClientFacade.getInstance().send(e + player.toJSON());
    }

    public void connectBot(String s) {
        RandomPlayer randomPlayer = new RandomPlayer(s);
        randomPlayer.setReadiness("Bot");
        randomPlayer.getToken().setColor("Blue"); //TODO Can be changed

        if (ClientFacade.getInstance().createClient("localhost", ServerFacade.getInstance().getServer().getSs().getLocalPort())) {
            MonopolyGameController.getInstance().addPlayer(randomPlayer);
            sendChange(randomPlayer);
        }
    }
}
