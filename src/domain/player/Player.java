package domain.player;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.board.Board;
import domain.board.Property;
import domain.board.Railroad;
import domain.board.Utility;
import domain.controller.MonopolyGameController;
import domain.die.DiceCup;

import java.security.PrivilegedExceptionAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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

    /**
     * Main method to test {@link #toJSON()}
     *
     * @param args cmdline args
     */
    public static void main(String[] args) {
        Player player = new Player("Player32");
        System.out.println(player.toJSON());
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
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

    public void setReadiness(String readiness){ this.readiness = readiness; }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public Token getToken() {
        return token;
    }

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
        this.doubleCounter+=1;
    }

    public boolean isInJail() {
        return inJail;
    }

    public void setInJail(boolean inJail) {
        this.inJail = inJail;
    }

    public void rollDice(){
//        String locName = Board.getInstance().getSq(this.token.getLocation()).getName();
        String locName = "Go";
        DiceCup.getInstance().rollDice(locName);
        this.faceValues = DiceCup.getInstance().getFaceValues();
    } // Player gives command to roll dice to the controller.


    @Override
    public int compareTo(Object o) { // To order players i use comparable interface and its compareTo method
        if(this.getFaceValues()[0] + this.getFaceValues()[1] + this.getFaceValues()[2] >=
                ((Player) o).getFaceValues()[0] + ((Player) o).getFaceValues()[1] + ((Player) o).getFaceValues()[2]){
            return -1;
        }else return 1;
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


    public boolean buy(){
        System.out.println("in  player buy");
        boolean sold = false;
        /* checks if buyable square i.e. railroad */


        boolean buyable =
                (Board.getInstance().getSquareList()[this.getToken().getLocation()[0]][this.getToken().getLocation()[1]] instanceof Property ||
                        Board.getInstance().getSquareList()[this.getToken().getLocation()[0]][this.getToken().getLocation()[1]] instanceof Railroad ||
                        Board.getInstance().getSquareList()[this.getToken().getLocation()[0]][this.getToken().getLocation()[1]] instanceof Utility);

        System.out.println("buyable checked");
        /*
        1. if which buyable square -> downcast accordingly
        2. if have enough money
        3. if owned ?
        * */


        if(buyable ) {
            System.out.println("buyable 2 checked ");

            if (Board.getInstance().getSquareList()[this.getToken().getLocation()[0]][this.getToken().getLocation()[1]] instanceof Property) {
                System.out.println("property checked ");

                if (this.getBalance() > ((Property) Board.getInstance().getSquareList()[this.getToken().getLocation()[0]][this.getToken().getLocation()[1]]).getBuyValue()) {
                    System.out.println("balance checked ");

                    if (!((Property) Board.getInstance().getSquareList()[this.getToken().getLocation()[0]][this.getToken().getLocation()[1]]).isOwned()) {
                        System.out.println("property sold");

                        /*
                        this.ownedProperties.add((Property) Board.getInstance().getSquareList()[this.getToken().getLocation()[0]][this.getToken().getLocation()[1]]);
                        int price = ((Property) Board.getInstance().getSquareList()[this.getToken().getLocation()[0]][this.getToken().getLocation()[1]]).getBuyValue();
                        this.setBalance(this.getBalance() - price);
                        System.out.println("owned changed");
                        */
                        sold = true;
                    }
                }
            }/*
            else if (Board.getInstance().getSquareList()[this.getToken().getLocation()[0]][this.getToken().getLocation()[1]] instanceof Railroad) {
                if (this.getBalance() > ((Railroad) Board.getInstance().getSquareList()[this.getToken().getLocation()[0]][this.getToken().getLocation()[1]]).getBuyValue()) {
                    if (!((Railroad) Board.getInstance().getSquareList()[this.getToken().getLocation()[0]][this.getToken().getLocation()[1]]).isOwned()) {
                        this.ownedRailroads.add((Railroad) Board.getInstance().getSquareList()[this.getToken().getLocation()[0]][this.getToken().getLocation()[1]]);
                        sold = true;
                    }
                }
            }
            else if (Board.getInstance().getSquareList()[this.getToken().getLocation()[0]][this.getToken().getLocation()[1]] instanceof Utility) {
                if (this.getBalance() > ((Railroad) Board.getInstance().getSquareList()[this.getToken().getLocation()[0]][this.getToken().getLocation()[1]]).getBuyValue()) {
                    if (!((Utility) Board.getInstance().getSquareList()[this.getToken().getLocation()[0]][this.getToken().getLocation()[1]]).isOwned()) {
                        this.ownedUtilities.add((Utility) Board.getInstance().getSquareList()[this.getToken().getLocation()[0]][this.getToken().getLocation()[1]]);
                        sold = true;
                    }
                }
            }
*/
        }

        return sold;
    }






}
