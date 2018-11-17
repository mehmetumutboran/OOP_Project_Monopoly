package domain.player;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.board.*;
import domain.die.DiceCup;

import java.util.ArrayList;
import java.util.Objects;

public class Player implements Comparable {
    private String name;
    private Token token;
    private int balance;
    private ArrayList<Property> ownedProperties;
    private ArrayList<Utility> ownedUtilities;
    private ArrayList<Railroad> ownedRailroads;
    private String readiness;
    private boolean started;
    private int doubleCounter; // Constructor
    private boolean inJail;
    private int[] faceValues;

    public Player() {
        this("");
    }

    public Player(String name) {
        this(name, new Token(), 3200, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), "Not Ready");
    }

    public Player(String name, Token token, int balance, ArrayList<Property> ownedProperties, ArrayList<Utility> ownedUtilities,
                  ArrayList<Railroad> ownedRailroads, String readiness) {
        this.name = name;
        this.token = token;
        this.balance = balance;
        this.ownedProperties = ownedProperties;
        this.ownedUtilities = ownedUtilities;
        this.ownedRailroads = ownedRailroads;
        this.readiness = readiness;
        this.faceValues = new int[3];
    }

    /**
     * Maps {@link Player} object to a JSON formatted String
     * Uses {@link com.fasterxml.jackson.annotation.JacksonAnnotation}
     *
     * @return JSON representation of {@link Player}
     */
    public String toJSON() {
        String result = "";
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        try {
            result = objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String getName() {
        return name;
    }

    public String getReadiness() {
        return this.readiness;
    }

    public void setReadiness() {
        if (this.readiness.equals("Ready")) readiness = "Not Ready";
        else readiness = "Ready";
    }

    public void setReadiness(String readiness) {
        this.readiness = readiness;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public Token getToken() {
        return token;
    }

    public String tokenColor(){return this.getToken().getColor();}

    public void setToken(Token token) {
        this.token = token;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public ArrayList<Property> getOwnedProperties() {
        return ownedProperties;
    }

    public void setOwnedProperties(ArrayList<Property> ownedProperties) {
        this.ownedProperties = ownedProperties;
    }

    public ArrayList<Utility> getOwnedUtilities() {
        return ownedUtilities;
    }

    public void setOwnedUtilities(ArrayList<Utility> ownedUtilities) {
        this.ownedUtilities = ownedUtilities;
    }

    public ArrayList<Railroad> getOwnedRailroads() {
        return ownedRailroads;
    }

    public void setOwnedRailroads(ArrayList<Railroad> ownedRailroads) {
        this.ownedRailroads = ownedRailroads;
    }

    public int[] getFaceValues() {
        return faceValues;
    }

    public void setFaceValues(int[] faceValues) {
        for (int i = 0; i < faceValues.length; i++) {
            this.faceValues[i] = faceValues[i];
        }
    }

    public int resetDoubleCounter() {
        return this.doubleCounter = 0;
    }

    public void incrementDoubleCounter() {
        this.doubleCounter += 1;
    }

    public boolean isInJail() {
        return inJail;
    }

    public void setInJail(boolean inJail) {
        this.inJail = inJail;
    }

    public void rollDice() {
//        String locName = Board.getInstance().getSq(this.token.getLocation()).getName();
        String locName = "Go";
        DiceCup.getInstance().rollDice(locName);
        this.faceValues = DiceCup.getInstance().getFaceValues();
    } // Player gives command to roll dice to the controller.


    @Override
    public int compareTo(Object o) { // To order players i use comparable interface and its compareTo method
        if (this.getFaceValues()[0] + this.getFaceValues()[1] + this.getFaceValues()[2] >=
                ((Player) o).getFaceValues()[0] + ((Player) o).getFaceValues()[1] + ((Player) o).getFaceValues()[2]) {
            return -1;
        } else return 1;
    }

    @Override
    public String toString() { //TODO This toString() is for debugging. It may change.
        final StringBuilder sb = new StringBuilder("Player{");
        sb.append("name='").append(name).append('\'');
        sb.append(", token=").append(token);
        sb.append(", balance=").append(balance);
        sb.append(", started=").append(started);
        sb.append('}');
        return sb.toString();
    }

    public boolean checkMajority(Property square) {
        return false;
        //TODO for upgrade downgrade
    }

    public void increaseMoney(int money) {
        this.setBalance(this.getBalance() + money);
    }

    public void decreaseMoney(int money) {
        this.setBalance(this.getBalance() - money);
    }


    public boolean buy() {
        System.out.println("in  player buy");
        boolean sold = false;
        /* checks if buyable square i.e. railroad */
        Square square = Board.getInstance().getSquareList()[this.getToken().getLocation()[0]][this.getToken().getLocation()[1]];


        boolean buyable = (square instanceof Property ||
                square instanceof Railroad ||
                square instanceof Utility);

        System.out.println("buyable checked");
        /*
        1. if which buyable square -> downcast accordingly
        2. if have enough money
        3. if owned ?
        * */


        if (buyable) {
            System.out.println("buyable 2 checked ");

            if (square instanceof Property
                    && this.getBalance() > ((Property) square).getBuyValue()
                    && !((Property) square).isOwned()) {
                return true;
            } else if (square instanceof Railroad
                    && this.getBalance() > ((Railroad) square).getBuyValue()
                    && !((Railroad) square).isOwned()) {
                return true;
            } else if (square instanceof Utility
                    && this.getBalance() > ((Utility) square).getBuyValue()
                    && !((Utility) square).isOwned()) {
                return true;
            }
        }

        return false;
    }


    public boolean payRent() {
        System.out.println("in  player payRent");
        /* checks if buyable square i.e. railroad */
        Square square = Board.getInstance().getSquareList()[this.getToken().getLocation()[0]][this.getToken().getLocation()[1]];


        boolean rentable =
                (square instanceof Property ||
                        square instanceof Railroad ||
                        square instanceof Utility);

        /*
        1. if which buyable square -> downcast accordingly
        2. if have enough money
        3. if owned ?
        * */


        if (rentable) {

            if (square instanceof Property
                    && ((Property) square).isOwned()
                    && !((Property) square).getOwner().equals(this)
                    && this.getBalance() > ((Property) square).getRent()) {
                return true;
            }
            /*others like railroad*/
            else if (square instanceof Railroad
                    && ((Railroad) square).isOwned()
                    && !((Railroad) square).getOwner().equals(this)
                    && this.getBalance() > ((Railroad) square).getRent()) {
                return true;
            } else if (square instanceof Utility
                    && ((Utility) square).isOwned()
                    && !((Utility) square).getOwner().equals(this)
                    && this.getBalance() > ((Utility) square).getRent()) {
                return true;
            }
        }

        return false;
    }


}
