package gui.controlDisplay.butons;

import domain.client.UIUpdater;
import domain.server.listeners.TurnChangedListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MortgageButton extends JButton implements ActionListener, TurnChangedListener {
    public MortgageButton(String text) {
        super(text);
        this.addActionListener(this);
        this.setEnabled(false);
        UIUpdater.getInstance().addTurnChangedListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }

    @Override
    public void onTurnChangedEvent(boolean isEnabled) {
        this.setEnabled(isEnabled);
    }
}
