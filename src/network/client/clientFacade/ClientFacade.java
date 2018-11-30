package network.client.clientFacade;


import domain.client.ResponseInterpreter;
import domain.util.GameInfo;
import network.client.Client;
import network.listeners.ConnectionFailedListener;
import network.listeners.ReceivedChangedListener;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Class that provides network logic to client Player
 * Uses observer pattern to publish Received message (~Game state)
 */
public class ClientFacade {
    private static ClientFacade clientFacade;

    private Client client;
    //    private ArrayList<Client> bots;
    private String message;

    /**
     * Subscribers
     */
    private volatile ArrayList<ReceivedChangedListener> receivedChangedListeners;
    private volatile ArrayList<ConnectionFailedListener> connectionFailedListeners;

    private ClientFacade() {
        receivedChangedListeners = new ArrayList<>();
        connectionFailedListeners = new ArrayList<>();
    }

    public static ClientFacade getInstance() {
        if (clientFacade == null)
            clientFacade = new ClientFacade();

        return clientFacade;
    }


    /**
     * Creates new client object and stores it
     *
     * @param ip   server ip
     * @param port server socket port
     * @return Whether client successfully created
     */
    public boolean createClient(String username, String ip, int port) {
        try {
            client = new Client(username, ip, port);
        } catch (IOException e) {
            createClientError();
            return false;
        }
        return true;
    }


    private void createClientError() {
        publishConnectionFailedAction();
    }

    /**
     * Sends Message to the server
     *
     * @param message Formatted as JSON String
     */
    public synchronized void send(String message) {
        client.send(message);
    }

    /**
     * Called by client when a message received
     * Also calls publish method to notify listeners
     *
     * @param m Received Message Formatted as JSON
     */
    public synchronized void sendReceivedMessage(String m) {
        this.message = m;
//        System.out.println(m);
        ResponseInterpreter.getInstance().interpret(m);
    }

    @SuppressWarnings("unchecked")
    private synchronized void publishReceivedChangedAction() {
        ArrayList<ReceivedChangedListener> temp = (ArrayList<ReceivedChangedListener>) receivedChangedListeners.clone();
        for (ReceivedChangedListener aReceivedChangedListener : temp) {
            if (aReceivedChangedListener == null) continue;
            aReceivedChangedListener.onReceivedChangedEvent();
        }
    }

    public synchronized void addReceivedChangedListener(ReceivedChangedListener listener) {
        if (!receivedChangedListeners.contains(listener)) receivedChangedListeners.add(listener);
    }

    @SuppressWarnings("unchecked")
    public synchronized void removeReceivedChangedListener(ReceivedChangedListener listener) {
        if (receivedChangedListeners.contains(listener)) {
            ArrayList<ReceivedChangedListener> temp = (ArrayList<ReceivedChangedListener>) receivedChangedListeners.clone();
            temp.remove(listener);
            receivedChangedListeners = temp;
        }
    }

    private synchronized void publishConnectionFailedAction() {
        for (ConnectionFailedListener aConnectionFailedListener : connectionFailedListeners) {
            if (aConnectionFailedListener == null) continue;
            aConnectionFailedListener.onConnectionFailedEvent();
        }
    }

    public synchronized void addConnectionFailedListener(ConnectionFailedListener listener) {
        if (!connectionFailedListeners.contains(listener)) connectionFailedListeners.add(listener);
    }

    public synchronized void removeAllConnectionFailedListeners() {
        connectionFailedListeners = new ArrayList<>();
    }

    public void terminate() {
        try {
            GameInfo.getInstance().reset();
            client.getDos().close();
            client.getDis().close();
            client.getSocket().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getMessage() {
        return message;
    }

    public String getUsername() {
        return client.getUsername();
    }


}
