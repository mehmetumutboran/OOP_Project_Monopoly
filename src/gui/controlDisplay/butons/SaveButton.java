package gui.controlDisplay.butons;

import domain.client.PlayerActionController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveButton extends JButton implements ActionListener {

    public SaveButton(String text) {
        super(text);
        this.addActionListener(this);
        this.setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PlayerActionController.getInstance().save();
    }
}
