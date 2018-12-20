package gui.baseFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DiceLabel extends JLabel {

    private static int count = 0;
    private BufferedImage img;

    public DiceLabel() {
        count++;
        this.setImage(1);
    }

    public void draw(Graphics g, int i, int width, int height) {
        g.drawImage(new ImageIcon(img.getScaledInstance(width / 35, height / 25, Image.SCALE_SMOOTH)).getImage(), width / 2 - width / 25 + i * getWidth(), height / 2 - getHeight() / 2, this);
        this.setBounds(width / 2 - width / 25 + i * getWidth(), height / 2 - getHeight() / 2, width / 35, height / 25);
    }

    public void setImage(int i) {
        try {
            switch (i) {
                case 1:
                    img = ImageIO.read(new File("res/dice1low.jpg"));
                    break;
                case 2:
                    img = ImageIO.read(new File("res/dice2low.jpg"));
                    break;
                case 3:
                    img = ImageIO.read(new File("res/dice3low.jpg"));
                    break;
                case 4:
                    img = ImageIO.read(new File("res/dice4low.jpg"));
                    break;
                case 5:
                    img = ImageIO.read(new File("res/dice5low.jpg"));
                    break;
                case 6:
                    img = ImageIO.read(new File("res/dice6low.jpg"));
                    break;
                case 7:
                    img = ImageIO.read(new File("res/MrMonopoly.jpg"));
                    break;
                case 8:
                    img = ImageIO.read(new File("res/Bus.jpg"));
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
