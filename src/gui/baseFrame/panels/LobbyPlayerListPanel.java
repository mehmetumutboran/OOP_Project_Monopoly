package gui.baseFrame.panels;

import domain.controller.MonopolyGameController;
import domain.listeners.PlayerListChangedListener;
import gui.ColorConverter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LobbyPlayerListPanel extends JPanel implements PlayerListChangedListener {
    private final int SQUARE_EDGE = 80;

    private ArrayList<LobbyPlayerLabel> playerLabels;


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
            playerLabels.get(i).setBounds(i * SQUARE_EDGE, 0, SQUARE_EDGE, SQUARE_EDGE);
            this.add(playerLabels.get(i));
        }


    }

    public void setPlayerLabelList(ArrayList<ArrayList<String>> playerAttributes, boolean isHost) {
        playerLabels.removeIf(p -> !MonopolyGameController.getInstance().getPlayerListName()
                .contains(p.getName()));

        for (ArrayList<String> player : playerAttributes) {
            getPlayer(player, isHost).setText("<HTML>" + player.get(0) + "<BR>" + player.get(2) + "</HTML>");
            getPlayer(player, isHost).setBackground(ColorConverter.getInstance().getColorMap().get(player.get(1)));
        }

        this.removeAll();
        revalidate();
        repaint();
    }

    private LobbyPlayerLabel getPlayer(ArrayList<String> list, boolean isHost) {
        for (int i = 0; i < playerLabels.size(); i++) {
            if (playerLabels.get(i) == null) continue;
            else if (playerLabels.get(i).getText().contains(list.get(0))) {
                return playerLabels.get(i);
            }
        }
        LobbyPlayerLabel temp = new LobbyPlayerLabel(list.get(0),
                ColorConverter.getInstance().getColorMap().get(list.get(1)), isHost);
        if (isHost &&
                temp.getName().equals(MonopolyGameController.getInstance().getPlayerList().get(0).getName()))
            temp.removeMouseListener(temp);
        this.playerLabels.add(temp);
        return getPlayer(list, isHost);
    }

    @Override
    public void onPlayerListChangedEvent(ArrayList<String> selectedColors) {
        setPlayerLabelList(MonopolyGameController.getInstance().getPlayerConnectAttributes(),
                (MonopolyGameController.getInstance().getPlayerList().get(0).getReadiness().equals("Host")));
    }

}
