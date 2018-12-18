package gui.baseFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TokenLabel extends JLabel {

    private String owner;
    private int x;
    private int y;


    public TokenLabel(String owner) {
        super();
        this.owner = owner;
        this.x=1;
        this.y=1;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void draw(Graphics g) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("res/pika.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(new ImageIcon(img.getScaledInstance(30, 30, Image.SCALE_SMOOTH)).getImage(), x, y, this);
        this.setBounds(x,y,30,30);
    }

    public void setCoordinates(int width, int height,int i) {
        if(i>=6){
            this.x = width-(width/17)*4 + (i-6)*this.getWidth();
            this.y = height-(height/17)*13-this.getHeight()*2;
        }else {
            this.x = width - ((width / 17) * 4) + (i * this.getWidth());
            this.y = height-((height/17)*13)-this.getHeight();
        }
    }
}
