package domain;

import domain.controller.MonopolyGameController;

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

    public String generateGameState(){
        //TODO
        return MonopolyGameController.getInstance().getPlayerList().get(0).toJSON();
    }

}
