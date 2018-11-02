package domain;

import network.ClientFacade;
import network.ServerFacade;

/**
 * Singleton Class to handle communication between UI and Network during initialization
 */
public class ConnectGameHandler {
    private ClientFacade clientFacade;
    private ServerFacade serverFacade;

    private static ConnectGameHandler connectGameHandler;

    private ConnectGameHandler() {
        serverFacade = new ServerFacade();
        clientFacade = new ClientFacade();
    }

    public static ConnectGameHandler getInstance() {
        if (connectGameHandler == null) {
            connectGameHandler = new ConnectGameHandler();
        }
        return connectGameHandler;
    }

    /**
     *
     * @param username Username from the {@link gui.baseFrame.buttons.hostJoinButtons.HostButton} usernameField
     * @param port Port number for the server connection from {@link gui.baseFrame.buttons.hostJoinButtons.HostButton}
     */
    public void connectHost(String username, int port) {
        if (serverFacade.createServer(port)) {
//            MonopolyGameController.getInstance().addPlayer(username);
//            System.out.println("In the Connection game handler Class\n");
            connectClient(username, "localhost", port); // Connects the host as a client after it creates server
        }
    }

    /**
     *
     * @param username Username for the player
     * @param ip IP for server connection
     * @param port for server Connection
     */
    public void connectClient(String username, String ip, int port) {
        if (clientFacade.createClient(ip, port)) {
            MonopolyGameController.getInstance().addPlayer(username);
        }
    }

}
