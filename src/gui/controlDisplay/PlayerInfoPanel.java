package gui.controlDisplay;

import domain.controller.MonopolyGameController;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PlayerInfoPanel extends JPanel implements MouseListener {

    private PlayerStatusPanel playerStatusPanel;

    private JLabel playerInfo;

    public PlayerInfoPanel(PlayerStatusPanel playerStatusPanel) {
        this.playerStatusPanel = playerStatusPanel;
        this.playerInfo = new JLabel();
        this.add(playerInfo);
        this.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        playerStatusPanel.getCardLayout().next(playerStatusPanel);
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    public void setPlayer(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>")
                .append("<body>")
                .append("<div align=\"left\" style=\"color:red; font-size:11px;\">Name: ")
                .append(MonopolyGameController.getInstance().getPlayerList().get(i).getName())
                .append("</div>")
                .append("</body></html>");
        playerInfo.setText(sb.toString());
    }
}
