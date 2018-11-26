package domain.server.player;

import domain.server.GameLogic;

import java.util.Random;

public class RandomPlayer extends Player {

    public RandomPlayer() {
        this("");
    }

    public RandomPlayer(String name) {
        super(name);
    }


    private void playTurn() {
        GameLogic.getInstance().roll(this.getName());

        GameLogic.getInstance().payRent();

        while (true) {
            if (selectAction()) break;
        }

        GameLogic.getInstance().finishTurn();

    }

    private boolean selectAction() {
        int x = (new Random()).nextInt(7);

        switch (x) {
            case 0:
                return GameLogic.getInstance().buy();
//            case 1:
//                return GameLogic.getInstance().sell();
//            case 2:
//                return GameLogic.getInstance().mortgage();
//            case 3:
//                return GameLogic.getInstance().unmortgage();
//            case 4:
//                return GameLogic.getInstance().upgrade();
//            case 5:
//                return GameLogic.getInstance().downgrade();
            default:
                return true;
        }
    }

    public synchronized boolean checkTurn() {
        if (GameLogic.getInstance().getCurrentPlayer().equals(this)) {
            playTurn();
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
