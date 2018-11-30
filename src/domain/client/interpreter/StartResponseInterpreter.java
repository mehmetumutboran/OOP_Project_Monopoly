package domain.client.interpreter;

import domain.client.UIUpdater;
import domain.util.GameInfo;

import java.util.ArrayList;

public class StartResponseInterpreter implements ResponseInterpretable {

    @Override
    public void interpret(String[] message) {
        UIUpdater.getInstance().changePanel("Game");
////      UIUpdater.getInstance().showList();
        UIUpdater.getInstance().startGame();
    }


}
