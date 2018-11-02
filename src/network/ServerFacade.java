package network;

public class ServerFacade {
    private Server server;

    public boolean createServer(int port) {
        server = new Server(port);
        return server != null;
    }
}
