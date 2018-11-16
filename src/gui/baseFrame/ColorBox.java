package gui.baseFrame;

import domain.controller.MonopolyGameController;
import domain.listeners.DisableColorChangeListener;
import domain.listeners.PlayerListChangedListener;
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

public class ColorBox extends JComboBox implements ActionListener, DisableColorChangeListener, PlayerListChangedListener {

    private ArrayList<String> colorList = (ArrayList<String>) (Stream.of("White", "LightGray", "Gray", "Blue", "Cyan", "Pink", "Green",
            "Orange", "Magenta", "Yellow", "Red","Turquoise").collect(Collectors.toList()));
    private String selectedItem;

    public ColorBox() {
        super();
        for (int i=0; i<colorList.size();i++) {
            this.insertItemAt(colorList.get(i), i);
        }
        this.setRenderer(ColorBoxRenderer.getInstance());
        MonopolyGameController.getInstance().addPlayerListChangedListener(this);
        this.addActionListener(this);
        MonopolyGameController.getInstance().addDisableColorChangeListener(this);
    }

    private void refresh(ArrayList<String> selectedColors){
        this.removeAllItems();
        this.addItem(selectedItem);
        //this.insertItemAt(ColorConverter.getInstance().getColor(selectedItem),0);
        ArrayList<String> temp = (ArrayList<String>) colorList.clone();
        temp.removeAll(selectedColors);
        //temp.add(selectedItem);
        temp.remove(selectedItem);
        for (int i=0; i < temp.size();i++) {
            this.insertItemAt(temp.get(i), i+1);
        }
        this.setRenderer(ColorBoxRenderer.getInstance());
        //this.removeItem(selectedItem);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.selectedItem = (String) this.getSelectedItem();
        MonopolyGameController.getInstance().changePlayerColor(0,(String) this.getSelectedItem());
    }

    @Override
    public void onDisableColorChangedEvent() {
        if(this.isEnabled()) this.setEnabled(false);
        else this.setEnabled(true);
    }

    @Override
    public void onPlayerListChangedEvent(ArrayList<String> selectedColors) {
        //refresh(selectedColors);
    }
}
