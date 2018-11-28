package domain.server.interpreter;

public class StartRequestInterpreter implements RequestInterpretable {
    @Override
    public void interpret(String[] message, int index) {
        String name = message[1];


//        ServerCommunicationHandler.getInstance().sendResponse(name, );

    }
}
