package gui.controlDisplay.butons;

import domain.UIUpdater;
import domain.controller.PlayerActionController;
import domain.listeners.TurnChangedListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpgradeButton extends JButton implements ActionListener, TurnChangedListener {
    public UpgradeButton(String text) {
        super(text);
        this.addActionListener(this);
        this.setEnabled(false);
        UIUpdater.getInstance().addTurnChangedListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("Upgrade Button is clicked");
    }

    @Override
    public void onTurnChangedEvent(boolean isEnabled) {
        this.setEnabled(isEnabled);
    }
}