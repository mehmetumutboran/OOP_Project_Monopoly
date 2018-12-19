package domain.server.player;

public class RandomPlayer extends Player {

    public RandomPlayer() {
        this("");
    }

    public RandomPlayer(String name) {
        super(name);
        this.setReadiness("Bot");
    }

    public RandomPlayer(String name, String color, String readiness) {
        super(name, color, readiness);
    }

    public RandomPlayer(Player player) {
        super("Bot " + player.getName(),
                player.getToken(),
                player.getBalance(),
                player.getOwnedProperties(),
                player.getOwnedUtilities(),
                player.getOwnedRailroads(),
                player.getMortgagedSquares(),
                "Bot",
                player.getDoubleCounter(),
                player.isInJail());
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
