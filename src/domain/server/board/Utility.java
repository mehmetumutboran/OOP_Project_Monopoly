package domain.server.board;

public class Utility extends DeedSquare {

    public Utility() {
        super("", 0, 0, 0, 0, null);
    }

    public Utility(String name, int layer, int index, int buyValue, int rent) {
        super(name, layer, index, buyValue, rent, null);
    }
}
