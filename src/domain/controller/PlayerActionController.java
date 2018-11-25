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
//        GameLogic.getInstance().roll(); //TODO Send to Server request
    }


    public void finishTurn() {
        GameLogic.getInstance().finishTurn();
    }
    // public void upgrade() {GameLogic.getInstance().upgrade(); }

    //public void downgrade(){ GameLogic.getInstance().downgrade(); }

    public boolean buy() {
        System.out.println("in player action controller");
        return (GameLogic.getInstance().buy());

    }

    public boolean rent() {
        System.out.println("in player action controller");
        return (GameLogic.getInstance().payRent());

    }
}
