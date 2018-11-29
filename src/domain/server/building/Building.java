package domain.server.building;

import domain.server.Savable;

public abstract class Building implements Savable {
    protected int cost;
    protected String name;

    public Building(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getName(){
        return name;
    }

    @Override
    public String generateSaveInfo() {
        return name + "@" +
                cost + ":";
    }
}
