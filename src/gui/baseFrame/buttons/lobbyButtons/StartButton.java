package gui.baseFrame.buttons.lobbyButtons;

import domain.client.PlayerActionController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartButton extends JButton implements ActionListener {

    public StartButton(String text) {
        super(text);
        addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        PlayerActionController.getInstance().startGame();
    }
}
