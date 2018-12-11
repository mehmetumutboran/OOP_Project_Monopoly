package gui.controlDisplay.butons;

import domain.client.PlayerActionController;
import domain.client.UIUpdater;
import domain.server.listeners.ButtonChangeListener;
import domain.server.listeners.TurnChangedListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinishTurnButton extends JButton implements ActionListener, TurnChangedListener, ButtonChangeListener {

    private final int INDEX = 4;

    public FinishTurnButton(String text) {
        super(text);
        this.addActionListener(this);
        this.setEnabled(false);
        UIUpdater.getInstance().addTurnChangedListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        PlayerActionController.getInstance().finishTurn();
    }

    @Override
    public void onTurnChangedEvent(String enable) {
        this.setEnabled(enable.charAt(INDEX)=='1');
    }

    @Override
    public void onButtonChangeEvent() {

    }
}
