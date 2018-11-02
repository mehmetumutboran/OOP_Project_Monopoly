package network;

/**
 * Class that provides network logic to Server Host
 */
public class ServerFacade {
    private Server server;

    /**
     * Creates Server object and stores it
     *
     * @param port Server socket port
     * @return Whether server successfully created
     */
    public boolean createServer(int port) {
        server = new Server(port);
        //noinspection ConstantConditions
        return server != null;
    }
}
