package gui.util;

import java.awt.*;
import java.util.HashMap;

public class ColorConverter {
    private static ColorConverter ourInstance = new ColorConverter();
    private HashMap<String, Color> colorMap;
    private HashMap<Color, String> reverseColorMap;


    public static ColorConverter getInstance() {
        return ourInstance;
    }

    private ColorConverter() {
        colorMap = new HashMap<>();
        initColorMap();
        initReverseColorMap();
    }

    private void initColorMap() {
        colorMap = new HashMap<>();
        colorMap.put("White", Color.white);
        colorMap.put("LightGray", Color.lightGray);
        colorMap.put("Gray", Color.gray);
        colorMap.put("Blue", Color.blue);
        colorMap.put("Cyan", Color.cyan);
        colorMap.put("Pink", Color.pink);
        colorMap.put("Green", Color.green);
        colorMap.put("Orange", Color.orange);
        colorMap.put("Magenta", Color.magenta);
        colorMap.put("Yellow", Color.yellow);
        colorMap.put("Red", Color.red);
        colorMap.put("Turquoise", new Color(38, 209, 188));
    }

    private void initReverseColorMap() {
        reverseColorMap = new HashMap<>();
        reverseColorMap.put(Color.white, "White");
        reverseColorMap.put(Color.lightGray, "LightGray");
        reverseColorMap.put(Color.gray, "Gray");
        reverseColorMap.put(Color.blue, "Blue");
        reverseColorMap.put(Color.cyan, "Cyan");
        reverseColorMap.put(Color.pink, "Pink");
        reverseColorMap.put(Color.green, "Green");
        reverseColorMap.put(Color.orange, "Orange");
        reverseColorMap.put(Color.magenta, "Magenta");
        reverseColorMap.put(Color.yellow, "Yellow");
        reverseColorMap.put(Color.red, "Red");
        reverseColorMap.put(new Color(38, 209, 188), "Turquoise");
    }

    public Color getColor(String color) {
        return colorMap.get(color);
    }

    public String getReverseColor(Color color) {
        return reverseColorMap.get(color);
    }

    public HashMap<String, Color> getColorMap() {
        return colorMap;
    }


}
