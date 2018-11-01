package gui.baseFrame.buttons.hostJoinButtons;

import gui.baseFrame.BaseFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HostButton extends JButton implements ActionListener {

    public HostButton(String text) {
        super(text);
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("HostGame Button Pressed");
        BaseFrame.setStatus("Lobby");
    }
}
