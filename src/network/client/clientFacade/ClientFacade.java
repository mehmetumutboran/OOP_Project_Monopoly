package network.client.clientFacade;


import domain.controller.MonopolyGameController;
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
    private String message;

    /**
     * Subscribers
     */
    private ArrayList<ReceivedChangedListener> receivedChangedListeners;
    private ArrayList<ConnectionFailedListener> connectionFailedListeners;

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
    public boolean createClient(String ip, int port) {
        try {
            client = new Client(ip, port, this);
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
        publishReceivedChangedAction();
    }

    private synchronized void publishReceivedChangedAction() {
        for (ReceivedChangedListener aReceivedChangedListener : receivedChangedListeners) {
            if (aReceivedChangedListener == null) continue;
            aReceivedChangedListener.onReceivedChangedEvent();
        }
    }

    public synchronized void addReceivedChangedListener(ReceivedChangedListener listener) {
        if (!receivedChangedListeners.contains(listener)) receivedChangedListeners.add(listener);
    }

    public synchronized void removeReceivedChangedListener(ReceivedChangedListener listener) {
        if (receivedChangedListeners.contains(listener)) receivedChangedListeners.remove(listener);
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


    public String getMessage() {
        return message;
    }
}
