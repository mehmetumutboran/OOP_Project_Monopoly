package domain.client.interpreter;

public class StartResponseInterpreter implements ResponseInterpretable {
    @Override
    public void interpret(String[] message) {
        String name = message[1];


//        ServerCommunicationHandler.getInstance().sendResponse(name, );

    }
}
