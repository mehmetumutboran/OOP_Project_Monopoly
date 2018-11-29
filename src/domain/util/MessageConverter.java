package domain.util;

import domain.server.Savable;
import domain.server.board.DeedSquare;
import domain.server.board.Property;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.Collector;
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

    public static int[] convertStringToIntArray(String str) {
        ArrayList<Integer> lst = integerArrayListConverter(str);
        int[] intArray = new int[lst.size()];
        for (int i = 0; i < lst.size(); i++) {
            intArray[i] = lst.get(i);
        }
        return intArray;
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

    private static ArrayList<Integer> integerArrayListConverter(String str) {
        ArrayList<Integer> lst = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ',') { // when ',' found
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
        return list.stream().map(Savable::generateSaveInfo).collect(Collectors.joining());
    }
}
