package domain.player;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.board.Property;
import domain.board.Railroad;
import domain.board.Utility;

import java.security.PrivilegedExceptionAction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Player {
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


}
