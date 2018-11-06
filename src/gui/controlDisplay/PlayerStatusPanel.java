package gui.controlDisplay;

import domain.controller.MonopolyGameController;
import domain.player.Player;
import gui.ColorConverter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Panel which has {@link domain.player.Player}s' name on it.
 * Allows user to click on Player Labels and see that player's assets.
 */
public class PlayerStatusPanel extends JPanel {
    private int INITIAL_X = 10;
    private  int INITIAL_Y = 10;
    private final int LAST_X = 490;
    private final int SQUARE_EDGE = 90;

    private int width;
    private int height;

    private ArrayList<PlayerLabel> playerLabels;

    public PlayerStatusPanel(int width, int height) {
        this.width = width;
        this.height = height;

        this.setPreferredSize(new Dimension(width, height/5));

        initGUI();
    }

    private void initGUI() {
        playerLabels = new ArrayList<>();

        for (int i = 0; i < MonopolyGameController.getInstance().getPlayerListName().size(); i++) {
            PlayerLabel temp = new PlayerLabel(MonopolyGameController.getInstance().getPlayerListName().get(i));
            temp.setBackground(ColorConverter.getInstance().getColor(
                    MonopolyGameController.getInstance().getPlayerList().get(i).getToken().getColor()));
            playerLabels.add(temp);
        }

    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for (int i = 0; i < playerLabels.size(); i++) {
            if(INITIAL_X + i*SQUARE_EDGE >= LAST_X){
                INITIAL_X = (INITIAL_X - 10) % (LAST_X-10) + 10;
                INITIAL_Y += INITIAL_Y + SQUARE_EDGE;
            }
            playerLabels.get(i).setBounds(INITIAL_X + i * SQUARE_EDGE, INITIAL_Y, SQUARE_EDGE, SQUARE_EDGE);
            this.add(playerLabels.get(i));
        }
    }

}
