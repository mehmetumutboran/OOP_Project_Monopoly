package domain.card;

public class Community extends Card{

    public Community(String name) {
        super(name);
    }

    @Override
    public boolean doAction() {
        return false;
    }
}
