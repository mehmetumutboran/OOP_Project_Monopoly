package gui.baseFrame;

import domain.controller.MonopolyGameController;
import gui.ColorBoxRenderer;
import gui.ColorConverter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ColorBox extends JComboBox implements ActionListener {

    private ArrayList<String> colorList = (ArrayList<String>) (Stream.of("White", "LightGray", "Gray", "Blue", "Cyan", "Pink", "Green",
            "Orange", "Magenta", "Yellow", "Red","Turquoise").collect(Collectors.toList()));


    public ColorBox() {
        super();
        for (int i=0; i<colorList.size();i++) {
            this.insertItemAt(colorList.get(i), i);
        }
        this.setRenderer(new ColorBoxRenderer());
        this.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        MonopolyGameController.getInstance().changePlayerColor(0,(String) this.getSelectedItem());
    }

}
