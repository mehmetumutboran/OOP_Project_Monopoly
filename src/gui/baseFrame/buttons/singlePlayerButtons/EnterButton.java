package gui.baseFrame.buttons.singlePlayerButtons;

import domain.client.PlayerActionController;
import gui.baseFrame.BaseFrame;
import gui.util.InputChecker;

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
        if (InputChecker.getInstance().userNameChecker(userNameField.getText())) {
            PlayerActionController.getInstance().host(userNameField.getText(), 2222, false);
            BaseFrame.setStatus("Lobby");
        }
    }
}
