package gui.baseFrame;

import domain.ConnectGameHandler;
import domain.MonopolyGameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class ColorBox extends JComboBox implements ActionListener {

    private HashMap<String,Color> colorMap;
    private ArrayList<Color> colorList;
    private JLabel playerLabel;

    //private Color playerColor;


    public ColorBox(ArrayList<Color> colorList){
        super(colorList.toArray());
        this.colorList = colorList;
        initColorMap();
        this.addActionListener(this);
    }

    public void setPlayerLabel(JLabel playerLabel){
        this.playerLabel = playerLabel;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //playerLabel.setBackground((Color) this.getSelectedItem());
        String colorChange = null;
        for (String colorName:colorMap.keySet()) {
            if(colorMap.get(colorName).equals(this.getSelectedItem())){
                colorChange = colorName;
                break;
            }
        }
        MonopolyGameController.getInstance().changePlayerColor(0,colorChange);

        // TODO ConnectGameHandler
    }

    private void initColorMap() {
        colorMap = new HashMap<>();
        colorMap.put("White",Color.white);
        colorMap.put("LightGray",Color.lightGray);
        colorMap.put("Gray",Color.gray);
        colorMap.put("Blue",Color.blue);
        colorMap.put("Cyan",Color.cyan);
        colorMap.put("Pink",Color.pink);
        colorMap.put("Green",Color.green);
        colorMap.put("Orange",Color.orange);
        colorMap.put("Magenta",Color.magenta);
        colorMap.put("Yellow",Color.yellow);
        colorMap.put("Red",Color.red);
        colorMap.put("Turquoise",new Color(38,209, 188));
    }

    public HashMap<String,Color> getColorMap(){
        return this.colorMap;
    }
}
