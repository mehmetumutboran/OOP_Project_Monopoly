package gui.baseFrame.buttons.multiplayerButtons;

import gui.baseFrame.BaseFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Allows player to return previous menu
 */
public class BackButton extends JButton implements ActionListener {
    public BackButton(String text) {
        super(text);
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("Back Button Pressed");
        BaseFrame.setStatus("Init");
    }
}
