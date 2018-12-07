package gui.controlDisplay.panels;

import domain.client.UIUpdater;
import domain.server.listeners.TurnUpdateListener;
import domain.util.GameInfo;

import javax.swing.*;
import java.awt.*;

/**
 * Panel which has {@link domain.server.player.Player}s' name on it.
 * Allows user to click on Player Labels and see that player's assets.
 */
public class PlayerStatusPanel extends JPanel implements TurnUpdateListener {
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
        UIUpdater.getInstance().addTurnUpdateListener(this);

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

        for (int i = 0; i < GameInfo.getInstance().getPlayerListSize(); i++) {
            if (name.equals(GameInfo.getInstance().getNameFromIndex(i))) {
                playerInfoPanel.setPlayer(i);
            }
        }
    }

    @Override
    public void onTurnUpdateEvent() {
        revalidate();
        repaint();
    }
}
