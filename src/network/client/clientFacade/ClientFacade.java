package network.client.clientFacade;


import domain.controller.MonopolyGameController;
import network.client.Client;
import network.listeners.ConnectionFailedListener;
import network.listeners.ReceivedChangedListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Class that provides network logic to hostClient Player
 * Uses observer pattern to publish Received message (~Game state)
 */
public class ClientFacade {
    private static ClientFacade clientFacade;

    private Client hostClient;
    private ArrayList<Client> bots;
    private String message;

    /**
     * Subscribers
     */
    private volatile ArrayList<ReceivedChangedListener> receivedChangedListeners;
    private volatile ArrayList<ConnectionFailedListener> connectionFailedListeners;

    private ClientFacade() {
        receivedChangedListeners = new ArrayList<>();
        connectionFailedListeners = new ArrayList<>();
        bots = new ArrayList<>();
    }

    public static ClientFacade getInstance() {
        if (clientFacade == null)
            clientFacade = new ClientFacade();

        return clientFacade;
    }


    /**
     * Creates new hostClient object and stores it
     *
     * @param ip   server ip
     * @param port server socket port
     * @return Whether hostClient successfully created
     */
    public boolean createClient(String username, String ip, int port) {
        try {
            hostClient = new Client(username, ip, port);
        } catch (IOException e) {
            createClientError();
            return false;
        }
        return true;
    }

    public boolean createBotClient(String username, String ip, int localPort) {
        try {
            bots.add(new Client(username, ip, localPort));
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
     * @param name Name of the player who performed action
     * @param message Formatted as JSON String
     */
    public synchronized void send(String name, String message) {
        if (name.contains("Bot")) {
            bots.stream().filter(x -> x.getUsername().equals(name))
                    .collect(Collectors.toList()).get(0).send(message);
        } else
            hostClient.send(message);
    }

    /**
     * Called by hostClient when a message received
     * Also calls publish method to notify listeners
     *
     * @param m Received Message Formatted as JSON
     */
    public synchronized void sendReceivedMessage(String m) {
        this.message = m;
//        System.out.println(m);
        publishReceivedChangedAction();
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
            MonopolyGameController.getInstance().reset();
            hostClient.getDis().close();
            hostClient.getDos().close();
            hostClient.getSocket().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getMessage() {
        return message;
    }


}
