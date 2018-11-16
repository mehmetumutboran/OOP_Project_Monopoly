package network.server.serverFacade;

import network.server.Server;

/**
 * Class that provides network logic to server Host
 */
public class ServerFacade {
    private Server server;

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
}