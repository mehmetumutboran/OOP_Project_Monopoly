package domain.client.interpreter;

import domain.client.UIUpdater;
import domain.util.MessageConverter;

public class RollResponseInterpreter implements ResponseInterpretable {
    @Override
    public void interpret(String[] message) {
        String name = message[1];
        int[] faceValues = MessageConverter.convertStringToIntArray(message[2]);

        switch (faceValues[2]) {
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
