package gui.baseFrame.panels;

import gui.baseFrame.BaseFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel {

    private int width;
    private int height;
    private BaseFrame frame;

    private BufferedImage image;
    private JLabel boardLabel;

    public GamePanel(int width, int height) {
        this.width = width;
        this.height = height;

        this.setLayout(null);
        this.setBackground(Color.white);

        try {
            if (System.getProperty("os.name").startsWith("Windows")) {
                image = ImageIO.read(new File("res\\Monopoly Game Board.png"));
            } else {
                image = ImageIO.read(new File("res/Monopoly Game Board.png"));
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }

        boardLabel = new JLabel(new ImageIcon(image));
        this.add(boardLabel,BorderLayout.NORTH);
        boardLabel.setSize(width,height);
        boardLabel.setOpaque(true);
    }
}
