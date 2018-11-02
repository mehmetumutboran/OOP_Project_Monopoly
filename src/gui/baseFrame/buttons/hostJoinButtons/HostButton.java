package gui.baseFrame.buttons.hostJoinButtons;

import domain.ConnectGameHandler;
import gui.baseFrame.BaseFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HostButton extends JButton implements ActionListener {
    private JTextField IDField;
    private JTextField portField;

    /**
     * Constructor
     *
     * @param text      Label of the button
     * @param IDField   {@link JTextField} for Username
     * @param portField {@link JTextField} for port number
     */
    public HostButton(String text, JTextField IDField, JTextField portField) {
        super(text);
        this.addActionListener(this);
        this.IDField = IDField;
        this.portField = portField;
    }

    /**
     * When player Presses this button it sends a request to network layer to create new Server.
     *
     * @param actionEvent {@link ActionEvent}
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("HostGame Button Pressed");
        BaseFrame.setStatus("Lobby");
        ConnectGameHandler.getInstance().connectHost(IDField.getText(), Integer.parseInt(portField.getText()));
    }
}
