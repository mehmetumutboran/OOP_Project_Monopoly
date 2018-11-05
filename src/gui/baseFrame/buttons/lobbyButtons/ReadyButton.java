package gui.baseFrame.buttons.lobbyButtons;

import domain.MonopolyGameController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * When player presses REadyButton it changes and becomes unready Button.
 */
public class ReadyButton extends JButton implements ActionListener {
    public ReadyButton(String text) {
        super(text);
        addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        MonopolyGameController.getInstance().changePlayerReadiness(0);
    }
}
