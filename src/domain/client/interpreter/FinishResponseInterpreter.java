package domain.client.interpreter;

import domain.client.ClientCommunicationHandler;
import domain.client.RandomPlayerHandler;
import domain.client.UIUpdater;
import domain.util.GameInfo;

public class FinishResponseInterpreter implements ResponseInterpretable {
    @Override
    public void interpret(String[] message) {
        GameInfo.getInstance().nextTurn();
        UIUpdater.getInstance().turnUpdate();

        if(GameInfo.getInstance().isMyselfHost() && GameInfo.getInstance().isPeekBot()){
            RandomPlayerHandler.getInstance().playBotTurn();
        }

        ClientCommunicationHandler.getInstance().sendReceived();

    }
}
