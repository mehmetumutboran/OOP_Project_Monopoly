package domain.board;

import domain.building.Building;

import java.util.ArrayList;

public class Property extends DeedSquare {
    private String color;
    private ArrayList<Building> buildingList;

    public Property(){
        this("", 0, 0, 0, 0, "");
    }

    public Property(String name, int layer, int index, int buyValue, int rent, String color) {
        super(name, layer, index, buyValue, rent,null);
        this.color = color;
        this.buildingList = new ArrayList<>();

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
