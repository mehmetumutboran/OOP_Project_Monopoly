package gui.baseFrame;

import domain.client.PlayerActionController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SquareLabel extends JLabel implements MouseListener {
    private char size;
    private final double LR_MARGIN = 0.99142857; //Left right
    private final double UD_MARGIN = 0.993; // up down

    public SquareLabel(char size) {
        this.size = size;
        System.out.println(size + " ");
        this.setBackground(new Color(255, 0, 0, 41));
        this.setOpaque(false);
        this.setVisible(true);
        this.addMouseListener(this);
    }

    public void draw(int width, int height, int i){
//        System.out.println("Boolean" + this.getHeight());
        this.setBounds(
                (int) (width * LR_MARGIN - this.getWidth()),
                (int) (height * (1 - UD_MARGIN) + ((size=='B' ? 2*i : i) * this.getHeight())),
                (int) (2 * (width / 17.0)),
                (int) ((size=='B' ? 2 : 1) * height / 17.0));
        revalidate();
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Label Clicked");
        PlayerActionController.getInstance().upgrade();
        repaint();

    }


    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.setOpaque(true);
        repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setOpaque(false);
        repaint();
    }
}
