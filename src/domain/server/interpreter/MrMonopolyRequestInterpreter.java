package domain.server.interpreter;

import domain.server.ReceivedChecker;
import domain.server.controller.ServerCommunicationHandler;
import domain.server.move.MoveControl;
import domain.server.util.ButtonStringGenerator;
import domain.util.Flags;
import domain.util.GameInfo;

public class MrMonopolyRequestInterpreter implements RequestInterpretable {

    boolean mrMonopolyChecked = false;


    private static final int SECOND_LAYER_SQ = 24;
    private static final int FIRST_LAYER_SQ = 40;
    private static final int ZEROTH_LAYER_SQ = 56;

    @Override
    public void interpret(String[] message, int index) {
        String name = message[1];
        MoveControl.getInstance().checkMrMonopoly(name);

        while (true) {
            if (ReceivedChecker.getInstance().checkReceived()) {
                ReceivedChecker.getInstance().setReceived();
                break;
            }
        }

        String msg = ButtonStringGenerator.getInstance().getButtonStringForNextSquare(name);
        char[] msg2 = msg.toCharArray();
        msg2[11] = '0';
        msg2[4] = '1';
        msg = String.valueOf(msg2);
        if (!GameInfo.getInstance().isBot(name))
            ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Button"), index, msg, name);

        //    ServerCommunicationHandler.getInstance()
        //            .sendResponse(Flags.getFlag("MrMonopoly"), name);
    }


}