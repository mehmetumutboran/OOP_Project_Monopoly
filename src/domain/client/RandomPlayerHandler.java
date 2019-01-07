package domain.client;

import domain.server.player.RandomPlayer;
import domain.util.GameInfo;

import java.util.Timer;
import java.util.TimerTask;

public class RandomPlayerHandler {
    private static RandomPlayerHandler ourInstance;
    private long moveOffset = 3000L;

    public static RandomPlayerHandler getInstance() {
        if (ourInstance == null) {
            ourInstance = new RandomPlayerHandler();
        }
        return ourInstance;
    }

    private RandomPlayerHandler() {
    }

    public void playNormalBotTurn() {
        Timer timer = new Timer();
        long delay = 250L;
        String difficulty = ((RandomPlayer) GameInfo.getInstance().getCurrentPlayer()).getDifficulty();
        int botDiff;
        if (difficulty.equals("Hard"))
            botDiff = 2;
        else if (difficulty.equals("Medium"))
            botDiff = 1;
        else if (difficulty.equals("Easy"))
            botDiff = 0;
        else botDiff = 0;

        TimerTask timerTaskBuy = new TimerTask() {
            @Override
            public void run() {
                RandomPlayerActionFactory.getInstance().generateStrategy("Buy", botDiff).doRandomPlayerAction();
            }
        };
        timer.schedule(timerTaskBuy, delay + moveOffset);


        TimerTask timerTaskPayRent = new TimerTask() {
            @Override
            public void run() {
                RandomPlayerActionFactory.getInstance().generateStrategy("PayRent").doRandomPlayerAction();
            }
        };
        timer.schedule(timerTaskPayRent, 2 * delay + moveOffset);


        TimerTask timerTaskDrawCard = new TimerTask() {
            @Override
            public void run() {
                RandomPlayerActionFactory.getInstance().generateStrategy("DrawCard").doRandomPlayerAction();
            }
        };
        timer.schedule(timerTaskDrawCard, 3 * delay + moveOffset);


        TimerTask timerTaskFinishTurn = new TimerTask() {
            @Override
            public void run() {
                RandomPlayerActionFactory.getInstance().generateStrategy("FinishTurn").doRandomPlayerAction();
            }
        };
        timer.schedule(timerTaskFinishTurn, 4 * delay + moveOffset);

    }

    public void playMrMonopolyBotTurn() {
        Timer timer = new Timer();
        long delay = 250L;
        String difficulty = ((RandomPlayer) GameInfo.getInstance().getCurrentPlayer()).getDifficulty();
        int botDiff;
        if (difficulty.equals("Hard"))
            botDiff = 2;
        else if (difficulty.equals("Medium"))
            botDiff = 1;
        else if (difficulty.equals("Easy"))
            botDiff = 0;
        else botDiff = 0;

        TimerTask timerTaskBuy = new TimerTask() {
            @Override
            public void run() {
                RandomPlayerActionFactory.getInstance().generateStrategy("Buy", botDiff).doRandomPlayerAction();
            }
        };
        timer.schedule(timerTaskBuy, delay + moveOffset);


        TimerTask timerTaskPayRent = new TimerTask() {
            @Override
            public void run() {
                RandomPlayerActionFactory.getInstance().generateStrategy("PayRent").doRandomPlayerAction();
            }
        };
        timer.schedule(timerTaskPayRent, 2 * delay + moveOffset);


        TimerTask timerTaskDrawCard = new TimerTask() {
            @Override
            public void run() {
                RandomPlayerActionFactory.getInstance().generateStrategy("DrawCard").doRandomPlayerAction();
            }
        };
        timer.schedule(timerTaskDrawCard, 3 * delay + moveOffset);


        TimerTask timerTaskMrMonopoly = new TimerTask() {
            @Override
            public void run() {
                RandomPlayerActionFactory.getInstance().generateStrategy("MrMonopoly").doRandomPlayerAction();
            }
        };
        timer.schedule(timerTaskMrMonopoly, delay + 2 * moveOffset);

        TimerTask timerTaskBuy1 = new TimerTask() {
            @Override
            public void run() {
                RandomPlayerActionFactory.getInstance().generateStrategy("Buy", botDiff).doRandomPlayerAction();
            }
        };
        timer.schedule(timerTaskBuy1, 2 * delay + 2 * moveOffset);


        TimerTask timerTaskPayRent1 = new TimerTask() {
            @Override
            public void run() {
                RandomPlayerActionFactory.getInstance().generateStrategy("PayRent").doRandomPlayerAction();
            }
        };
        timer.schedule(timerTaskPayRent1, 3 * delay + 2 * moveOffset);


        TimerTask timerTaskDrawCard1 = new TimerTask() {
            @Override
            public void run() {
                RandomPlayerActionFactory.getInstance().generateStrategy("DrawCard").doRandomPlayerAction();
            }
        };
        timer.schedule(timerTaskDrawCard1, 4 * delay + 2 * moveOffset);


        TimerTask timerTaskFinishTurn = new TimerTask() {
            @Override
            public void run() {
                RandomPlayerActionFactory.getInstance().generateStrategy("FinishTurn").doRandomPlayerAction();
            }
        };
        timer.schedule(timerTaskFinishTurn, 3 * moveOffset);
    }

}
