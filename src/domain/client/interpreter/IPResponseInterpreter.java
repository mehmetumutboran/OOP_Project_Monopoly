package domain.client.interpreter;

import domain.util.MessageConverter;
import network.client.clientFacade.ClientFacade;

import java.util.Arrays;

public class IPResponseInterpreter implements ResponseInterpretable {
    @Override
    public void interpret(String[] message) {
        String name = message[1];
        String[][] ips = MessageConverter.convertStringTo2DStringArray(message[2]);

        System.out.println("\n 000000000-----------\n" + Arrays.deepToString(ips));

        ClientFacade.getInstance().setIps(ips);
    }
}
