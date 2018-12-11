package domain.server.board;

public class Railroad extends DeedSquare {
    private boolean hasDepot;
    private int[] rents = new int[10];
    private int currentRent;

    public Railroad() {
        this("", 0, 0, 0, new int[]{0, 0, 0, 0,0,0,0,0,0,0 });
    }

    public Railroad(String name, int layer, int index, int buyValue, int[] rents) {
        super(name, layer, index, buyValue,  null,rents);
        this.rents = rents.clone();
        this.currentRent = rents[0];
        hasDepot = false;
    }

    public int[] getRents() {
        return rents;
    }

    public void setRents(int[] rents) {
        this.rents = rents;
    }

    public int getCurrentRent() {
        return currentRent;
    }

    public void setCurrentRent(int currentRent) {
        this.currentRent = currentRent;
    }

    public void updateRentInUpDownGrade(String action) {
        if(action.equals("UP"))
            setCurrentRent(getCurrentRent()*2);
        else if(action.equals("DOWN"))
            setCurrentRent(getCurrentRent()/2);
    }

    public boolean isHasDepot() {
        return hasDepot;
    }

    public void setHasDepot(boolean hasDepot) {
        this.hasDepot = hasDepot;
    }


    @Override
    public String generateSaveInfo() {
        return super.toString() + ";" +
                hasDepot + "+";
    }
}
