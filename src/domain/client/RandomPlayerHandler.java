package domain.client;

import domain.util.Flags;
import domain.util.GameInfo;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class RandomPlayerHandler{
    private static RandomPlayerHandler ourInstance;
    private float botChance = 0.4f;

    public static RandomPlayerHandler getInstance() {
        if (ourInstance == null) {
            ourInstance = new RandomPlayerHandler();
        }
        return ourInstance;
    }

    private RandomPlayerHandler() {
    }

    public void playBotTurn() {
        int playCount = 2; // 1 for roll and 1 for finish turn initially
        TimerTask timerTaskRoll = new TimerTask() {
            @Override
            public void run() {
                roll();
            }
        };
        TimerTask timerTaskBuy;
        TimerTask timerTaskFinish = new TimerTask() {
            @Override
            public void run() {
                finishTurn();
            }
        };

        Timer timer = new Timer();
        long delay = 500L;

        if(lucky()){
            timerTaskBuy = new TimerTask() {
                @Override
                public void run() {
                    buy();
                }
            };
            timer.schedule(timerTaskBuy,(++playCount-1)*delay);
        }

        timer.schedule(timerTaskRoll,delay);
        timer.schedule(timerTaskFinish,playCount*delay);
    }

    private boolean lucky() {
        return botChance > new Random().nextFloat();
    }

    public void roll() {
        System.out.println("Bot roll");
        ClientCommunicationHandler.getInstance().sendRequest(Flags.getFlag("Roll"), GameInfo.getInstance().getCurrentPlayerName());
    }


    public void finishTurn() {
        ClientCommunicationHandler.getInstance().sendRequest(Flags.getFlag("Finish"), GameInfo.getInstance().getCurrentPlayerName());
    }

    public void buy() {
        System.out.println("Bot roll");
        ClientCommunicationHandler.getInstance().sendRequest(Flags.getFlag("Buy"), GameInfo.getInstance().getCurrentPlayerName());
    }


//    // public void upgrade() {GameLogic.getInstance().upgrade(); }
//
//    //public void downgrade(){ GameLogic.getInstance().downgrade(); }
//
//    public boolean buy() {
//        System.out.println("in player action controller");
//        return (GameLogic.getInstance().buy());
//
//    }
//
//    public boolean payRent() {
//        System.out.println("in player action controller");
//        return (GameLogic.getInstance().payRent());
//
//    }

}
