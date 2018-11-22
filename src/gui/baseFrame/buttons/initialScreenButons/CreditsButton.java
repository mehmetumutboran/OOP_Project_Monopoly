package gui.baseFrame.buttons.initialScreenButons;

import gui.baseFrame.BaseFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreditsButton extends JButton implements ActionListener {

    public CreditsButton(String text) {
        super(text);
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("Credits Button Pressed");
        BaseFrame.setStatus("Credits");
    }
}
