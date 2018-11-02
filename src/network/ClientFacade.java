package network;

/**
 * Class that provides network logic to Client Player
 */
public class ClientFacade {
    private Client client;

    /**
     * Creates new Client object and stores it
     * @param ip Server ip
     * @param port Server socket port
     * @return Whether client successfully created
     */
    public boolean createClient(String ip, int port) {
        client = new Client(ip, port);
        //noinspection ConstantConditions
        return client != null;
    }
}
