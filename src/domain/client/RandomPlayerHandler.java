package domain.client;

import domain.server.board.Board;
import domain.server.board.DeedSquare;
import domain.server.board.Square;
import domain.util.Flags;
import domain.util.GameInfo;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class RandomPlayerHandler {
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

    public void playBotTurn(boolean isSecondMove) {
        Timer timer = new Timer();
        long delay = 500L;

        TimerTask timerTaskBuy = new TimerTask() {
            @Override
            public void run() {
                buy();
            }
        };
        if (lucky() && isBuyable()) {
            System.out.println("Bot is trying to buy here!!");
            timer.schedule(timerTaskBuy, delay);
        }


        TimerTask timerTaskPayRent = new TimerTask() {
            @Override
            public void run() {
                payRent();
            }
        };
        if (mustPayRent()) {
            System.out.println("Bot is trying to pay rent here!!");
            timer.schedule(timerTaskPayRent, delay);
        }

        if (GameInfo.getInstance().getCurrentPlayer().getFaceValues()[2] == 7 && isSecondMove) {
            TimerTask timerTaskFinish = new TimerTask() {
                @Override
                public void run() {
                    finishTurn();
                }
            };
            timer.schedule(timerTaskFinish, 2 * delay);
        } else if(GameInfo.getInstance().getCurrentPlayer().getFaceValues()[2] != 7) {
            TimerTask timerTaskFinish = new TimerTask() {
                @Override
                public void run() {
                    finishTurn();
                }
            };
            timer.schedule(timerTaskFinish, 2 * delay);
        }

    }

    private boolean isBuyable() {
        int[] loc = GameInfo.getInstance().getCurrentPlayer().getToken().getLocation().clone();
        Square sq = Board.getInstance().getSquare(loc[0], loc[1]);
        return sq instanceof DeedSquare && ((DeedSquare) sq).getOwner() == null;
    }

    private boolean mustPayRent() {
        int[] loc = GameInfo.getInstance().getCurrentPlayer().getToken().getLocation().clone();
        Square sq = Board.getInstance().getSquare(loc[0], loc[1]);
        return sq instanceof DeedSquare &&
                ((DeedSquare) sq).getOwner() != null &&
                !((DeedSquare) sq).getOwner().equals(GameInfo.getInstance().getCurrentPlayer().getName());

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
        System.out.println("Bot buy");
        ClientCommunicationHandler.getInstance().sendRequest(Flags.getFlag("Buy"), GameInfo.getInstance().getCurrentPlayerName());
    }

    public void payRent() {
        System.out.println("Bot payRent");
        ClientCommunicationHandler.getInstance().sendRequest(Flags.getFlag("PayRent"), GameInfo.getInstance().getCurrentPlayerName());
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
