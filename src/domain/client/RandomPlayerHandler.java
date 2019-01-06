package domain.client;

import domain.server.player.RandomPlayer;
import domain.util.GameInfo;

import java.util.Timer;
import java.util.TimerTask;

public class RandomPlayerHandler {
    private static RandomPlayerHandler ourInstance;

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
                RandomPlayerActionFactory.getInstance().generateStrategy("FinishTurn").doRandomPlayerAction();
            }
        };
        timer.schedule(timerTaskFinishTurn, 4 * delay);

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


        TimerTask timerTaskMrMonopoly = new TimerTask() {
            @Override
            public void run() {
                RandomPlayerActionFactory.getInstance().generateStrategy("MrMonopoly").doRandomPlayerAction();
            }
        };
        timer.schedule(timerTaskMrMonopoly, 4 * delay);

        TimerTask timerTaskBuy1 = new TimerTask() {
            @Override
            public void run() {
                RandomPlayerActionFactory.getInstance().generateStrategy("Buy", botDiff).doRandomPlayerAction();
            }
        };
        timer.schedule(timerTaskBuy1, 7 * delay);


        TimerTask timerTaskPayRent1 = new TimerTask() {
            @Override
            public void run() {
                RandomPlayerActionFactory.getInstance().generateStrategy("PayRent").doRandomPlayerAction();
            }
        };
        timer.schedule(timerTaskPayRent1, 8 * delay);


        TimerTask timerTaskDrawCard1 = new TimerTask() {
            @Override
            public void run() {
                RandomPlayerActionFactory.getInstance().generateStrategy("DrawCard").doRandomPlayerAction();
            }
        };
        timer.schedule(timerTaskDrawCard1, 9 * delay);


        TimerTask timerTaskFinishTurn = new TimerTask() {
            @Override
            public void run() {
                RandomPlayerActionFactory.getInstance().generateStrategy("FinishTurn").doRandomPlayerAction();
            }
        };
        timer.schedule(timerTaskFinishTurn, 10 * delay);
    }

}
