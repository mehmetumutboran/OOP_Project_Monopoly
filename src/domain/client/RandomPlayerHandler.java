package domain.client;

import java.util.Timer;
import java.util.TimerTask;

public class RandomPlayerHandler {
    private static RandomPlayerHandler ourInstance;
    private int botDiff = 3;

    public static RandomPlayerHandler getInstance() {
        if (ourInstance == null) {
            ourInstance = new RandomPlayerHandler();
        }
        return ourInstance;
    }

    private RandomPlayerHandler() {
    }

    public void playBotTurn(boolean isSecondMove) {
        Timer timer = new Timer();
        long delay = 250L;

        TimerTask timerTaskBuy = new TimerTask() {
            @Override
            public void run() {
                RandomPlayerActionFactory.getInstance().generateStrategy("Buy", botDiff).doRandomPlayerAction();
            }
        };
        timer.schedule(timerTaskBuy, delay);


        TimerTask timerTaskPayRent = new TimerTask() {
            @Override
            public void run() {
                RandomPlayerActionFactory.getInstance().generateStrategy("PayRent").doRandomPlayerAction();
            }
        };
        timer.schedule(timerTaskPayRent, 2 * delay);


        TimerTask timerTaskDrawCard = new TimerTask() {
            @Override
            public void run() {
                RandomPlayerActionFactory.getInstance().generateStrategy("DrawCard").doRandomPlayerAction();
            }
        };
        timer.schedule(timerTaskDrawCard, 3 * delay);


        TimerTask timerTaskFinishTurn = new TimerTask() {
            @Override
            public void run() {
                RandomPlayerActionFactory.getInstance().generateStrategy("FinishTurn", isSecondMove).doRandomPlayerAction();
            }
        };
        timer.schedule(timerTaskFinishTurn, 4 * delay);

    }

}
