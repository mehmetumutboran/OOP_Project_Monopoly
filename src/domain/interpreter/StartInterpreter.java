package domain.interpreter;

import domain.controller.ServerCommunicationHandler;

public class StartInterpreter implements Interpreter {
    @Override
    public void interpret(String[] message) {
        String name = message[1];


//        ServerCommunicationHandler.getInstance().sendResponse(name, );

    }
}
