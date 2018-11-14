package domain;

import domain.controller.MonopolyGameController;
import domain.controller.PlayerActionController;

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

    public String generateCurrentAction(char flag){
        //TODO
        String s = flag + GameLogic.getInstance().getPlayers().peekFirst().toJSON();
//        System.out.println("GameState Message: \t" + s);
        return s;
    }

}
