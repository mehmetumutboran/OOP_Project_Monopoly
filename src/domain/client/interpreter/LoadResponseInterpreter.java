package domain.client.interpreter;

import domain.client.ClientCommunicationHandler;
import domain.client.UIUpdater;
import domain.util.GameInfo;
import domain.util.LoadGameHandler;

import java.util.ArrayList;

public class LoadResponseInterpreter implements ResponseInterpretable {
    @Override
    public void interpret(String[] message) {
        String load = message[1];
        LoadGameHandler.getInstance().loadGame(load);
        GameInfo.getInstance().startGame();
        UIUpdater.getInstance().changePanel("Game");
        UIUpdater.getInstance().startGame();
        for (String name:GameInfo.getInstance().getPlayerQueue()) {
            UIUpdater.getInstance().setTokenLocation(name,GameInfo.getInstance().getPlayer(name).getToken().getLocation()[0],
                    GameInfo.getInstance().getPlayer(name).getToken().getLocation()[1]);
        }
        ClientCommunicationHandler.getInstance().sendReceived();

    }
}
