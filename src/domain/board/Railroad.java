package domain.board;

public class Railroad extends DeedSquare{
    private boolean hasDepot;

    public Railroad(String name, int buyValue, int rent) {
        super(name, buyValue, rent);
    }

    public boolean isHasDepot() {
        return hasDepot;
    }

    public void setHasDepot(boolean hasDepot) {
        this.hasDepot = hasDepot;
    }
}
