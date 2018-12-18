package gui.baseFrame;

import gui.Animator.Drawable;
import gui.Animator.Path;
import gui.Animator.TokenPath;

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
    private int width;
    private int height;
    private int tokIndex;
    private Path path;
    int [] oldLoc = {0,1};
    int [] newLoc;


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
        if(path != null && path.hasMoreSteps()) point = path.nextPosition();
        else{
            path = pathChooser();
            point = path.nextPosition();
        }
        g.drawImage(new ImageIcon(img.getScaledInstance(tokenWidth, tokenHeight, Image.SCALE_SMOOTH)).getImage(), (int) point.getX(), (int) point.getY(), this);
        this.setBounds((int) point.getX(), (int) point.getY(),tokenWidth,tokenHeight);
    }

    private Path pathChooser() {
        return new TokenPath(100, 500, 1400,1000,200);
    }

    public void setCoordinates(int width, int height,int i) {
        this.width = width;
        this.height = height;
        tokenWidth=width/56;
        tokenHeight=height/40;
        tokIndex = i;
        if(i>=6){
            point.setLocation(width-(width/17)*4 + (i-6)*this.getWidth()-width/100,height-(height/17)*13-this.getHeight()*2);
        }else {
            point.setLocation(width - ((width / 17) * 4) + (i * this.getWidth())-width/100,height-((height/17)*13)-this.getHeight());
        }
    }

    public int[] getOldLoc() {
        return oldLoc;
    }

    public void setOldLoc(int[] oldLoc) {
        this.oldLoc = oldLoc;
    }

    public int[] getNewLoc() {
        return newLoc;
    }

    public void setNewLoc(int[] newLoc) {
        this.newLoc = newLoc;
    }
}
