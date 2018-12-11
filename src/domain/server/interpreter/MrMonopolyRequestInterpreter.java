package domain.server.interpreter;

import domain.server.GameLogic;
import domain.server.controller.ServerCommunicationHandler;
import domain.server.util.ButtonStringGenerator;
import domain.util.Flags;

public class MrMonopolyRequestInterpreter implements RequestInterpretable {

    @Override
    public void interpret(String[] message, int index) {
        String name = message[1];
        GameLogic.getInstance().checkMrMonopoly(name);
        String msg = ButtonStringGenerator.getInstance().getButtonString(name);
        char[] msg2 = msg.toCharArray();
        msg2[11] = '0';
        msg2[4] = '1';
        msg = String.valueOf(msg2);
        ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Button"), index,msg , name);

    //    ServerCommunicationHandler.getInstance()
    //            .sendResponse(Flags.getFlag("MrMonopoly"), name);
    }
}