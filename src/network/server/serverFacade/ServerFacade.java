package network.server.serverFacade;

import domain.server.RequestInterpreter;
import network.server.Server;

import java.io.IOException;

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
    public boolean createServer(int port, boolean isMulti) {
        server = new Server(port, isMulti);
        //noinspection ConstantConditions
        return server != null;
    }

    public Server getServer() {
        return server;
    }

    public void kick(String username) {
        server.getClientHandler(username).terminate();
    }

    public void shutDown(){
        try {
            server.getSs().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setRequest(String request) {
        System.out.println("\n\nServerFacade: setREquest\n\n");
        RequestInterpreter.getInstance().interpret(request);
    }


    public void send(String name, String response) {
        try {
            Server.sendAll(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
