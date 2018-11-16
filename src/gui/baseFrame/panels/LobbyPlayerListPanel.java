package gui.baseFrame.panels;

import domain.controller.MonopolyGameController;
import domain.listeners.PlayerListChangedListener;
import gui.ColorConverter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LobbyPlayerListPanel extends JPanel implements PlayerListChangedListener {
    private final int SQUARE_EDGE = 80;

    private ArrayList<JLabel> playerLabels;


    public LobbyPlayerListPanel() {
        playerLabels = new ArrayList<>();
        MonopolyGameController.getInstance().addPlayerListChangedListener(this);

        this.setVisible(true);
        this.setBackground(Color.black);
        this.setOpaque(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < playerLabels.size(); i++) {
            this.remove(playerLabels.get(i));
            playerLabels.get(i).setBounds( i * SQUARE_EDGE, 0, SQUARE_EDGE, SQUARE_EDGE);
            this.add(playerLabels.get(i));
        }


    }

    public void setPlayerLabelList(ArrayList<ArrayList<String>> playerAttributes) {
        playerLabels.removeIf(p -> !MonopolyGameController.getInstance().getPlayerListName()
                .contains(p.getText().substring(6, p.getText().indexOf('<', 2))));

        for (ArrayList<String> player : playerAttributes) {
            getPlayer(player).setText("<HTML>" + player.get(0) + "<BR>" + player.get(2) + "</HTML>");
            getPlayer(player).setBackground(ColorConverter.getInstance().getColorMap().get(player.get(1)));
        }

        this.removeAll();
        revalidate();
        repaint();
    }

    private JLabel getPlayer(ArrayList<String> list) {
        for (int i = 0; i < playerLabels.size(); i++) {
            if (playerLabels.get(i) == null) continue;
            else if (playerLabels.get(i).getText().contains(list.get(0))) {
                return playerLabels.get(i);
            }
        }
        JLabel temp = new JLabel(list.get(0));
        temp.setBackground(ColorConverter.getInstance().getColorMap().get(list.get(1)));
        temp.setOpaque(true);
        this.playerLabels.add(temp);
        return getPlayer(list);
    }

    @Override
    public void onPlayerListChangedEvent(ArrayList<String> selectedColors) {
        setPlayerLabelList(MonopolyGameController.getInstance().getPlayerConnectAttributes());
    }

}
