package domain.client.interpreter;

import domain.client.ClientCommunicationHandler;
import domain.client.UIUpdater;
import domain.util.Flags;

public class DontStartResponseInterpreter implements ResponseInterpretable {
    @Override
    public void interpret(String[] message) {
        int notReadyCount = Integer.parseInt(message[1]);
        UIUpdater.getInstance().showPrompt(Flags.getFlag("DontStart"), notReadyCount);
        ClientCommunicationHandler.getInstance().sendReceived();

    }
}
