package domain.server.board;

public class Railroad extends DeedSquare {
    private boolean hasDepot;

    public Railroad() {
        this("", 0, 0, 0, 0);
    }

    public Railroad(String name, int layer, int index, int buyValue, int rent) {
        super(name, layer, index, buyValue, rent, null);
        hasDepot = false;
    }

    public void updateRent() {

    }

    public boolean isHasDepot() {
        return hasDepot;
    }

    public void setHasDepot(boolean hasDepot) {
        this.hasDepot = hasDepot;
    }


}
