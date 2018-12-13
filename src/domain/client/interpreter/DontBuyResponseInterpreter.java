package domain.client.interpreter;

import domain.client.UIUpdater;
import domain.server.board.DeedSquare;
import domain.util.Flags;

public class DontBuyResponseInterpreter implements ResponseInterpretable {

    @Override
    public void interpret(String[] message) {
        UIUpdater.getInstance().showPrompt(Flags.getFlag
                ("DontBuy"));    }
}
