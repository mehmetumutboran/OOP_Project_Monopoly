package gui.baseFrame.buttons.lobbyButtons;

import domain.controller.MonopolyGameController;
import gui.baseFrame.ColorBox;
import gui.baseFrame.buttons.multiplayerButtons.BackButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * When player presses ReadyButton it changes and becomes unready Button.
 */
public class ReadyButton extends JButton implements ActionListener {
    private String[] state;
    private Color[] colors;
    private static int counter = 0;
    private BackButton backButton;
    private ColorBox colorBox;

    public ReadyButton(BackButton backButton, ColorBox colorBox) {
        super("Ready");
        state = new String[]{"Ready", "Unready"};
        colors = new Color[]{Color.GREEN, Color.RED};
        this.setBackground(colors[0]);
        addActionListener(this);
        this.backButton = backButton;
        this.colorBox = colorBox;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MonopolyGameController.getInstance().changePlayerReadiness(0);
        counter++;
        this.setText(state[counter % 2]);
        this.setBackground(colors[counter % 2]);
        this.backButton.setEnabled(counter % 2 == 0);
        this.colorBox.setEnabled(counter % 2 == 0);
    }
}
