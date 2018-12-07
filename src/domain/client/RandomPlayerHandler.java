package domain.client;

import domain.util.Flags;
import domain.util.GameInfo;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class RandomPlayerHandler{
    private static RandomPlayerHandler ourInstance;

    public static RandomPlayerHandler getInstance() {
        if (ourInstance == null) {
            ourInstance = new RandomPlayerHandler();
        }
        return ourInstance;
    }

    private RandomPlayerHandler() {
    }

    public void playBotTurn() {
        TimerTask timerTaskRoll = new TimerTask() {
            @Override
            public void run() {
                roll();
            }
        };

        TimerTask timerTaskFinish = new TimerTask() {
            @Override
            public void run() {
                finishTurn();
            }
        };

        Timer timer = new Timer();
        long delay = 500L;

        timer.schedule(timerTaskRoll,delay);
        timer.schedule(timerTaskFinish,2*delay);
    }

    public void roll() {
        ClientCommunicationHandler.getInstance().sendRequest(Flags.getFlag("Roll"), GameInfo.getInstance().getCurrentPlayer());
    }


    public void finishTurn() {
        ClientCommunicationHandler.getInstance().sendRequest(Flags.getFlag("Finish"), GameInfo.getInstance().getCurrentPlayer());
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
