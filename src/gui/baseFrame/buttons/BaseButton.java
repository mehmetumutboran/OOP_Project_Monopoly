package gui.baseFrame.buttons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class BaseButton extends JButton implements MouseListener, ActionListener {

    private Font bigFont;
    private Font smallFont;

    private Color bigColor;
    private Color smallColor;

    public BaseButton(String text) {
        super(text);
        bigFont = new Font("Comic Sans",Font.BOLD,42);
        smallFont = new Font("Comic Sans",Font.BOLD,32);
        smallColor = new Color(44, 194, 224);
        bigColor = new Color(255, 90, 94);
        this.setFont(smallFont);
        this.setForeground(smallColor);
        this.setOpaque(false);
        this.addActionListener(this);
        this.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.setFont(bigFont);
        this.setForeground(bigColor);
        revalidate();
        repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setFont(smallFont);
        this.setForeground(smallColor);
        revalidate();
        repaint();
    }
}
