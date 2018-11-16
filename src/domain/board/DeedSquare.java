package domain.board;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.player.Player;

/**
 * Class for Buyable Squares.
 */
public abstract class DeedSquare extends Square {
    private int buyValue;
    private int rent;
    private Player owner;
    private boolean owned;
    private boolean mortgaged;


    public DeedSquare() {
        this("" , 0 , 0, 0, 0,null);
    }

    public DeedSquare(String name, int layer, int index, int buyValue, int rent , Player owner) {
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

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
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

    public String toJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        String result = "";
        try {
            result = objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

}
