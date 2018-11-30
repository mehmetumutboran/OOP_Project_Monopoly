package domain.server.board;

import domain.server.Savable;

/**
 * Class for Buyable Squares.
 */
public abstract class DeedSquare extends Square implements Savable {
    private int buyValue;
    private int rent;
    private String owner;
    private boolean mortgaged;


    public DeedSquare() {
        this("", 0, 0, 0, 0, null);
    }

    public DeedSquare(String name, int layer, int index, int buyValue, int rent, String owner) {
        super(name, layer, index);
        this.buyValue = buyValue;
        this.rent = rent;
        this.owner = owner;
    }

    public int getRent() {
        return rent;
    }

    public int getBuyValue() {
        return buyValue;
    }

    public void setRent(int rent) {
        this.rent = rent;
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
                rent + ";" +
                owner + ";" +
                mortgaged;
    }

}
