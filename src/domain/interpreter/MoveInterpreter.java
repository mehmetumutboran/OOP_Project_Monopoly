package domain.interpreter;

import domain.GameLogic;
import domain.MessageConverter;
import domain.UIUpdater;
import domain.board.Board;
import domain.player.Player;

public class MoveInterpreter implements Interpreter {
    @Override
    public void interpret(String[] message) {
        String name = message[1];
        int[] location = MessageConverter.convertStringToIntArray(message[2]);

        Player player = GameLogic.getInstance().getPlayer(name);

        player.getToken().setLocation(location);

        UIUpdater.getInstance().setMessage(name + " moved to " + Board.getInstance().getSquare(location[0], location[1]).getName()); //TODO Mrmonopoly
    }
}
