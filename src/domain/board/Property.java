package domain.board;

import domain.building.Building;

import java.util.ArrayList;

public class Property extends DeedSquare {
    private String color;
    private ArrayList<Building> buildingList;

    public Property(String name, int buyValue, int rent, String color) {
        super(name, buyValue, rent);
        this.color = color;
        this.buildingList = new ArrayList<>(); //TODO Lazy init?
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public ArrayList<Building> getBuildingList() {
        return buildingList;
    }


}
