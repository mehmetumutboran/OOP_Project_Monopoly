package domain.client;

import domain.util.Flags;
import network.client.clientFacade.ClientFacade;

public class ClientCommunicationHandler {
    private static ClientCommunicationHandler ourInstance;

    public static ClientCommunicationHandler getInstance() {
        if (ourInstance == null)
            ourInstance = new ClientCommunicationHandler();
        return ourInstance;
    }

    private ClientCommunicationHandler() {
    }



    public void sendRequest(char flag, String username) {
        ClientFacade.getInstance().send(flag + "|" + username);
    }

    public void sendRequest(char flag, String username, String color) {
        ClientFacade.getInstance().send(flag + "|" + username + "|" + color);
    }

    public void sendReceived(){
        ClientFacade.getInstance().send(Flags.getFlag("Received") + "|" + ClientFacade.getInstance().getUsername());

    }
}
