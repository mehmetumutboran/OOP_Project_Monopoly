package gui.controlDisplay.panels;

import domain.server.board.Board;
import domain.util.GameInfo;

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
                .append("<div style=\"text-align:left; font-size:9px;\">")

                .append("<br/><span style=\"color:red\">Name: </span>")
                .append(GameInfo.getInstance().getNameFromIndex(i))

                .append("<br/><span style=\"color:red\">Location: </span>")
                .append(Board.getInstance().getSquare(GameInfo.getInstance().getLayerFromIndex(i), GameInfo.getInstance().getLocationFromIndex(i)))

                .append("<br/><span style=\"color:red\">Money: </span>")
                .append(GameInfo.getInstance().getBalanceFromIndex(i))

                .append("<br/><span style=\"color:red\">Owned Properties: </span><br/>");
        StringBuilder ownedProperties = new StringBuilder();
        for (int j = 0; j < GameInfo.getInstance().getPropertyFromIndex(i).size(); j++) {
            ownedProperties.append(GameInfo.getInstance().getPropertyFromIndex(i).get(j));
            switch (GameInfo.getInstance().getPropertyFromIndex(i).get(j).getUpgradeLevel()) {
                case 5:
                    ownedProperties.append("(Hotel)");
                    break;
                case 6:
                    ownedProperties.append("(SkyScraper)");
                    break;
                case 0:
                    break;
                default:
                    ownedProperties.append("(").append(GameInfo.getInstance().getPropertyFromIndex(i).get(j).getUpgradeLevel()).append(" Houses)");
            }
            ownedProperties.append("<br/>");
        }

        sb.append(ownedProperties);

        sb.append("<span style=\"color:red\">Owned Utilities: </span>")
                .append(GameInfo.getInstance().getUtilityFromIndex(i)
                        .replaceAll("\\[", "")
                        .replaceAll("]", ""))

                .append("<br/><span style=\"color:red\">Owned Railroads: </span>")
                .append(GameInfo.getInstance().getRailRoadFromIndex(i)
                        .replaceAll("\\[", "")
                        .replaceAll("]", ""))

                .append("<br/><span style=\"color:red\">Mortgaged Squares: </span>")
                .append(GameInfo.getInstance().getMortgagedFromIndex(i)
                        .replaceAll("\\[", "")
                        .replaceAll("]", ""))

                .append("</div></body></html>");
        playerInfo.setText(sb.toString());
    }
}
