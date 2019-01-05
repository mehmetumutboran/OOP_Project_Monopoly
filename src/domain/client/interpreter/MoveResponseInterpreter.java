package domain.client.interpreter;

import domain.client.ClientCommunicationHandler;
import domain.client.RandomPlayerHandler;
import domain.client.UIUpdater;
import domain.server.player.RandomPlayer;
import domain.util.GameInfo;
import domain.util.MessageConverter;

public class MoveResponseInterpreter implements ResponseInterpretable {
    @Override
    public void interpret(String[] message) {
        String name = message[1];
        int[] location = MessageConverter.convertStringToIntArray(message[2].substring(0, message[2].indexOf("@")), ',');
        String locName = message[2].substring(message[2].indexOf("@") + 1);
        boolean isSecondMove = message[3].equals("1");
//        System.out.println(Arrays.toString(MessageConverter.convertStringToIntArray(message[2], ',')));
//        System.out.println(Arrays.toString(location));

        GameInfo.getInstance().getPlayer(name).getToken().setLocation(location);

        if (GameInfo.getInstance().isBot(GameInfo.getInstance().getCurrentPlayer().getName()))
            RandomPlayerHandler.getInstance().playBotTurn(isSecondMove);

        UIUpdater.getInstance().setMessage(name + " moved to " + locName); //TODO Mrmonopoly

        ClientCommunicationHandler.getInstance().sendReceived();
    }
}
