package network;


import network.listeners.ReceivedChangedListener;

import java.util.ArrayList;

/**
 * Class that provides network logic to Client Player
 * Uses observer pattern to publish Received message (~Game state)
 */
public class ClientFacade {
    private Client client;
    private String message;

    /**
     * Subscribers
     */
    private static ArrayList<ReceivedChangedListener> receivedChangedListeners = new ArrayList<>();


    /**
     * Creates new Client object and stores it
     *
     * @param ip   Server ip
     * @param port Server socket port
     * @return Whether client successfully created
     */
    public boolean createClient(String ip, int port) {
        client = new Client(ip, port, this);
        //noinspection ConstantConditions
        return client != null;
    }

    /**
     * Sends Message to the server
     * @param message Formatted as JSON String
     */
    public void send(String message) {
        client.send(message);
    }

    /**
     * Called by Client when a message received
     * Also calls publish method to notify listeners
     * @param m Received Message Formatted as JSON
     */
    public void sendReceivedMessage(String m) {
        this.message = m;
        System.out.println("ClientFacade message received:\n" + m);
        publishReceivedChangedAction();
    }

    private void publishReceivedChangedAction() {
        for (ReceivedChangedListener aReceivedChangedListener : receivedChangedListeners) {
            if (aReceivedChangedListener == null) continue;
            aReceivedChangedListener.onReceivedChangedEvent();
        }
    }

    public void addReceivedChangedListener(ReceivedChangedListener listener){
        receivedChangedListeners.add(listener);
    }

    public String getMessage() {
        return message;
    }
}
