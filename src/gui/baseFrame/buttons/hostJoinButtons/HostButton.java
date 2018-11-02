package gui.baseFrame.buttons.hostJoinButtons;

import domain.ConnectGameHandler;
import gui.baseFrame.BaseFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HostButton extends JButton implements ActionListener {
    private JTextField IDField;
    private JTextField portField;

    public HostButton(String text, JTextField IDField, JTextField portField) {
        super(text);
        this.addActionListener(this);
        this.IDField = IDField;
        this.portField = portField;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("HostGame Button Pressed");
        BaseFrame.setStatus("Lobby");
        ConnectGameHandler.getInstance().connectHost(IDField.getText(), Integer.parseInt(portField.getText()));
    }
}
