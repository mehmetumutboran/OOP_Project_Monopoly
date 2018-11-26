package gui.controlDisplay.panels;

import domain.server.board.Board;
import domain.server.controller.MonopolyGameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PlayerInfoPanel extends JPanel implements MouseListener {

    private PlayerStatusPanel playerStatusPanel;
    private JLabel playerInfo;

    public PlayerInfoPanel(PlayerStatusPanel playerStatusPanel) {
        this.playerStatusPanel = playerStatusPanel;
        this.setLayout(new BorderLayout());

        this.playerInfo = new JLabel();
        this.add(playerInfo);

        JScrollPane scrollPane = new JScrollPane(playerInfo);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.add(scrollPane);
        scrollPane.addMouseListener(this);
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
                .append("<div style=\"text-align:left; font-size:12px;\">")

                .append("<br/><span style=\"color:red\">Name: </span>")
                .append(MonopolyGameController.getInstance().getPlayerList().get(i).getName())

                .append("<br/><span style=\"color:red\">Location: </span>")
                .append(Board.getInstance().getSquare(MonopolyGameController.getInstance().getPlayerList().get(i).getToken().getLocation()[0], MonopolyGameController.getInstance().getPlayerList().get(i).getToken().getLocation()[1]))

                .append("<br/><span style=\"color:red\">Money: </span>")
                .append(MonopolyGameController.getInstance().getPlayerList().get(i).getBalance())

                .append("<br/><span style=\"color:red\">Owned Properties: </span>")
                .append(MonopolyGameController.getInstance().getPlayerList().get(i).getOwnedProperties().toString()
                        .replaceAll("\\[", "")
                        .replaceAll("]", ""))

                .append("<br/><span style=\"color:red\">Owned Utilities: </span>")
                .append(MonopolyGameController.getInstance().getPlayerList().get(i).getOwnedUtilities().toString()
                        .replaceAll("\\[", "")
                        .replaceAll("]", ""))

                .append("<br/><span style=\"color:red\">Owned Railroads: </span>")
                .append(MonopolyGameController.getInstance().getPlayerList().get(i).getOwnedRailroads().toString()
                        .replaceAll("\\[", "")
                        .replaceAll("]", ""))

                .append("</div></body></html>");
        playerInfo.setText(sb.toString());
    }
}
