package domain.server.player;

import domain.server.board.Property;
import domain.server.board.Railroad;
import domain.server.board.Utility;

import java.util.ArrayList;

public class RandomPlayer extends Player {

    private String difficulty = "Medium";

    public RandomPlayer() {
        this("", "Medium");
    }

    public RandomPlayer(String name, String difficulty) {
        super(name);
        this.setReadiness("Bot");
        this.difficulty = difficulty;
    }

    public RandomPlayer(String name, String color, String readiness, String difficulty) {
        super(name, color, readiness);
        this.difficulty = difficulty;
    }

    public RandomPlayer(Player player) {
        super("MBot " + player.getName(),
                player.getToken(),
                player.getBalance(),
                player.getOwnedProperties(),
                player.getOwnedUtilities(),
                player.getOwnedRailroads(),
                "Bot",
                player.getDoubleCounter(),
                player.isInJail());

        player.getOwnedProperties().forEach(x -> x.setOwner(this.getName()));
        player.getOwnedUtilities().forEach(x -> x.setOwner(this.getName()));
        player.getOwnedRailroads().forEach(x -> x.setOwner(this.getName()));
    }


    public RandomPlayer(String name, Token token, int balance,
                        ArrayList<Property> propertyList, ArrayList<Utility> utilityList,
                        ArrayList<Railroad> railroadList,
                        String readiness, int doubleCounter, boolean isInJail) {
        super(name,
                token,
                balance,
                propertyList,
                utilityList,
                railroadList,
                readiness,
                doubleCounter,
                isInJail);
        if (!readiness.equals("Bot")) throw new ClassCastException();
    }

    @Override
    public String toString() {
        return super.toString() + "," + difficulty;
    }

    public String getDifficulty() {
        return difficulty;
    }
}
