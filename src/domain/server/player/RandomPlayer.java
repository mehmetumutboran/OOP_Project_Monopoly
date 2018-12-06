package domain.server.player;

public class RandomPlayer extends Player {

    public RandomPlayer() {
        this("");
    }

    public RandomPlayer(String name) {
        super(name);
        this.setReadiness("Bot");
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
