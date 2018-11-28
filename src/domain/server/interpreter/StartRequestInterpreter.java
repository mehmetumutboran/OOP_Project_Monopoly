package domain.server.interpreter;

import domain.server.controller.ServerCommunicationHandler;
import domain.server.util.GameInfo;
import domain.util.Flags;

public class StartRequestInterpreter implements RequestInterpretable {
    @Override
    public void interpret(String[] message, int index) {
        String name = message[1];
        int count = GameInfo.getInstance().checkReadiness();
        if(count!=0){
            ServerCommunicationHandler.getInstance()
                    .sendResponse(Flags.getFlag("DontStart"), index, count, name);
            return;
        }

       ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Start"), name);

    }
}
