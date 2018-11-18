package domain;

import domain.board.DeedSquare;

public class GameState {
    private static GameState gs;

    private GameState() {
    }

    public static GameState getInstance() {
        if (gs == null) {
            gs = new GameState();
        }

        return gs;
    }

    public String generateCurrentAction(char flag) {
        //TODO
        return flag + GameLogic.getInstance().getPlayers().peekFirst().toJSON();
    }

    public String generatePoolAction(char flag , int money) {
        //TODO
        return flag + Integer.toString(money);
    }

    public String generateupdownGradeAction(char flag, DeedSquare square) {
        return flag + GameLogic.getInstance().getPlayers().peekFirst().toJSON() + "~" + square.toJSON();
    }

}
