package gui.controlDisplay.butons;

import domain.UIUpdater;
import domain.controller.PlayerActionController;
import domain.listeners.TurnChangedListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RollDiceButton extends JButton implements ActionListener, TurnChangedListener {

    public RollDiceButton(String text) {
        super(text);
        this.addActionListener(this);
        this.setEnabled(false);
        UIUpdater.getInstance().addTurnChangedLListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        PlayerActionController.getInstance().roll();
        System.out.println("Roll dice clicked");
    }

    @Override
    public void onTurnChangedEvent(boolean isEnabled) {
        this.setEnabled(isEnabled);
    }
}
