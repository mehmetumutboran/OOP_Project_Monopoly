package gui.baseFrame;


import gui.util.ColorConverter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TokenLabel extends JLabel {

    private String owner;
    private String color;
    private int x;
    private int y;


    public TokenLabel(String owner, String color) {
        super();
        this.owner = owner;
        this.color = color;
        this.setBackground(ColorConverter.getInstance().getColor(this.color));
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void draw(Graphics g, int i) {
        g.setColor(ColorConverter.getInstance().getColor(this.color));
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("res/pika.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(new ImageIcon(img.getScaledInstance(30, 30, Image.SCALE_SMOOTH)).getImage(), x, y, this);
    }

    public void setCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
