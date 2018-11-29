package domain.client.interpreter;

import domain.util.SaveGameHandler;

public class SaveResponseInterpreter implements ResponseInterpretable {
    @Override
    public void interpret(String[] message) {
        String saveInfo = message[1];
        SaveGameHandler.getInstance().saveGame(saveInfo);
    }

}
