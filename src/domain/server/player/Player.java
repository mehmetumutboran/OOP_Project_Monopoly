package domain.server.player;

import domain.server.Savable;
import domain.server.board.*;
import domain.util.MessageConverter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Player implements Comparable, Savable {
    private String name;
    private Token token;
    private int balance;
    private ArrayList<Property> ownedProperties;
    private ArrayList<Utility> ownedUtilities;
    private ArrayList<Railroad> ownedRailroads;
    //    private ArrayList<DeedSquare> mortgagedSquares;
    private String readiness;
    private int doubleCounter; // Constructor (double die)
    private boolean inJail;
    private boolean secondMove; // Mr monopoly
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
        this(name, token, balance, ownedProperties, ownedUtilities, ownedRailroads, readiness, 0, false);
    }

    public Player(String name, Token token, int balance,
                  ArrayList<Property> propertyList, ArrayList<Utility> utilityList,
                  ArrayList<Railroad> railroadList,
                  String readiness, int doubleCounter, boolean isInJail) {

        this.name = name;
        this.token = token;
        this.balance = balance;
        this.ownedProperties = propertyList;
        this.ownedUtilities = utilityList;
        this.ownedRailroads = railroadList;
//        this.mortgagedSquares = mortgagedSquares;
        this.readiness = readiness;
        this.faceValues = new int[3];
        this.doubleCounter = doubleCounter;
        this.inJail = isInJail;
        this.secondMove = false;

        //TODO test purposes

       /* if (this.name.contains("MBot Ross")) {
            this.ownedProperties.add((Property) Board.getInstance().getNameGivenSquare("Esplanade Avenue"));
            this.ownedProperties.add((Property) Board.getInstance().getNameGivenSquare("Canal Street"));
            this.ownedProperties.add((Property) Board.getInstance().getNameGivenSquare("Magazine Street"));
            this.ownedProperties.add((Property) Board.getInstance().getNameGivenSquare("Bourbon Street"));
            ((Property) Board.getInstance().getNameGivenSquare("Magazine Street")).upgrade();
            ((Property) Board.getInstance().getNameGivenSquare("Canal Street")).upgrade();

            //this.ownedProperties.add((Property) Board.getInstance().getNameGivenSquare("Bourbon Street"));

            ((Property) Board.getInstance().getNameGivenSquare("Esplanade Avenue")).setOwner(name);
            ((Property) Board.getInstance().getNameGivenSquare("Canal Street")).setOwner(name);
            ((Property) Board.getInstance().getNameGivenSquare("Magazine Street")).setOwner(name);
            ((Property) Board.getInstance().getNameGivenSquare("Bourbon Street")).setOwner(name);

//            this.getOwnedProperties().get(0).getBuildingList().add(new Hotel(((Property) Board.getInstance().getNameGivenSquare("Esplanade Avenue")).getHouseBuildingCost()));
//            this.getOwnedProperties().get(0).setUpgraded(true);
//
//            this.getOwnedProperties().get(1).getBuildingList().add(new Hotel(((Property) Board.getInstance().getNameGivenSquare("Esplanade Avenue")).getHouseBuildingCost()));
//            this.getOwnedProperties().get(1).setUpgraded(true);
//            this.getOwnedProperties().get(2).getBuildingList().add(new Hotel(((Property) Board.getInstance().getNameGivenSquare("Esplanade Avenue")).getHouseBuildingCost()));
//            this.getOwnedProperties().get(2).setUpgraded(true);
            // ((Property) Board.getInstance().getNameGivenSquare("Bourbon Street")).setOwner(name);
        }*/
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
        System.arraycopy(faceValues, 0, this.faceValues, 0, faceValues.length);
    }

    public ArrayList<DeedSquare> getMortgagedSquares() {
        ArrayList<DeedSquare> mortgaged = new ArrayList<>();
        ownedProperties.stream().filter(DeedSquare::isMortgaged).forEach(mortgaged::add);
        ownedUtilities.stream().filter(DeedSquare::isMortgaged).forEach(mortgaged::add);
        ownedRailroads.stream().filter(DeedSquare::isMortgaged).forEach(mortgaged::add);
        return mortgaged;
    }

