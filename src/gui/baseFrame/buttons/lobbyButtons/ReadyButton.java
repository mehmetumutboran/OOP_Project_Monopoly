package gui.baseFrame.buttons.lobbyButtons;

import domain.client.PlayerActionController;
import gui.baseFrame.ColorBox;
import gui.baseFrame.buttons.BaseButton;
import gui.baseFrame.buttons.multiplayerButtons.BackButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * When player presses ReadyButton it changes and becomes unready Button.
 */
public class ReadyButton extends BaseButton {
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
        this.backButton = backButton;
        this.colorBox = colorBox;
    }

    // TODO CHANGE THE (%2) LOGIC IF POSSIBLE SINCE IT DOESN'T HANDLE EVERYTHING
    @Override
    public void actionPerformed(ActionEvent e) {
        PlayerActionController.getInstance().changePlayerReadiness();
        counter++;
        this.setText(state[counter % 2]);
        this.setBackground(colors[counter % 2]);
        this.backButton.setEnabled(counter % 2 == 0);
        this.colorBox.setEnabled(counter % 2 == 0);
    }
}
