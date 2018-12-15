package domain.server.board;

import domain.server.Savable;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Class for Buyable Squares.
 */
public abstract class DeedSquare extends Square implements Savable {
    private int currentRent;
    protected int buildingCost;
    private int buyValue;
    protected int[] rents;
    protected String owner;
    private boolean mortgaged;
    private int mortgageValue;


    public DeedSquare() {
        this("", 0, 0, 0, null, new int[10]);
    }

    public DeedSquare(String name, int layer, int index, int buyValue, String owner, int[] rents) {
        super(name, layer, index);
        this.buyValue = buyValue;
        this.rents = rents.clone();
        this.owner = owner;
        this.currentRent = rents[0];
        this.mortgageValue = rents[8];
        this.buildingCost = rents[9];
    }

    public int[] getRents() {
        return rents;
    }

    public int getBuyValue() {
        return buyValue;
    }

    public void setRent(int[] rents) {
        this.rents = rents;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public boolean isMortgaged() {
        return mortgaged;
    }

    public void setMortgaged(boolean mortgaged) {
        this.mortgaged = mortgaged;
    }

    public boolean isOwned() {
        return !(owner == null);
    }

    public int getCurrentRent() {
        return currentRent;
    }

    public int getMortgageValue() {
        return mortgageValue;
    }

    public void setBuyValue(int buyValue) {
        this.buyValue = buyValue;
    }

    public void setCurrentRent(int rent) {
        this.currentRent = rent;
    }

    public void setMortgageValue(int mortgageValue) {
        this.mortgageValue = mortgageValue;
    }

    //    public String toJSON() {
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
//        String result = "";
//        try {
//            result = objectMapper.writeValueAsString(this);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }


    @Override
    public String toString() {
        return name;
    }

    @Override
    public String generateSaveInfo() {
        return name + ";" +
                location[0] + ";" +
                location[1] + ";" +
                buyValue + ";" +
                Arrays.stream(rents)
                        .mapToObj(String::valueOf)
                        .collect(Collectors.joining("@")) + ";" +
                owner + ";" +
                mortgaged;
    }

}
