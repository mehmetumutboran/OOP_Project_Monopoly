package network;

public class ServerFacade {
    private Server server;

    public Server createServer(int port) {
        System.out.println("In the Server Facade");
        return server = new Server(port);
    }
}
