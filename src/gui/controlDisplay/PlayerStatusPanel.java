package gui.controlDisplay;

import domain.controller.MonopolyGameController;

import javax.swing.*;
import java.awt.*;

/**
 * Panel which has {@link domain.player.Player}s' name on it.
 * Allows user to click on Player Labels and see that player's assets.
 */
public class PlayerStatusPanel extends JPanel {
    private int width;
    private int height;

    private CardLayout cardLayout;

    private PlayerLabelsPanel playerLabelsPanel;
    private PlayerInfoPanel playerInfoPanel;


    public PlayerStatusPanel(int width, int height) {
        this.width = width;
        this.height = height;

        this.setPreferredSize(new Dimension(width, 180));
        cardLayout = new CardLayout();
        this.setLayout(cardLayout);

        initGUI();
    }

    private void initGUI() {
        playerLabelsPanel = new PlayerLabelsPanel(this);
        playerInfoPanel = new PlayerInfoPanel(this);

        this.add(playerLabelsPanel, "Label Panel");
        this.add(playerInfoPanel, "Info Panel");
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public void setPlayerInfoPanel(String name) {

        for (int i = 0; i < MonopolyGameController.getInstance().getPlayerList().size(); i++) {
            if (name.equals(MonopolyGameController.getInstance().getPlayerList().get(i).getName())) {
                playerInfoPanel.setPlayer(i);
            }
        }
    }
}
