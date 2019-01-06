package domain.client.interpreter;

import domain.client.UIUpdater;
import domain.util.Flags;
import domain.util.GameInfo;

public class DontPayRentResponseInterpreter implements ResponseInterpretable {

    @Override
    public void interpret(String[] message) {
        System.out.println("!!!!!!!!!!! dont pay");
        String name = message[1];
        if (!GameInfo.getInstance().isBot(name))
            UIUpdater.getInstance().showPrompt(Flags.getFlag
                    ("DontPayRent"));
    }
}
