package domain.client.interpreter;

import domain.client.ClientCommunicationHandler;
import domain.util.GameInfo;

public class DoubleCounterResponseInterpreter implements ResponseInterpretable{
    @Override
    public void interpret(String[] message) {
        if(message[2].equals("1")) GameInfo.getInstance().getPlayer(message[1]).incrementDoubleCounter();
        else if (message[2].equals("0")) GameInfo.getInstance().getPlayer(message[1]).resetDoubleCounter();

        ClientCommunicationHandler.getInstance().sendReceived();
    }
}
