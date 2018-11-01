package domain.player;

import domain.board.Property;
import domain.board.Railroad;
import domain.board.Utility;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    private String name;
    private Token token;
    private int balance;
    private ArrayList<Property> ownedProperties;
    private ArrayList<Utility> ownedUtilities;
    private ArrayList<Railroad> ownedRailroads;
    private HashMap<String, Integer> colorMap; // TODO ?

    //TODO how to take color of token ?

}
