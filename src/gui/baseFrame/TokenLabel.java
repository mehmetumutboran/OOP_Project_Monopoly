package gui.baseFrame;

import gui.Animator.Drawable;
import gui.Animator.Path;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TokenLabel extends JLabel implements Drawable {

    private String owner;
    private Point point;
    private int tokenWidth;
    private int tokenHeight;
    private Path path;


    public TokenLabel(String owner) {
        super();
        this.owner = owner;
        point = new Point();

    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public void draw(Graphics g) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("res/pika.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(new ImageIcon(img.getScaledInstance(tokenWidth, tokenHeight, Image.SCALE_SMOOTH)).getImage(), (int) point.getX(), (int) point.getY(), this);
        this.setBounds((int) point.getX(), (int) point.getY(),tokenWidth,tokenHeight);
    }

    public void setCoordinates(int width, int height,int i) {
        tokenWidth=width/56;
        tokenHeight=height/40;
        if(i>=6){
            point.setLocation(width-(width/17)*4 + (i-6)*this.getWidth()-width/100,height-(height/17)*13-this.getHeight()*2);
        }else {
            point.setLocation(width - ((width / 17) * 4) + (i * this.getWidth())-width/100,height-((height/17)*13)-this.getHeight());
        }
    }

}
