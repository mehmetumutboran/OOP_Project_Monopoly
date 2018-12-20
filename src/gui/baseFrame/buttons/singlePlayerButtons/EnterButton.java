package gui.baseFrame.buttons.singlePlayerButtons;

import domain.client.PlayerActionController;
import gui.baseFrame.BaseFrame;
import gui.baseFrame.buttons.BaseButton;
import gui.util.InputChecker;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class EnterButton extends BaseButton {
    private JTextField userNameField;

    public EnterButton(String text, JTextField userNameField) {
        super(text);
        this.userNameField = userNameField;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("Enter Button Pressed");
        if (InputChecker.getInstance().userNameChecker(userNameField.getText())) {
            PlayerActionController.getInstance().host(userNameField.getText(), 2222, false);
            BaseFrame.getInstance().setStatus("Lobby");
        }
    }
}
