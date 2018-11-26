package domain;

/**
 * There should be only UI Updater inside it.
 *
 */
public class ResponseInterpreter {
    private static ResponseInterpreter ourInstance;

    public static ResponseInterpreter getInstance() {
        if(ourInstance == null)
            ourInstance = new ResponseInterpreter();
        return ourInstance;
    }

    private ResponseInterpreter() {
    }

    public void interpret(String message){
        switch (message.charAt(0)){
            case GameLogic.rollFlag:
                interpretRoll(message.split("[|]"));
                break;

            default:
                break;
        }
    }

    private void interpretRoll(String[] message){
        String name = message[1];
        int[] faceValues = MessageConverter.convertStringToIntArray(message[2]);

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
