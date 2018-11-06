package gui.baseFrame.buttons.hostJoinButtons;

import domain.controller.ConnectGameHandler;
import gui.baseFrame.BaseFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JoinButton extends JButton implements ActionListener {
    private JTextField IDField;
    private JTextField IPField;
    private JTextField portField;

    public JoinButton(String text, JTextField IDField, JTextField IPField, JTextField portField) {
        super(text);
        this.addActionListener(this);
        this.IDField = IDField;
        this.IPField = IPField;
        this.portField = portField;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("Join Button Pressed");
        BaseFrame.setStatus("Lobby");
        ConnectGameHandler.getInstance().connectClient(IDField.getText(), IPField.getText(), Integer.parseInt(portField.getText()));
    }
}
