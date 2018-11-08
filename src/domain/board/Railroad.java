package domain.board;

public class Railroad extends DeedSquare {
    private boolean hasDepot;

    public Railroad(String name, int layer, int index, int buyValue, int rent) {
        super(name, layer, index, buyValue, rent);
        hasDepot = false;
    }

    public boolean isHasDepot() {
        return hasDepot;
    }

    public void setHasDepot(boolean hasDepot) {
        this.hasDepot = hasDepot;
    }
}
