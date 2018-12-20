package domain.util;

import domain.server.Savable;
import domain.server.board.Board;
import domain.server.board.Property;
import domain.server.board.Railroad;
import domain.server.board.Utility;
import domain.server.building.Building;
import domain.server.building.Hotel;
import domain.server.building.House;
import domain.server.building.Skyscraper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class MessageConverter {
    private static MessageConverter mr;

    private MessageConverter() {
        mr = new MessageConverter();
    }

    public static MessageConverter getInstance() {
        if (mr != null) {
            mr = new MessageConverter();
        }
        return mr;
    }

    public static <T> String convertArrayToString(T[] array) {
        return Arrays.toString(array);
    }

    public static String convertArrayToString(int[] array) {
        return Arrays.toString(array);
    }

    public static String convertQueueToString(Deque<String> deque) {
        return deque.toString();
    }

    public static int[] convertStringToIntArray(String str, char delimiter) {
        ArrayList<Integer> lst = integerArrayListConverter(str, delimiter);
        int[] intArray = new int[lst.size()];
        for (int i = 0; i < lst.size(); i++) {
            intArray[i] = lst.get(i);
        }
        return intArray;
    }

    public static String[] convertStringToStringArray(String str) {
        return str.substring(1, str.length()-1).split(",\\s");
    }

    public static Deque<String> convertStringToDeque(String str) {
        assert str.contains(",");
        Deque<String> myDeque = new LinkedList<>();
        int j = 0; // we also init j to keep the last position of ' '
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ',') { // when ',' found
                if (j == 0) myDeque.addLast(str.substring(1, i));
                else myDeque.addLast(str.substring(j + 1, i));
                j = i + 1;
            }
        }
        myDeque.addLast(str.substring(j + 1, str.indexOf(']'))); // adding the last one manually
        return myDeque;
    }

    private static ArrayList<Integer> integerArrayListConverter(String str, char delimiter) {
        ArrayList<Integer> lst = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == delimiter) { // when ',' found
                char chr1 = str.charAt(i - 2);
                char chr2 = str.charAt(i - 1);
                int a;
                if (isNumeric(chr1)) // if 2 index prev chr is numeric
                    a = (10 * (chr1 - 48)) + (chr2 - 48); // ab = 10*a + b
                else
                    a = (chr2 - 48); // a = 1*a
                lst.add(a);
            }
        }
        char chr1 = str.charAt(str.length() - 3); // for the last one since we can't
        char chr2 = str.charAt(str.length() - 2); // detect it with ',' we add it manually
        int a;
        if (isNumeric(chr1)) a = (10 * (chr1 - 48)) + (chr2 - 48);
        else a = chr2 - 48;
        lst.add(a);
        return lst;
    }

    private static boolean isNumeric(char chr) {
        return chr <= 57 && chr >= 48;
    }

    public static String convertListToString(ArrayList<? extends Savable> list) {
        if (list.isEmpty()) return "null";
        return list.stream().map(Savable::generateSaveInfo).collect(Collectors.joining());
    }

    public static ArrayList<? extends Savable> convertStringToList(String message, int index) {
        if (message.equals("null")) return new ArrayList<>(); // TODO check cast
        String[] arr = message.split("[+]");

        if (index == 5) {
            return convertStringToPropertyList(arr);
        } else if (index == 6) {
            return convertStringToUtilityList(arr);
        } else if (index == 7) {
            return convertStringToRailroadList(arr);
        } else if (index == 8) {
            return convertStringToMortgagedSquares(arr);
        }
        return null;
    }

    private static ArrayList<? extends Savable> convertStringToMortgagedSquares(String[] arr) {
        //TODO
        return null;
//        ArrayList<DeedSquare> mortgagedSquares = new ArrayList<>();
//        String[] squareInfo;
//        String name, owner, color;
//        int layer, location, cost;
//        int[] rent;
//        boolean mortgaged, hasUpgrade;
//        ArrayList<Building> buildings;
//        for (String s : arr) {
//            squareInfo = s.split("[;]");
//            name = squareInfo[0];
//            layer = Integer.parseInt(squareInfo[1]);
//            location = Integer.parseInt(squareInfo[2]);
//            cost = Integer.parseInt(squareInfo[3]);
//            rent = convertStringToIntArray(squareInfo[4], '@');
//            owner = squareInfo[5];
//            mortgaged = Boolean.parseBoolean(squareInfo[6]);
//            color = squareInfo[7];
//            buildings = convertStringToBuildingList(squareInfo[8].split("[:]"));
//            hasUpgrade = Boolean.parseBoolean(squareInfo[9]);
//            Property square = (Property) Board.getInstance().getSquare(layer, location);
//            square.setBuildingList(buildings);
//            square.setRent(rent);
//            square.setOwner(owner);
//            square.setMortgaged(mortgaged);
//            square.setUpgraded(hasUpgrade);
//            mortgagedSquares.add(square);
//        }
//
//        return mortgagedSquares;
    }

    private static ArrayList<Property> convertStringToPropertyList(String[] arr) {
        ArrayList<Property> properties = new ArrayList<>();
        String[] squareInfo;
        String name, owner, color;
        int layer, location, cost;
        int[] rent;
        boolean mortgaged, hasUpgrade;
        ArrayList<Building> buildings;
        for (String s : arr) {
            squareInfo = s.split("[;]");
            name = squareInfo[0];
            layer = Integer.parseInt(squareInfo[1]);
            location = Integer.parseInt(squareInfo[2]);
            cost = Integer.parseInt(squareInfo[3]);
            rent = convertStringToIntArray(squareInfo[4], '@');
            owner = squareInfo[5];
            mortgaged = Boolean.parseBoolean(squareInfo[6]);
            color = squareInfo[7];
            buildings = convertStringToBuildingList(squareInfo[8].split("[:]"));
            hasUpgrade = Boolean.parseBoolean(squareInfo[9]);
            Property square = (Property) Board.getInstance().getSquare(layer, location);
            square.setBuildingList(buildings);
            square.setRent(rent);
            square.setOwner(owner);
            square.setMortgaged(mortgaged);
            square.setUpgraded(hasUpgrade);
            properties.add(square);
        }

        return properties;
    }

    private static ArrayList<Building> convertStringToBuildingList(String[] buildingInfo) {
        ArrayList<Building> buildings = new ArrayList<>();
        String[] buildingArr;
        String name;
        int cost;
        for (String s : buildingInfo) {
            buildingArr = s.split("[@]");
            name = buildingArr[0];
            cost = Integer.parseInt(buildingArr[1]);
//              Building building = (Building) Class.forName("domain.server.building." + name).getDeclaredConstructor().newInstance();
            Building building;
            if (name.equals("House")) building = new House(cost);
            else if (name.equals("Hotel")) building = new Hotel(cost);
            else building = new Skyscraper(cost);

            building.setCost(cost);
            buildings.add(building);
        }
        return buildings;
    }

    private static ArrayList<Utility> convertStringToUtilityList(String[] arr) {
        ArrayList<Utility> utilities = new ArrayList<>();
        String[] squareInfo;
        String name, owner;
        int layer, location, cost;
        int[] rent;
        boolean mortgaged;
        for (String s : arr) {
            squareInfo = s.split("[;]");
            name = squareInfo[0];
            layer = Integer.parseInt(squareInfo[1]);
            location = Integer.parseInt(squareInfo[2]);
            cost = Integer.parseInt(squareInfo[3]);
            rent = convertStringToIntArray(squareInfo[4], '@');
            owner = squareInfo[5];
            mortgaged = Boolean.parseBoolean(squareInfo[6]);
            Utility square = (Utility) Board.getInstance().getSquare(layer, location);
            square.setRent(rent);
            square.setOwner(owner);
            square.setMortgaged(mortgaged);
            utilities.add(square);
        }
        return utilities;
    }

    private static ArrayList<Railroad> convertStringToRailroadList(String[] arr) {
        ArrayList<Railroad> railroads = new ArrayList<>();
        String[] squareInfo;
        String name, owner;
        int layer, location, cost;
        int[] rent;
        boolean mortgaged, hasDepot;
        for (String s : arr) {
            squareInfo = s.split("[;]");
            name = squareInfo[0];
            layer = Integer.parseInt(squareInfo[1]);
            location = Integer.parseInt(squareInfo[2]);
            cost = Integer.parseInt(squareInfo[3]);
            rent = convertStringToIntArray(squareInfo[4], '@');
            owner = squareInfo[5];
            mortgaged = Boolean.parseBoolean(squareInfo[6]);
            hasDepot = Boolean.parseBoolean(squareInfo[7]);
            Railroad square = (Railroad) Board.getInstance().getSquare(layer, location);
            square.setRent(rent);
            square.setOwner(owner);
            square.setMortgaged(mortgaged);
            railroads.add(square);
        }
        return railroads;
    }
}
