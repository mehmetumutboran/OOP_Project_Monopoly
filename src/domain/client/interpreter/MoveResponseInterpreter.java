package domain.client.interpreter;

import domain.client.UIUpdater;
import domain.server.GameLogic;
import domain.server.board.Board;
import domain.server.player.Player;
import domain.util.MessageConverter;

import java.util.Arrays;

public class MoveResponseInterpreter implements ResponseInterpretable {
    @Override
    public void interpret(String[] message) {
        String name = message[1];
        int[] location = MessageConverter.convertStringToIntArray(message[2]);

        System.out.println(Arrays.toString(MessageConverter.convertStringToIntArray(message[2])));
        System.out.println(Arrays.toString(location));

        Player player = GameLogic.getInstance().getPlayer(name);

        player.getToken().setLocation(location);

        UIUpdater.getInstance().setMessage(name + " moved to " + Board.getInstance().getSquare(location[0], location[1]).getName()); //TODO Mrmonopoly
    }
}
