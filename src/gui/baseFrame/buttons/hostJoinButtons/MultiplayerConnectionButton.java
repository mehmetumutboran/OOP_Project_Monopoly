package gui.baseFrame.buttons.hostJoinButtons;

import network.listeners.ConnectionFailedListener;

import javax.swing.*;
import java.awt.event.ActionListener;

public abstract class MultiplayerConnectionButton extends JButton implements ActionListener, ConnectionFailedListener {

    public MultiplayerConnectionButton(String text) {
        super(text);
        this.addActionListener(this);
    }
}
