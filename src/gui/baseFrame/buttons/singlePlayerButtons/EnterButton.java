package gui.baseFrame.buttons.singlePlayerButtons;

import domain.controller.ConnectGameHandler;
import gui.InputChecker;
import gui.baseFrame.BaseFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnterButton extends JButton implements ActionListener {
    private JTextField userNameField;

    public EnterButton(String text, JTextField userNameField) {
        super(text);
        this.userNameField = userNameField;
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("Enter Button Pressed");
        if(InputChecker.getInstance().userNameChecker(userNameField.getText())) {
            ConnectGameHandler.getInstance().connectHost(userNameField.getText(), 2222, false);
            BaseFrame.setStatus("Lobby");
        }
    }
}
