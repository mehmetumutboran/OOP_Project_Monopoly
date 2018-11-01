package gui.baseFrame.buttons.singlePlayerButtons;

import gui.baseFrame.BaseFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnterButton extends JButton implements ActionListener {

    public EnterButton(String text) {
        super(text);
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("Enter Button Pressed");
        BaseFrame.setStatus("Lobby");
    }
}
