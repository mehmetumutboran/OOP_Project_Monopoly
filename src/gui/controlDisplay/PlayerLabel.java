package gui.controlDisplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PlayerLabel extends JLabel implements MouseListener {

    public PlayerLabel(String text) {
        super(text);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        this.setOpaque(true);
        this.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        this.setBackground(this.getBackground().brighter());
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
