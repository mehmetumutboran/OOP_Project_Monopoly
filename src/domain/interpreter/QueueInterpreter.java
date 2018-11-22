package domain.interpreter;

import domain.GameLogic;
import domain.MessageConverter;
import domain.RandomPlayer;
import domain.UIUpdater;
import domain.player.Player;

public class QueueInterpreter implements Interpreter {
    @Override
    public void interpret(String[] message) {
        GameLogic.getInstance().setPlayers(MessageConverter.convertStringToDeque(message[1]));

        //Bots play on only host's program
        if (GameLogic.getInstance().getPlayerList().get(0).getReadiness().equals("Host")) {

            // Check for bots if they starts the game.
            for (Player player : GameLogic.getInstance().getPlayerList()) {
                if (player.getReadiness().equals("Bot") && ((RandomPlayer) player).checkTurn()) {
                    break;
                }
            }
        }
        UIUpdater.getInstance().turnUpdate();
    }
}
