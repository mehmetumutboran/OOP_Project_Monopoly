package gui.controlDisplay;

import domain.controller.MonopolyGameController;
import domain.listeners.GameStartedListener;
import gui.ColorConverter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlayerLabelsPanel extends JLabel implements GameStartedListener {
    private int INITIAL_X = 0;
    private int INITIAL_Y = 0;
    private final int LAST_X = 490;
    private final int SQUARE_EDGE = 90;

    private ArrayList<PlayerLabel> playerLabels;

    private PlayerStatusPanel playerStatusPanel;


    public PlayerLabelsPanel(PlayerStatusPanel playerStatusPanel) {
        this.playerStatusPanel = playerStatusPanel;

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
        for (int i = 0; i < playerLabels.size(); i++) {
            if (INITIAL_X + i * SQUARE_EDGE >= LAST_X) {
                INITIAL_X = (INITIAL_X - 10) % (LAST_X - 10) + 10; //TODO Change
                INITIAL_Y += INITIAL_Y + SQUARE_EDGE;
            }
            playerLabels.get(i).setBounds(INITIAL_X + i * SQUARE_EDGE, INITIAL_Y, SQUARE_EDGE, SQUARE_EDGE);
            this.add(playerLabels.get(i));
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
