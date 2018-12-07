package domain.server.board;

public class Utility extends DeedSquare {
    private int[] rents = new int[10];
    private int currentRent;

    public Utility() {
        this("", 0, 0, 0,  new int[]{0,0,0,0,0,0,0,0,0,0 });

    }

    public Utility(String name, int layer, int index, int buyValue, int [] rents) {
        super(name, layer, index, buyValue,  null,rents);
        this.rents = rents.clone();
        this.currentRent = rents[0];
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

    @Override
    public String generateSaveInfo() {
        return super.toString() + "+";
    }
}
