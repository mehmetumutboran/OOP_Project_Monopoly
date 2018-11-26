package domain.server.building;

public abstract class Building {
    private int cost;

    public Building() {
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public abstract String getName();
}
