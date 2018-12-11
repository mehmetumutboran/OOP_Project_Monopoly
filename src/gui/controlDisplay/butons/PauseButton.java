package gui.controlDisplay.butons;

import domain.client.PlayerActionController;
import domain.server.listeners.ButtonChangeListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PauseButton extends JButton implements ActionListener, ButtonChangeListener {

    public PauseButton(String text) {
        super(text);
        this.addActionListener(this);
        this.setEnabled(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PlayerActionController.getInstance().pause();
    }

    @Override
    public void onButtonChangeEvent() {

    }
}
