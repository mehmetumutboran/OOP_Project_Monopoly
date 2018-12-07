package gui.controlDisplay;

import gui.controlDisplay.panels.PlayerLabelsPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PlayerLabel extends JLabel implements MouseListener {
    private PlayerLabelsPanel playerLabelsPanel;

    public PlayerLabel(String text, PlayerLabelsPanel panel) {
        super(text);
        playerLabelsPanel = panel;
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        this.setOpaque(true);
        this.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        playerLabelsPanel.getPlayerStatusPanel().setPlayerInfoPanel(this.getText());
        playerLabelsPanel.getPlayerStatusPanel().getCardLayout().next(playerLabelsPanel.getPlayerStatusPanel());
        System.out.println("Player info clicked");
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
}
