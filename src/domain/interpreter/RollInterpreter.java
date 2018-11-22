package domain.interpreter;

import domain.GameLogic;
import domain.MessageConverter;
import domain.UIUpdater;

public class RollInterpreter implements Interpreter {
    @Override
    public void interpret(String[] message) {
        String name = message[0];
        int[] faceValues = MessageConverter.convertStringToIntArray(message[1]);

        GameLogic.getInstance().getPlayer(name).setFaceValues(faceValues);

        UIUpdater.getInstance().setMessage(name + " rolled " + faceValues[0] + " " + faceValues[1] + " " + faceValues[2]); //TODO Mrmonopoly
    }
}
