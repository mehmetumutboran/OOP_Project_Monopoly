package gui.baseFrame.buttons.lobbyButtons;

import domain.client.PlayerActionController;
import domain.client.UIUpdater;
import domain.server.listeners.ReadinessChangedListener;
import gui.baseFrame.buttons.BaseButton;

import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * When player presses ReadyButton it changes and becomes unready Button.
 */
public class ReadyButton extends BaseButton implements ReadinessChangedListener {
    private String[] state;
    private Color[] colors;

    public ReadyButton() {
        super("Ready");
        state = new String[]{"Ready", "Unready"};
        colors = new Color[]{Color.GREEN, Color.RED};
        this.setBackground(colors[0]);
        UIUpdater.getInstance().addReadinessChangedListener(this);
    }

    // TODO CHANGE THE (%2) LOGIC IF POSSIBLE SINCE IT DOESN'T HANDLE EVERYTHING
    @Override
    public void actionPerformed(ActionEvent e) {
        PlayerActionController.getInstance().changePlayerReadiness();
    }


    @Override
    public void onReadinessChangedEvent(boolean ready) {
        int counter = 0;
        if (ready) counter = 1;
        this.setText(state[counter % 2]);
        this.setBackground(colors[counter % 2]);
    }
}
