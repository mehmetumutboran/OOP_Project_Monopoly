package gui.baseFrame.buttons.lobbyButtons;

import domain.controller.MonopolyGameController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartButton extends JButton implements ActionListener {
    JDialog dialog;

    public StartButton(String text) {
        super(text);
        addActionListener(this);
        dialog = new JDialog();
        dialog.setTitle("Player's are not Ready");
        JLabel label = new JLabel("Player's are not ready");
        JButton button = new JButton("OK");
        button.addActionListener(e -> dialog.setVisible(false));
        dialog.add(label);
        dialog.add(button);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(!MonopolyGameController.getInstance().checkReadiness()){
            dialog.setVisible(true);
        }
    }
}
