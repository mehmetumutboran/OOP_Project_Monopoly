package domain.board;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.player.Player;

/**
 * Class for Buyable Squares.
 */
public abstract class DeedSquare extends Square {
    private int buyValue;


    @JsonIgnoreProperties({"ownedProperties", "ownedRailroads", "ownedUtilities"})
    private Player owner;
    private boolean owned;
    private boolean mortgaged;
    private int[] rents ;
    private int currentRent;

    public DeedSquare() {
        this("", 0, 0, 0, null,  new int[]{1,1,1,1,1,1,1,1 });
    }

    public DeedSquare(String name, int layer, int index, int buyValue,  Player owner, int[] rents) {
        super(name, layer, index);
        this.buyValue = buyValue;
        this.owner = owner;
        this.rents=rents.clone();
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

    public int getBuyValue() {
        return buyValue;
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
