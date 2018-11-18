package gui.baseFrame.buttons.hostJoinButtons;

import domain.controller.ConnectGameHandler;
import gui.InputChecker;
import gui.baseFrame.BaseFrame;
import network.client.clientFacade.ClientFacade;
import network.listeners.ConnectionFailedListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HostButton extends JButton implements ActionListener, ConnectionFailedListener {
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
        this.IDField = IDField;
        this.portField = portField;
        this.addActionListener(this);
        ClientFacade.getInstance().addConnectionFailedListener(this);

    }

    /**
     * When player Presses this button it sends a request to network layer to create new server.
     *
     * @param actionEvent {@link ActionEvent}
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("HostGame Button Pressed");
//        LobbyPanel.setHost(true);
        checkConnection();
    }

    private void checkConnection() {
        String username = null;
        int port = 0;

        if (InputChecker.getInstance().userNameChecker(IDField.getText())) {
            username = IDField.getText();
        } else return;

        if (InputChecker.getInstance().portChecker(portField.getText())) {
            port = Integer.parseInt(portField.getText());
        } else return;

        String status = ConnectGameHandler.getInstance().connectHost(username, port);
        if (status.equals("Successful")) {
            BaseFrame.setStatus("Lobby");
        }
    }

    @Override
    public void onConnectionFailedEvent() {
        JOptionPane.showMessageDialog(null, "Cannot connect to the server!!",
                "Error", JOptionPane.WARNING_MESSAGE);
    }
}
