package domain.client.interpreter;

import domain.client.ClientCommunicationHandler;
import domain.client.UIUpdater;
import domain.server.board.Board;
import domain.server.board.Square;
import domain.util.GameInfo;
import domain.util.MessageConverter;
import network.client.ClientReceiver;

import java.util.ArrayList;
import java.util.Arrays;

public class LabelLighterResponseInterpreter implements ResponseInterpretable {
    @Override
    public void interpret(String[] message) {
        //String name = message[1];
        String actionType = message[2];
        String [] sqnames = message[1].substring(1, message[1].length()-1).split(",\\s");
        ArrayList<int[]> list = new ArrayList<>();
        for (String s : sqnames){
                list.add(Board.getInstance().getNameGivenSquare(s).getLocation());
        }
        UIUpdater.getInstance().updateLabels(list,actionType);
        ClientCommunicationHandler.getInstance().sendReceived();
    }


}
