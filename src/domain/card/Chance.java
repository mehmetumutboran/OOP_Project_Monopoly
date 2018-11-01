package domain.card;

public class Chance extends Card{

    public Chance(String name) {
        super(name);
    }

    @Override
    public boolean doAction() {
        return false;
    }
}
