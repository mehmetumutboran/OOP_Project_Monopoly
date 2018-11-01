package gui.baseFrame.buttons.multiplayerButtons;

import gui.baseFrame.BaseFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JoinGameButton extends JButton implements ActionListener {
    public JoinGameButton(String text) {
        super(text);
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("Join Button Pressed");
        BaseFrame.setStatus("Join");
    }
}
