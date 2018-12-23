package network.client.clientFacade;


import domain.client.PlayerActionController;
import domain.client.ResponseInterpreter;
import domain.client.interpreter.KickResponseInterpreter;
import domain.server.controller.ConnectGameHandler;
import domain.util.Flags;
import domain.util.GameInfo;
import domain.util.MessageConverter;
import network.client.Client;
import network.listeners.ConnectionFailedListener;
import network.listeners.ReceivedChangedListener;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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
    private volatile String[][] ips;
    private int ipIndex = 1;

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
     * @param message Formatted message string
     */
    public synchronized void send(String message) {
        //@requires client not null
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


    public void setIps(String[][] ips) {
        this.ips = ips;
    }

    public void reconnect() {
        if(!GameInfo.getInstance().isStarted()){
            new KickResponseInterpreter().interpret(new String[]{String.valueOf(Flags.getFlag("Kick"))});
            return;
        }
        System.out.println("\nThe client's username is " + getUsername());
        System.out.println("Ip index is "+ ipIndex+1);
        System.out.println(Arrays.deepToString(ips));
        //System.out.println("Trying to reconnect to "+ips[ipIndex][0]+"\n");
        PlayerActionController.getInstance().reconnect(ips[++ipIndex][0].equals(getUsername()));
    }

    public void resetIndex(){
        this.ipIndex = 1;
    }

    public String getNextIP(){
        return ips[ipIndex][1];
    }

    public int getPort() {
        return client.getSocket().getPort();
    }

    public String getOldHostName() {
        return ips[ipIndex-1][0];
    }
}
