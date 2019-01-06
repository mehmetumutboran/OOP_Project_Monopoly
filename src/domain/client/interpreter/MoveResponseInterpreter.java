package domain.client.interpreter;

import domain.client.ClientCommunicationHandler;
import domain.client.RandomPlayerHandler;
import domain.client.UIUpdater;
import domain.util.GameInfo;
import domain.util.MessageConverter;

import java.util.Timer;
import java.util.TimerTask;

public class MoveResponseInterpreter implements ResponseInterpretable {
    @Override
    public void interpret(String[] message) {
        String name = message[1];
        int[] location = MessageConverter.convertStringToIntArray(message[2].substring(0, message[2].indexOf("@")), ',');
        String locName = message[2].substring(message[2].indexOf("@") + 1);
        boolean isFirstMove = message[3].equals("0");
//        System.out.println(Arrays.toString(MessageConverter.convertStringToIntArray(message[2], ',')));
//        System.out.println(Arrays.toString(location));

        GameInfo.getInstance().getPlayer(name).getToken().setLocation(location);
        if (!isFirstMove)
            GameInfo.getInstance().getPlayer(name).setSecondMove(true);

        if (GameInfo.getInstance().isMyselfHost() && GameInfo.getInstance().isBot(name)) {
            if (GameInfo.getInstance().getPlayer(name).getFaceValues()[2] == 7 && isFirstMove)
                RandomPlayerHandler.getInstance().playMrMonopolyBotTurn();
            else if (isFirstMove) // todo jail is wrong here
                RandomPlayerHandler.getInstance().playNormalBotTurn();
        }

        Timer timer = new Timer();
        long delay = 300L;
        TimerTask timerTaskUIUpdate = new TimerTask() {
            @Override
            public void run() {
                UIUpdater.getInstance().setMessage(name + " moved to " + locName);
            }
        };
        timer.schedule(timerTaskUIUpdate, delay);


        ClientCommunicationHandler.getInstance().sendReceived();
    }
}
