package domain.board;

import domain.building.Building;

import java.util.ArrayList;

public class Property extends DeedSquare {
    private String color;
    private ArrayList<Building> buildingList;
    private boolean hasUpgrade;

    public Property() {
        this("", 0, 0, 0, 0, "");
    }

    public Property(String name, int layer, int index, int buyValue, int rent, String color) {
        super(name, layer, index, buyValue, rent, null);
        this.color = color;
        this.buildingList = new ArrayList<>();
        hasUpgrade = false;
    }


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setUpgraded(boolean hasUpgrade) {
        this.hasUpgrade = hasUpgrade;
    }

    public boolean isUpgraded() {
        return hasUpgrade;
    }

    public ArrayList<Building> getBuildingList() {
        return buildingList;
    }

    public void setBuildingList(ArrayList<Building> buildingList) {
        this.buildingList = buildingList;
    }

    public int getHouseCost() {
        return 0;
    }

    public int getHotelCost() {
        return 0;
    }

    public int getSkyScrapperCost() {
        return 0;
    }

    public void updateRent() {
        if (this.buildingList.isEmpty()) {

        } else if (this.buildingList.get(0).getName().equals("Skyscrapper")) {

        } else if (this.buildingList.get(0).getName().equals("Hotel")) {

        } else {
            if (this.buildingList.size() == 4) {

            } else if (this.buildingList.size() == 3) {

            } else if (this.buildingList.size() == 2) {

            } else {

            }
        }
    }

    /**
     * Checks if all squares in Square's color group are at the same level.
     *
     * @param square
     * @return
     */
    public boolean isUpgradable(Property square) {
        //TODO for upgrade downgrade
        return false;
    }
}
