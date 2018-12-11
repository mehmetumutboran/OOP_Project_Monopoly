package gui.baseFrame.buttons.multiplayerButtons;

import gui.baseFrame.BaseFrame;
import gui.baseFrame.buttons.BaseButton;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JoinGameButton extends BaseButton {
    public JoinGameButton(String text) {
        super(text);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("Join Button Pressed");
        BaseFrame.setStatus("Join");
    }
}
