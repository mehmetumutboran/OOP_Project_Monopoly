package domain.client.interpreter;

import domain.client.ClientCommunicationHandler;
import domain.client.RandomPlayerActionFactory;
import domain.client.RandomPlayerHandler;
import domain.client.UIUpdater;
import domain.server.player.RandomPlayer;
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
        boolean isSecondMove = message[3].equals("1");
//        System.out.println(Arrays.toString(MessageConverter.convertStringToIntArray(message[2], ',')));
//        System.out.println(Arrays.toString(location));

        GameInfo.getInstance().getPlayer(name).getToken().setLocation(location);

        if (GameInfo.getInstance().isBot(GameInfo.getInstance().getCurrentPlayer().getName())) {
            if(isSecondMove) {
                Timer timer = new Timer();
                long delay = 1000L;
                TimerTask timerTaskPlayBotTurn = new TimerTask() {
                    @Override
                    public void run() {
                        UIUpdater.getInstance().setMessage(name + " moved to " + locName);
                        RandomPlayerHandler.getInstance().playBotTurn(true);
                    }
                };
                timer.schedule(timerTaskPlayBotTurn, delay);

                ClientCommunicationHandler.getInstance().sendReceived();

                return;
            }else{
                RandomPlayerHandler.getInstance().playBotTurn(false);
            }
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
