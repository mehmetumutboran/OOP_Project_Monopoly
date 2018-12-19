package network.server.serverFacade;

import domain.server.RequestInterpreter;
import domain.server.controller.ServerCommunicationHandler;
import domain.server.interpreter.RemoveRequestInterpreter;
import domain.util.Flags;
import network.server.ClientHandler;
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

    public synchronized void kick(String username) {
        server.getClientHandler(username).terminate();
        server.removeClient(server.getClientHandler(username));
    }

    public void shutDown() {
        try {
            server.getSs().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void interpretRequest(String line, int index) {
        RequestInterpreter.getInstance().interpret(line, index);
    }


    public void send(String response) {
        try {
            server.sendAll(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(int index, String response) {
        try {
            server.sendToOne(index, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int nameToIndex(String username){
        return server.getClientIndex(username);
    }

    public void setClientInfo(String username){
        Server.setClientInfo(username);
    }

    public void removeClient(ClientHandler clientHandler) {
        new RemoveRequestInterpreter().interpret(new String[]{"Exit", server.getClientNameFromIndex(clientHandler.getIndex())}, clientHandler.getIndex());
    }

    public int getTotalNumPlayers() {
        return server.getTotalNumPlayers();
    }
}
