package domain;

import network.ClientFacade;
import network.Server;
import network.ServerFacade;

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

    public Server connectHost(String username, int port) {
        Server server;
        if((server = serverFacade.createServer(port)) != null){
            MonopolyGameController.getInstance().addPlayer(username);
            System.out.println("In the Connection game handler Class\n" + server.toString());
        }
        return server;
    }

    public void connectClient(String username, String ip, int port) {
        if(clientFacade.createClient(ip, port)){
            MonopolyGameController.getInstance().addPlayer(username);
        }
    }

}
