package domain.server.board;

public class Railroad extends DeedSquare implements Upgradable {
    private boolean hasDepot;
    private int[] rents;
    private int currentRent;

    public Railroad() {
        this("", 0, 0, 0, new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
    }

    public Railroad(String name, int layer, int index, int buyValue, int[] rents) {
        super(name, layer, index, buyValue, null, rents);
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

//    public void updateRentInUpDownGrade(String action) {
//        if(action.equals("UP"))
//            setCurrentRent(getCurrentRent()*2);
//        else if(action.equals("DOWN"))
//            setCurrentRent(getCurrentRent()/2);
//    }

    @Override
    public boolean isUpgradable() {
        return !hasDepot;
    }

    @Override
    public boolean isDowngradable() {
        return hasDepot;
    }

    @Override
    public void updateRent() {
        if (hasDepot) setCurrentRent(this.currentRent * 2);
        else setCurrentRent(this.currentRent / 2);
    }

    @Override
    public boolean isUpgraded() {
        return hasDepot;
    }

    @Override
    public int getBuildingCount() {
        return hasDepot ? 1 : 0;
    }

    @Override
    public int getUpgradeLevel() {
        if (hasDepot) return 7;
        else return 0;

    }

    @Override
    public void upgrade() {
        hasDepot = true;
        updateRent();

    }

    @Override
    public void downgrade() {
        hasDepot = false;
        updateRent();
    }

    @Override
    public int getBuildingCost() {
        return 0;
    }

    @Override
    public boolean hasUpgradedSquareInGroup() {
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
