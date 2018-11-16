package gui.controlDisplay.panels;

import domain.controller.MonopolyGameController;
import domain.listeners.GameStartedListener;
import gui.ColorConverter;
import gui.controlDisplay.PlayerLabel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlayerLabelsPanel extends JLabel implements GameStartedListener {
    private final int SQUARE_EDGE = 90;

    private ArrayList<PlayerLabel> playerLabels;

    private PlayerStatusPanel playerStatusPanel;


    public PlayerLabelsPanel(PlayerStatusPanel playerStatusPanel) {
        this.playerStatusPanel = playerStatusPanel;

        this.setLayout(new GridLayout(2, 6));
        this.setPreferredSize(playerStatusPanel.getSize());
        MonopolyGameController.getInstance().addGameStartedListener(this);
        initGUI();

        this.setVisible(true);

    }

    private void initGUI() {
        playerLabels = new ArrayList<>();


    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (PlayerLabel playerLabel : playerLabels) {
            playerLabel.setPreferredSize(new Dimension(SQUARE_EDGE, SQUARE_EDGE));
            this.add(playerLabel);
        }
    }

    public PlayerStatusPanel getPlayerStatusPanel() {
        return playerStatusPanel;
    }

    @Override
    public void onGameStartedEvent() {
        for (int i = 0; i < MonopolyGameController.getInstance().getPlayerListName().size(); i++) {
            PlayerLabel temp = new PlayerLabel(MonopolyGameController.getInstance().getPlayerListName().get(i), this);
            temp.setBackground(ColorConverter.getInstance().getColor(
                    MonopolyGameController.getInstance().getPlayerList().get(i).getToken().getColor()));
            playerLabels.add(temp);
        }
    }
}
