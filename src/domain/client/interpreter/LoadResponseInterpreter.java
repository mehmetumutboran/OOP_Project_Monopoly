package domain.client.interpreter;

import domain.client.ClientCommunicationHandler;
import domain.client.UIUpdater;
import domain.util.Flags;
import domain.util.GameInfo;
import domain.util.LoadGameHandler;
import domain.util.MessageConverter;
import network.client.Client;
import network.client.clientFacade.ClientFacade;

import java.util.ArrayList;

public class LoadResponseInterpreter implements ResponseInterpretable {
    @Override
    public void interpret(String[] message) {
        String load = message[1];
        LoadGameHandler.getInstance().loadGame(load);
        GameInfo.getInstance().startGame();
        GameInfo.getInstance().loadQueueFix();
        UIUpdater.getInstance().changePanel("Game");
        UIUpdater.getInstance().startGame();
        UIUpdater.getInstance().startTokens(new ArrayList<>(GameInfo.getInstance().getPlayerQueue()));
        System.out.println("LOADED QUEUE!!!!!!" + MessageConverter.convertQueueToString(GameInfo.getInstance().getPlayerQueue()));
        for (String name:GameInfo.getInstance().getPlayerQueue()) {
            UIUpdater.getInstance().setTokenLocation(name,GameInfo.getInstance().getPlayer(name).getToken().getLocation()[0],
                    GameInfo.getInstance().getPlayer(name).getToken().getLocation()[1]);
        }
        ClientCommunicationHandler.getInstance().sendReceived();
        if((GameInfo.getInstance().isMyselfHost() && GameInfo.getInstance().isPeekBot()) || ClientFacade.getInstance().getUsername().equals(GameInfo.getInstance().getCurrentPlayerName())){
            ClientCommunicationHandler.getInstance().sendRequest(Flags.getFlag("Finish"), GameInfo.getInstance().getCurrentPlayer().getName());
        }


    }
}
