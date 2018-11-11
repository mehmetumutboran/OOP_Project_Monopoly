package domain.board;

import domain.player.Player;

/**
 * Class for Buyable Squares.
 */
public abstract class DeedSquare extends Square {
    private int buyValue;
    private int rent;
    private Player owner;
    private boolean isMortgaged;

    public DeedSquare(String name, int layer, int index, int buyValue, int rent) {
        super(name, layer, index);
        this.buyValue = buyValue;
        this.rent = rent;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public boolean isMortgaged() {
        return isMortgaged;
    }

    public void setMortgaged(boolean mortgaged) {
        isMortgaged = mortgaged;
    }

    public boolean isOwned() {
        return owner == null;
    }
}
