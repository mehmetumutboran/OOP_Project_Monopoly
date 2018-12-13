package domain.server.player;

import domain.server.Savable;
import domain.server.board.*;
import domain.server.building.Hotel;
import domain.server.building.House;
import domain.server.die.DiceCup;
import domain.util.MessageConverter;

import java.util.ArrayList;
import java.util.Objects;

public class Player implements Comparable, Savable {
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

    public Player(String name, String color, String readiness) {
        this(name, new Token(), 3200, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), readiness);
        this.token.setColor(color);
    }

    public Player(String name, Token token, int balance, ArrayList<Property> ownedProperties, ArrayList<Utility> ownedUtilities,
                  ArrayList<Railroad> ownedRailroads, String readiness) {
        this(name, token, balance, ownedProperties, ownedUtilities, ownedRailroads, readiness, false, 0, false);
    }

    public Player(String name, Token token, int balance,
                  ArrayList<Property> propertyList, ArrayList<Utility> utilityList, ArrayList<Railroad> railroadList,
                  String readiness, boolean isStarted, int doubleCounter, boolean isInJail) {

        this.name = name;
        this.token = token;
        this.balance = balance;
        this.ownedProperties = propertyList;
        this.ownedUtilities = utilityList;
        this.ownedRailroads = railroadList;
        this.readiness = readiness;
        this.faceValues = new int[3];
        this.started = isStarted;
        this.doubleCounter = doubleCounter;
        this.inJail = isInJail;

        if(this.name.equals("Brian")) {
            this.ownedProperties.add((Property) Board.getInstance().getNameGivenSquare("Esplanade Avenue"));
            this.ownedProperties.add((Property) Board.getInstance().getNameGivenSquare("Canal Street"));
            this.ownedProperties.add((Property) Board.getInstance().getNameGivenSquare("Magazine Street"));


            this.ownedProperties.add((Property) Board.getInstance().getNameGivenSquare("Bourbon Street"));

            ((Property) Board.getInstance().getNameGivenSquare("Esplanade Avenue")).setOwner(name);
            ((Property) Board.getInstance().getNameGivenSquare("Canal Street")).setOwner(name);
            ((Property) Board.getInstance().getNameGivenSquare("Magazine Street")).setOwner(name);

//            this.getOwnedProperties().get(0).getBuildingList().add(new Hotel(((Property) Board.getInstance().getNameGivenSquare("Esplanade Avenue")).getHouseBuildingCost()));
//            this.getOwnedProperties().get(0).setUpgraded(true);
//
//            this.getOwnedProperties().get(1).getBuildingList().add(new Hotel(((Property) Board.getInstance().getNameGivenSquare("Esplanade Avenue")).getHouseBuildingCost()));
//            this.getOwnedProperties().get(1).setUpgraded(true);
//            this.getOwnedProperties().get(2).getBuildingList().add(new Hotel(((Property) Board.getInstance().getNameGivenSquare("Esplanade Avenue")).getHouseBuildingCost()));
//            this.getOwnedProperties().get(2).setUpgraded(true);
             ((Property) Board.getInstance().getNameGivenSquare("Bourbon Street")).setOwner(name);
        }
    }


    /**
     * Maps {@link Player} object to a JSON formatted String
     * Uses {@link com.fasterxml.jackson.annotation.JacksonAnnotation}
     *
     * @return JSON representation of {@link Player}
     */
//    public String toJSON() {
//        String result = "";
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
//        try {
//            result = objectMapper.writeValueAsString(this);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
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

    public String tokenColor() {
        return this.getToken().getColor();
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

    public int getDoubleCounter() {
        return doubleCounter;
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

//    public void rollDice() {
//        String locName = Board.getInstance().getSquare(this.token.getLocation()[0], this.token.getLocation()[1]).getName();
//        // String locName = "Go";
//        DiceCup.getInstance().rollDice(locName);
//        // this.faceValues = DiceCup.getInstance().getFaceValues();
//    } // Player gives command to roll dice to the controller.


    @Override
    public int compareTo(Object o) { // To order players i use comparable interface and its compareTo method
        if (this.getFaceValues()[0] + this.getFaceValues()[1] + this.getFaceValues()[2] >=
                ((Player) o).getFaceValues()[0] + ((Player) o).getFaceValues()[1] + ((Player) o).getFaceValues()[2]) {
            return -1;
        } else return 1;
    }

    @Override
    public String toString() {
        return name + "," + tokenColor() + "," + readiness;
    }

    @Override
    public String generateSaveInfo() {
        return name + "," +
                token.generateSaveInfo() + "," +
                balance + "," +
                MessageConverter.convertListToString(ownedProperties) + "," +
                MessageConverter.convertListToString(ownedUtilities) + "," +
                MessageConverter.convertListToString(ownedRailroads) + "," +
                readiness + "," +
                started + "," +
                doubleCounter + "," +
                inJail + "*\n";
    }


    public boolean checkMajority(Property square) {
        int numOfColoredSqBoardHas = Board.getInstance().getSameColoredSquares(square.getColor()).length;
        int numOfColoredSqPlayerHas = 0;
        for (Property sq : getOwnedProperties()){
            if(sq.getColor().equals(square.getColor()))
                numOfColoredSqPlayerHas++;
        }
        if(numOfColoredSqBoardHas > 2 ){
            if (numOfColoredSqBoardHas-numOfColoredSqPlayerHas == 0 || numOfColoredSqBoardHas-numOfColoredSqPlayerHas == 1)
                return true;
            else
                return false;
        }else
            if (numOfColoredSqBoardHas-numOfColoredSqPlayerHas == 0)
                return true;
            else
                return false;
    }
    public boolean checkMonopoly (Property square) {
        int numOfColoredSqBoardHas = Board.getInstance().getSameColoredSquares(square.getColor()).length;
        int numOfColoredSqPlayerHas = 0;
        for (Property sq : getOwnedProperties()){
            if(sq.getColor().equals(square.getColor()))
                numOfColoredSqPlayerHas++;
        }

        if(numOfColoredSqBoardHas-numOfColoredSqPlayerHas==0)
            return true;
        else
            return false;
    }

    public void increaseMoney(int money) {
        this.setBalance(this.getBalance() + money);
    }

    public void decreaseMoney(int money) {
        this.setBalance(this.getBalance() - money);
    }


    public boolean buy() {
        System.out.println("in  player buy");
        /* checks if buyable square i.e. railroad */
        Square square = Board.getInstance().getSquareList()[this.getToken().getLocation()[0]][this.getToken().getLocation()[1]];


        boolean buyable = (square instanceof DeedSquare);

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
            } else return square instanceof Utility
                    && this.getBalance() > ((Utility) square).getBuyValue()
                    && !((Utility) square).isOwned();
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
                    && this.getBalance() > ((Property) square).getCurrentRent()) {
                return true;
            }
            /*others like railroad*/
            else if (square instanceof Railroad
                    && ((Railroad) square).isOwned()
                    && !((Railroad) square).getOwner().equals(this)
                    && this.getBalance() > ((Railroad) square).getCurrentRent()) {
                return true;
            } else return square instanceof Utility
                    && ((Utility) square).isOwned()
                    && !((Utility) square).getOwner().equals(this)
                    && this.getBalance() > ((Utility) square).getCurrentRent();
        }

        return false;
    }

    public void addDeed(Square square) {
        if (square instanceof Property) {
            ownedProperties.add((Property) square);
        } else if (square instanceof Railroad) {
            ownedRailroads.add((Railroad) square);
        } else if (square instanceof Utility) {
            ownedUtilities.add((Utility) square);
        }
    }
}
