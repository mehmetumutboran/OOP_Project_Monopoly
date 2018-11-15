package domain.controller;

import domain.GameLogic;

public class PlayerActionController {
    private static PlayerActionController ourInstance;


    public static PlayerActionController getInstance() {
        if (ourInstance == null)
            ourInstance = new PlayerActionController();
        return ourInstance;
    }

    private PlayerActionController() {
    }


    public void roll() {
        GameLogic.getInstance().roll();
    }


    public void finishTurn() {
        GameLogic.getInstance().finishTurn();
    }

    public boolean buy() {
        System.out.println("in player action controller");
        return ( GameLogic.getInstance().buy() );

    }
}
