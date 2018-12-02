package domain.board;

public class Utility extends DeedSquare {
    private int[] rents = new int[8];
    private int currentRent;

    public Utility() {
        this("", 0, 0, 0,  new int[]{1,1,1,1,1,1,1,1 });

    }

    public Utility(String name, int layer, int index, int buyValue, int [] rents) {
        super(name, layer, index, buyValue,  null,rents);
      //  this.rents = rents.clone();
      //  this.currentRent = rents[0];
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
}
