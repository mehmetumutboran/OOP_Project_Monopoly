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

    public String generateGameState(char flag){
        //TODO
        return flag + PlayerActionController.getInstance().getPlayers().peekFirst().toJSON();
    }

}
