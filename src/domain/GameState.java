package domain;

public class GameState {
    private static GameState gs;

    private GameState() {
    }

    public static GameState getGs() {
        if (gs == null) {
            gs = new GameState();
        }

        return gs;
    }
}