//    public void addMortgagedSquare(DeedSquare square) {
//        if (square instanceof Property) {
//            this.ownedProperties.removeIf(x -> x.equals(square));
//        } else if (square instanceof Utility) {
//            this.ownedUtilities.removeIf(x -> x.equals(square));
//        } else {
//            this.ownedRailroads.removeIf(x -> x.equals(square));
//        }
//        this.mortgagedSquares.add(square);
//    }

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

    /**
     * This method compares two player object corresponding to their face values.
     *
     * @param o The other object that we compare with this.
     * @return 1 if o's total face value is bigger than this total face value. Else returns -1.
     */
    @Override
    public int compareTo(Object o) {
        // @requires: o!=null
        // @effects: returns an integer that shows which object is bigger.
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
                readiness + "," + //TODO Started
                doubleCounter + "," +
                inJail + "*\n";
    }


    public boolean checkMajority(Property square) {
        int numOfColoredSqBoardHas = Board.getInstance().getSameColoredSquares(square.getColor()).length;
        int numOfColoredSqPlayerHas = getNumOfColoredSqPlayerHas(square);

        if (numOfColoredSqBoardHas > 2) {
            return numOfColoredSqBoardHas - numOfColoredSqPlayerHas == 0 || numOfColoredSqBoardHas - numOfColoredSqPlayerHas == 1;
        } else
            return numOfColoredSqBoardHas - numOfColoredSqPlayerHas == 0;
    }

    public boolean checkMonopoly(Property square) {
        int numOfColoredSqBoardHas = Board.getInstance().getSameColoredSquares(square.getColor()).length;
        int numOfColoredSqPlayerHas = getNumOfColoredSqPlayerHas(square);

        return numOfColoredSqBoardHas - numOfColoredSqPlayerHas == 0;
    }

    private int getNumOfColoredSqPlayerHas(Property square) {
        int numOfColoredSqPlayerHas = 0;
        for (Property sq : getOwnedProperties()) {
            if (sq.getColor().equals(square.getColor()))
                numOfColoredSqPlayerHas++;
        }
        return numOfColoredSqPlayerHas;
    }

    public void increaseMoney(int money) {
        this.setBalance(this.getBalance() + money);
    }

    public void decreaseMoney(int money) {
        this.setBalance(this.getBalance() - money);
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

    public boolean repOK() {
        if (this.balance < 0) return false;
        if (this.ownedProperties.size() > 64) return false;
        if (this.ownedUtilities.size() > 12) return false;
        if (this.ownedRailroads.size() > 8) return false;
        if (this.getMortgagedSquares().size() > 84) return false;
        if (hasDuplicate(ownedRailroads) || hasDuplicate(ownedProperties)
                || hasDuplicate(ownedUtilities) || hasDuplicate(this.getMortgagedSquares())) return false;
        if (!this.readiness.equals("Host") && !this.readiness.equals("Not Ready") &&
                !this.readiness.equals("Ready") && !this.readiness.equals("Bot"))
            return false;
        if (doubleCounter < 0 || doubleCounter > 2) return false;
        if (faceValues == null || faceValues.length > 3) return false;

        return true;
    }

    public static <T> boolean hasDuplicate(Iterable<T> all) {
        Set<T> set = new HashSet<>();
        // Set#add returns false if the set does not change, which
        // indicates that a duplicate element has been added.
        for (T each : all) if (!set.add(each)) return true;
        return false;
    }

    public boolean isSecondMove() {
        return secondMove;
    }

    public void setSecondMove(boolean secondMove) {
        this.secondMove = secondMove;
    }


}
