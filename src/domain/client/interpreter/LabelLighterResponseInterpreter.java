package domain.client.interpreter;

import domain.client.ClientCommunicationHandler;
import domain.client.UIUpdater;
import domain.server.board.Board;

import java.util.ArrayList;

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
        UIUpdater.getInstance().updateLabels(list,actionType, 1);
        ClientCommunicationHandler.getInstance().sendReceived();
    }


}
