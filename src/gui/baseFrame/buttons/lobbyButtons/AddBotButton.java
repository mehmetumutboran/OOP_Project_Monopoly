package gui.baseFrame.buttons.lobbyButtons;

import domain.controller.ConnectGameHandler;
import gui.baseFrame.ColorBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class AddBotButton extends JButton implements ActionListener {
    private String[] names = {"Annie", "Buddy", "John", "Amelia", "Tom", "Sophia", "Jessie", "Amy", "Luna", "Eric", "Ross"};
    private static int count = 0;

    public AddBotButton(String text) {
        super(text);
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        count++;
        Random random = new Random();
        ConnectGameHandler.getInstance().connectBot("Bot " + names[count%11], ColorBox.colorList.get(random.nextInt(ColorBox.colorList.size())));
    }
}
