package gui.baseFrame.buttons.lobbyButtons;

import domain.controller.MonopolyGameController;

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
        int startStatus = MonopolyGameController.getInstance().checkReadiness();
        if (startStatus == -1) {
            JOptionPane.showMessageDialog(null, "Cannot start with only 1 player",
                    "Error", JOptionPane.WARNING_MESSAGE);
        } else if (startStatus == 1) {
            JOptionPane.showMessageDialog(null, startStatus + " Player is not ready yet!",
                    "Error", JOptionPane.WARNING_MESSAGE);
        } else if (startStatus != 0) {
            JOptionPane.showMessageDialog(null, startStatus + " Players are not ready yet!",
                    "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
}
