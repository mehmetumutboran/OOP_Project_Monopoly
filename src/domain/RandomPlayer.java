package domain;

import domain.player.Player;

public class RandomPlayer extends Player {

    public RandomPlayer(){
        this("");
    }

    public RandomPlayer(String name) {
        super(name);
    }


    public synchronized void playTurn() {
        GameLogic.getInstance().roll();
        GameLogic.getInstance().finishTurn();
    }

}
