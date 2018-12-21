package gui.baseFrame;

import gui.Animator.Drawable;
import gui.Animator.Path;
import gui.Animator.TokenPath;
import gui.baseFrame.panels.GamePanel;

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
    int [] newLoc = {0,1};
    private BufferedImage img = null;
    private static int indexCount;
    private boolean firstSet = true;

    public TokenLabel(String owner) {
        super();
        this.owner = owner;
        point = new Point();

        try {
            img = ImageIO.read(new File("res/pika.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        tokIndex = indexCount;
        indexCount++;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public void draw(Graphics g) {
        if(firstSet){
            setCoordinates(width,height);
            if(tokIndex>=6){
                point.setLocation(width-(width/17)*4 + (tokIndex-6)*this.getWidth()-width/100,height-(height/17)*13-this.getHeight()*2);
            }else {
                point.setLocation(width - ((width / 17) * 4) + (tokIndex * this.getWidth())-width/100,height-((height/17)*13)-this.getHeight());
            }
            firstSet = false;
        }else {
            if (path != null && path.hasMoreSteps()) {
                point = path.nextPosition();
            } else {
                GamePanel.animator.animatorStopped = true;
            }
        }
        g.drawImage(new ImageIcon(img.getScaledInstance(tokenWidth, tokenHeight, Image.SCALE_SMOOTH)).getImage(), (int) point.getX(), (int) point.getY(), null);
        this.setBounds((int) point.getX(), (int) point.getY(),tokenWidth,tokenHeight);
    }

    private Path pathChooser() {
        return new TokenPath(width/14, height/2, width/14*9,height/2,width/14);
    }

    public void setCoordinates(int width, int height) {
        this.width = width;
        this.height = height;
        tokenWidth = width/56;
        tokenHeight = height/40;
        this.setBounds((int) point.getX(), (int) point.getY(),tokenWidth,tokenHeight);
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

    public void setPath() {
        path = pathChooser();
    }
}
