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
        boolean startStatus = MonopolyGameController.getInstance().checkReadiness();
        if(!startStatus) {
            JOptionPane.showMessageDialog(null, "Not All Players are ready",
                    "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
}
