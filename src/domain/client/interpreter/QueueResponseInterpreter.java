package domain.client.interpreter;

import domain.client.UIUpdater;
import domain.server.GameLogic;
import domain.server.player.Player;
import domain.server.player.RandomPlayer;
import domain.util.MessageConverter;

public class QueueResponseInterpreter implements ResponseInterpretable {
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
