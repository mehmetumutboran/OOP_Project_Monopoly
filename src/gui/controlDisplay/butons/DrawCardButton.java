package gui.controlDisplay.butons;

import domain.client.ClientCommunicationHandler;
import domain.client.PlayerActionController;
import domain.client.UIUpdater;
import domain.server.listeners.TurnChangedListener;
import domain.util.Flags;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DrawCardButton extends JButton implements TurnChangedListener, ActionListener {

    private final int INDEX = 8;

    public DrawCardButton(String text) {
        super(text);
        this.addActionListener(this);
        this.setEnabled(false);
        UIUpdater.getInstance().addTurnChangedListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        PlayerActionController.getInstance().drawCard();
    }

    @Override
    public void onTurnChangedEvent(String enable) {
        this.setEnabled(enable.charAt(INDEX)=='1');
    }
}
