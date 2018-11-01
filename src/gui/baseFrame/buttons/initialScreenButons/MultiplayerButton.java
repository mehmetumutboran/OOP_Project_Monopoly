package gui.baseFrame.buttons.initialScreenButons;

import gui.baseFrame.BaseFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MultiplayerButton extends JButton implements ActionListener {

    public MultiplayerButton(String text) {
        super(text);
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("Multi Button Pressed");
        BaseFrame.setStatus("Multi");
    }
}
