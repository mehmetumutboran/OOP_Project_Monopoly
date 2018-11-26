package gui.controlDisplay.butons;

import domain.client.UIUpdater;
import domain.server.listeners.TurnChangedListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DowngradeButton extends JButton implements ActionListener, TurnChangedListener {
    public DowngradeButton(String text) {
        super(text);
        this.addActionListener(this);
        this.setEnabled(false);
        UIUpdater.getInstance().addTurnChangedListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //TODO should call another label for choosing own deeds.
        //PlayerActionController.getInstance().downgrade();
        System.out.println("Downgrade Button is clicked");
    }

    @Override
    public void onTurnChangedEvent(boolean isEnabled) {
        this.setEnabled(isEnabled);
    }
}



