package gui;

import java.awt.*;
import java.util.HashMap;

public class ColorConverter {
    private static ColorConverter ourInstance = new ColorConverter();
    private HashMap<String, Color> colorMap;


    public static ColorConverter getInstance() {
        return ourInstance;
    }

    private ColorConverter() {
        colorMap = new HashMap<>();
        initColorMap();
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

    public Color getColor(String color){
        return colorMap.get(color);
    }

    public HashMap<String, Color> getColorMap(){
        return colorMap;
    }


}
