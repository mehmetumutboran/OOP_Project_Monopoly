package gui.baseFrame.buttons.multiplayerButtons;

import gui.baseFrame.BaseFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HostGameButton extends JButton implements ActionListener {
    public HostGameButton(String text) {
        super(text);
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("Host Button Pressed");
        BaseFrame.setStatus("Host");
    }
}
