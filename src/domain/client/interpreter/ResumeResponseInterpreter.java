package domain.client.interpreter;

import domain.client.ClientCommunicationHandler;
import domain.client.UIUpdater;

public class ResumeResponseInterpreter implements ResponseInterpretable {
    @Override
    public void interpret(String[] message) {
        UIUpdater.getInstance().resumeUpdate();
        ClientCommunicationHandler.getInstance().sendReceived();
    }
}
