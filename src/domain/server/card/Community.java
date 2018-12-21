package domain.server.card;

public class Community extends Card {

    public Community(String name) {
        super(name);
    }

    @Override
    public boolean doAction(String name) {
        return false;
    }
}
