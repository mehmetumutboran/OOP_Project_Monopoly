package gui.controlDisplay.panels;

import domain.client.UIUpdater;
import domain.server.listeners.GameStartedListener;
import domain.server.listeners.PlayerQuitEventListener;
import domain.server.listeners.TurnChangedListener;
import domain.server.listeners.TurnUpdateListener;
import domain.util.GameInfo;
import gui.controlDisplay.PlayerLabel;
import gui.util.ColorConverter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlayerLabelsPanel extends JLabel implements GameStartedListener, PlayerQuitEventListener, TurnUpdateListener {
    private final int SQUARE_EDGE = 90;

    private ArrayList<PlayerLabel> playerLabels;

    private PlayerStatusPanel playerStatusPanel;


    public PlayerLabelsPanel(PlayerStatusPanel playerStatusPanel) {
        this.playerStatusPanel = playerStatusPanel;
        UIUpdater.getInstance().addPlayerQuitEventListener(this);

        this.setLayout(new GridLayout(2, 6));
        this.setPreferredSize(playerStatusPanel.getSize());
        UIUpdater.getInstance().addGameStartedListener(this);
        UIUpdater.getInstance().addTurnUpdateListener(this);
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
        setPlayerLabel();
    }

    public void setPlayerLabel() {
        int size = GameInfo.getInstance().getPlayerListSize();
        for (int i = 0; i < size; i++) {
            PlayerLabel temp = new PlayerLabel(GameInfo.getInstance().getNameFromIndex(i), this);
            temp.setBackground(ColorConverter.getInstance().getColor(
                    GameInfo.getInstance().getColorFromIndex(i)));
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

    @Override
    public void onTurnUpdateEvent() {
        for (int i = 0; i < playerLabels.size(); i++) {
            playerLabels.get(i).setText(playerLabels.get(i).getName());
            if(GameInfo.getInstance().isCurrentPlayerFromIndex(i)) {
                playerLabels.get(i).setText(playerLabels.get(i).getName() + "   Current Turn!!");
            }
        }
        revalidate();
        repaint();
    }
}
