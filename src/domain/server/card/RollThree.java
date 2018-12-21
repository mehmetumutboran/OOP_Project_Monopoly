package domain.server.card;

public class RollThree extends Card {

    public RollThree(String name) {
        super(name);
    }

    @Override
    public boolean doAction(String name) {
        return false;
    }
}
