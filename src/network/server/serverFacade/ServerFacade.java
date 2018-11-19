package network.server.serverFacade;

import network.server.Server;

/**
 * Class that provides network logic to server Host
 */
public class ServerFacade {
    private Server server;

    private static ServerFacade instance;

    private ServerFacade() {

    }

    public static ServerFacade getInstance() {
        if (instance == null) instance = new ServerFacade();
        return instance;
    }

    /**
     * Creates server object and stores it
     *
     * @param port server socket port
     * @return Whether server successfully created
     */
    public boolean createServer(int port) {
        server = new Server(port);
        //noinspection ConstantConditions
        return server != null;
    }

    public Server getServer() {
        return server;
    }

    public void kick(String username) {
        server.getClientHandler(username).terminate();
    }
}
