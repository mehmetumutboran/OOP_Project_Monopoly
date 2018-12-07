package domain.client.interpreter;

import domain.client.UIUpdater;
import domain.server.board.Board;
import domain.server.board.DeedSquare;
import domain.server.board.Square;
import domain.util.Flags;
import domain.util.GameInfo;

public class DontBuyResponseInterpreter implements ResponseInterpretable {

    @Override
    public void interpret(String[] message) {
        UIUpdater.getInstance().showPrompt(Flags.getFlag
                ("DontBuy"));    }
}
