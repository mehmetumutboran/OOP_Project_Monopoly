package domain.client.interpreter;

import domain.client.ClientCommunicationHandler;
import domain.client.UIUpdater;
import domain.util.GameInfo;
import domain.util.MessageConverter;

import java.util.Arrays;

public class MoveResponseInterpreter implements ResponseInterpretable {
    @Override
    public void interpret(String[] message) {
        String name = message[1];
        int[] location = MessageConverter.convertStringToIntArray(message[2].substring(0, message[2].indexOf("@")), ',');
        String locName = message[2].substring(message[2].indexOf("@") + 1);
        System.out.println(Arrays.toString(MessageConverter.convertStringToIntArray(message[2], ',')));
        System.out.println(Arrays.toString(location));

        GameInfo.getInstance().getPlayer(name).getToken().setLocation(location);

        UIUpdater.getInstance().setMessage(name + " moved to " + locName); //TODO Mrmonopoly

        ClientCommunicationHandler.getInstance().sendReceived();
    }
}
