package domain.interpreter;

import domain.GameLogic;
import domain.MessageConverter;
import domain.UIUpdater;

public class RollInterpreter implements Interpreter {
    @Override
    public void interpret(String[] message) {
        String name = message[1];
        int[] faceValues = MessageConverter.convertStringToIntArray(message[2]);

        GameLogic.getInstance().getPlayer(name).setFaceValues(faceValues);

        switch (faceValues[2]){
            case 7:
                UIUpdater.getInstance().setMessage(name + " rolled " + faceValues[0] + " " + faceValues[1] + " Mr.Monopoly");
                break;
            case 8:
                UIUpdater.getInstance().setMessage(name + " rolled " + faceValues[0] + " " + faceValues[1] + " Bus");
                break;
            default:
                UIUpdater.getInstance().setMessage(name + " rolled " + faceValues[0] + " " + faceValues[1] + " " + faceValues[2]);
                break;
        }

    }
}
