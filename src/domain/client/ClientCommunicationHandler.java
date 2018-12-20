package domain.client;

import domain.util.Flags;
import domain.util.MessageConverter;
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

    //    public void sendRequest(char flag, String username, String updown) {
//        ClientFacade.getInstance().send(flag + "|" + username + "|" + updown);
//    }
    public void sendRequest(char flag, String username, String args) {
        ClientFacade.getInstance().send(flag + "|" + username + "|" + args);
    }

    public void sendRequest(char flag, String username, int[] location) {
        ClientFacade.getInstance().send(flag + "|" + username + "|" + MessageConverter.convertArrayToString(location));
    }

    public void sendReceived() {
        ClientFacade.getInstance().send(Flags.getFlag("Received") + "|" + ClientFacade.getInstance().getUsername());

    }
}

