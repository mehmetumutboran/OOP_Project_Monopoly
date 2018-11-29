package gui.baseFrame.panels;

import domain.server.controller.MonopolyGameController;
import domain.server.listeners.PlayerListChangedListener;
import domain.util.GameInfo;
import gui.util.ColorConverter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LobbyPlayerListPanel extends JPanel implements PlayerListChangedListener {
    private final int SQUARE_EDGE = 80;

    private ArrayList<LobbyPlayerLabel> playerLabels;


    public LobbyPlayerListPanel() {
        playerLabels = new ArrayList<>();
        GameInfo.getInstance().addPlayerListChangedListener(this);

        this.setVisible(true);
        this.setOpaque(false);
        //TODO Border
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
                temp.getName().equals(GameInfo.getInstance().getMyself().getName()))
            temp.removeMouseListener(temp);
        this.playerLabels.add(temp);
        return getPlayer(list, isHost);
    }

    @Override
    public void onPlayerListChangedEvent(ArrayList<String> selectedColors) {
        setPlayerLabelList(GameInfo.getInstance().getPlayerConnectAttributes(),
                (GameInfo.getInstance().isMyselfHost()));
    }

}
