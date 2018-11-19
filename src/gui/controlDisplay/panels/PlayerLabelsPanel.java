package gui.controlDisplay.panels;

import domain.UIUpdater;
import domain.controller.MonopolyGameController;
import domain.listeners.GameStartedListener;
import domain.listeners.PlayerQuitEventListener;
import gui.ColorConverter;
import gui.controlDisplay.PlayerLabel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlayerLabelsPanel extends JLabel implements GameStartedListener, PlayerQuitEventListener {
    private final int SQUARE_EDGE = 90;

    private ArrayList<PlayerLabel> playerLabels;

    private PlayerStatusPanel playerStatusPanel;


    public PlayerLabelsPanel(PlayerStatusPanel playerStatusPanel) {
        this.playerStatusPanel = playerStatusPanel;
        UIUpdater.getInstance().addPlayerQuitEventListener(this);

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
    public void onGameStartedEvent(ArrayList<String> pca) {
        for (int i = 0; i < MonopolyGameController.getInstance().getPlayerListName().size(); i++) {
            PlayerLabel temp = new PlayerLabel(MonopolyGameController.getInstance().getPlayerListName().get(i), this);
            temp.setBackground(ColorConverter.getInstance().getColor(
                    MonopolyGameController.getInstance().getPlayerList().get(i).getToken().getColor()));
            playerLabels.add(temp);
        }
    }

    @Override
    public void onPlayerQuitEvent(String name) {
        playerLabels.removeIf(p -> p.getText().equals(name));
        System.out.println("\n\n ----------------==========-----------======\n" + playerLabels + "\n\n");

//        playerLabels.forEach(this::remove);
        this.removeAll();
        revalidate();
        repaint();
    }
}
