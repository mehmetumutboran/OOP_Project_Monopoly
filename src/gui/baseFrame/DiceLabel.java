package gui.baseFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DiceLabel extends JLabel {

    private int x;
    private int y;
    private static int count;
    private BufferedImage img;

    public DiceLabel(){
        this.x = 645 + count*40;
        this.y = 480;
        count++;
        this.setImage(1);
    }

    public void draw(Graphics g, int i) {
        g.drawImage(new ImageIcon(img.getScaledInstance(40, 40, Image.SCALE_SMOOTH)).getImage(), x, y, this);
    }

    public void setImage(int i){
        try {
            switch (i){
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