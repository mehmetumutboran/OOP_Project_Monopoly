package domain.client.interpreter;

import domain.client.ClientCommunicationHandler;
import domain.client.UIUpdater;
import domain.util.LoadGameHandler;

public class LoadResponseInterpreter implements ResponseInterpretable {
    @Override
    public void interpret(String[] message) {
        String load = message[1];
        LoadGameHandler.getInstance().loadGame(load);
        UIUpdater.getInstance().changePanel("Game");
        UIUpdater.getInstance().startGame();
        ClientCommunicationHandler.getInstance().sendReceived();

    }
}
