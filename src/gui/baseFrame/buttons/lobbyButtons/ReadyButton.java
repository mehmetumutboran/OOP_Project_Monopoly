package gui.baseFrame.buttons.lobbyButtons;

import domain.controller.MonopolyGameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterAbortException;

/**
 * When player presses REadyButton it changes and becomes unready Button.
 */
public class ReadyButton extends JButton implements ActionListener {
    private String[] state;
    private Color[] colors;
    private static int counter = 0;

    public ReadyButton() {
        super("Ready");
        state = new String[]{"Ready", "Unready"};
        colors = new Color[]{Color.GREEN, Color.RED};
        this.setBackground(colors[0]);
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MonopolyGameController.getInstance().changePlayerReadiness(0);
        counter++;
        this.setText(state[counter%2]);
        this.setBackground(colors[counter%2]);
    }
}
