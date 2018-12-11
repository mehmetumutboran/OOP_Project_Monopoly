package gui.baseFrame.buttons.hostJoinButtons;

import domain.client.PlayerActionController;
import gui.baseFrame.buttons.BaseButton;
import gui.util.InputChecker;
import network.listeners.ConnectionFailedListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JoinButton extends BaseButton implements ConnectionFailedListener {
    private JTextField IDField;
    private JTextField IPField;
    private JTextField portField;

    public JoinButton(String text, JTextField IDField, JTextField IPField, JTextField portField) {
        super(text);
        this.IDField = IDField;
        this.IPField = IPField;
        this.portField = portField;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("Join Button Pressed");
//        LobbyPanel.setHost(false);
        checkConnection();
    }

    private void checkConnection() {
        String username = null;
        String ip = null;
        int port = 0;
        if (InputChecker.getInstance().userNameChecker(IDField.getText())) {
            username = IDField.getText();
        } else return;

        if (InputChecker.getInstance().portChecker(portField.getText())) {
            port = Integer.parseInt(portField.getText());
        } else return;

        if (InputChecker.getInstance().ipChecker(IPField.getText())) {
            ip = IPField.getText();
        } else return;

        PlayerActionController.getInstance().join(username, ip, port);
        //ConnectGameHandler.getInstance().connectClient(username, ip,
//                port, false);
        //else if(status.equals("Failed"))
    }

    @Override
    public void onConnectionFailedEvent() {
        JOptionPane.showMessageDialog(null, "Cannot connect to the server!!",
                "Error", JOptionPane.WARNING_MESSAGE);
    }
}
