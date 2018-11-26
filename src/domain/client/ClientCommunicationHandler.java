package domain.client;

import network.client.clientFacade.ClientFacade;

public class ClientCommunicationHandler {
    private static ClientCommunicationHandler ourInstance;

    public static ClientCommunicationHandler getInstance() {
        if(ourInstance == null)
            ourInstance = new ClientCommunicationHandler();
        return ourInstance;
    }

    private ClientCommunicationHandler() {
    }


    public void sendRequest(char flag, String username) {
        ClientFacade.getInstance().send(username, flag + "|" + username);
    }
}
