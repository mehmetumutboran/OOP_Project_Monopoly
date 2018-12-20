package domain.client.interpreter;

import domain.util.MessageConverter;
import network.client.clientFacade.ClientFacade;

public class IPResponseInterpreter implements ResponseInterpretable {
    @Override
    public void interpret(String[] message) {
        String name = message[1];
        String[] ips = MessageConverter.convertStringToStringArray(message[2]);

        ClientFacade.getInstance().setIps(ips);
    }
}
