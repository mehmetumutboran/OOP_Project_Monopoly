package domain;

import domain.player.Player;

public class RandomPlayer extends Player {

    public RandomPlayer(){
        this("");
    }

    public RandomPlayer(String name) {
        super(name);
    }


    private void playTurn() {

        GameLogic.getInstance().roll();

        GameLogic.getInstance().finishTurn();

    }

    public synchronized boolean checkTurn() {
        if(GameLogic.getInstance().getPlayers().peekFirst().equals(this)){
            playTurn();
            return true;
        }
        return false;
    }
}
