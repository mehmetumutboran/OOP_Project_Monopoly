package domain.client.interpreter;

import domain.client.UIUpdater;
import domain.server.board.Board;
import domain.server.board.Property;
import domain.util.MessageConverter;

public class HurricaneResponseInterpreter implements ResponseInterpretable {
    @Override
    public void interpret(String[] message) {
        String name = message[1];
        String input = message[2];

        if(input.equals("fail")){
            UIUpdater.getInstance().setMessage(name + " drew Hurricane but no one has upgraded buildings!");
        }else{
            String[] hurricaneSquareNames = MessageConverter.convertStringToArray(input);
            String victimName = ((Property) Board.getInstance().getNameGivenSquare(hurricaneSquareNames[0])).getOwner();
            for (int i = 0; i < hurricaneSquareNames.length; i++) {
                ((Property) Board.getInstance().getNameGivenSquare(hurricaneSquareNames[i])).downgrade();
            }
            UIUpdater.getInstance().setMessage(name + " drew Hurricane and chose "+ victimName+ " as victim");
        }
    }
}
