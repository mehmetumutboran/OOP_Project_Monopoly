package gui.controlDisplay.butons;

import domain.client.PlayerActionController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PauseButton extends JButton implements ActionListener {

    public PauseButton(String text) {
        super(text);
        this.addActionListener(this);
        this.setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PlayerActionController.getInstance().pause();
    }
}
