package gui.controlDisplay.butons;

import domain.controller.PlayerActionController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RollDiceButton extends JButton implements ActionListener {

    public RollDiceButton(String text) {
        super(text);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        PlayerActionController.getInstance().roll();
    }
}
