package gui.baseFrame.buttons.lobbyButtons;

import domain.client.PlayerActionController;
import gui.baseFrame.buttons.BaseButton;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartButton extends BaseButton {

    public StartButton(String text) {
        super(text);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        PlayerActionController.getInstance().startGame();
    }
}
