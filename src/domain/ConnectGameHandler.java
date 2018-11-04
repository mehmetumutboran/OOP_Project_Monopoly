package domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.player.Player;
import network.ClientFacade;
import network.ServerFacade;
import network.listeners.ReceivedChangedListener;

import java.io.IOException;

/**
 * Singleton Class to handle communication between UI and Network during initialization
 */
public class ConnectGameHandler implements ReceivedChangedListener {
    private ClientFacade clientFacade;
    private ServerFacade serverFacade;

    private static ConnectGameHandler connectGameHandler;

    private ConnectGameHandler() {
        serverFacade = new ServerFacade();
        clientFacade = new ClientFacade();
        clientFacade.addReceivedChangedListener(this);
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
        if (serverFacade.createServer(port)) {
//            MonopolyGameController.getInstance().addPlayer(username);
//            System.out.println("In the Connection game handler Class\n");
            connectClient(username, "localhost", port); // Connects the host as a client after it creates server
        }
    }

    /**
     * @param username Username for the player
     * @param ip       IP for server connection
     * @param port     for server Connection
     */
    public void connectClient(String username, String ip, int port) {
        Player player = new Player(username);

        if (clientFacade.createClient(ip, port)) {
            MonopolyGameController.getInstance().addPlayer(player);
            clientFacade.send(player.toJSON());
//            System.out.println(MonopolyGameController.getInstance().getPlayerList());
        }
    }

    /**
     * Normally it would transfer received message to the {@link MessageInterpreter}
     * Right now it assumes Received message is new {@link Player} and adds it to the {@link MonopolyGameController#getPlayerList()}
     */
    @Override
    public void onReceivedChangedEvent() {
        //TODO change to MessageInterpreter
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        String message = clientFacade.getMessage();

        if(message.charAt(0) == 'A') return;

        try {
            Player player = mapper.readValue(message, Player.class);
            if(!MonopolyGameController.getInstance().getPlayerList().contains(player)) {
                clientFacade.send(MonopolyGameController.getInstance().getPlayerList().get(0).toJSON());
                MonopolyGameController.getInstance().addPlayer(player);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
