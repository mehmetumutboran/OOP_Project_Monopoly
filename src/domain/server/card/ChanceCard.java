package domain.server.card;

public class ChanceCard extends Card {

    public ChanceCard(String name) {
        super(name);
    }

    @Override
    public boolean doAction() {
        return false;
    }
}
