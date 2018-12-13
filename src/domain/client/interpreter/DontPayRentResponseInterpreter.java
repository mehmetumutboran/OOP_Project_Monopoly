package domain.client.interpreter;

import domain.client.UIUpdater;
import domain.server.board.DeedSquare;
import domain.util.Flags;

public class DontPayRentResponseInterpreter implements ResponseInterpretable {

    @Override
    public void interpret(String[] message) {
        System.out.println("!!!!!!!!!!! dont pay");
        UIUpdater.getInstance().showPrompt(Flags.getFlag
                ("DontPayRent"));    }
}
