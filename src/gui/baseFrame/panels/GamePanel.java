package gui.baseFrame.panels;

import domain.controller.MonopolyGameController;
import domain.listeners.GameStartedListener;
import gui.ColorConverter;
import gui.baseFrame.TokenFactory;
import gui.baseFrame.TokenLabel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GamePanel extends JPanel implements GameStartedListener {

    private int width;
    private int height;

    private BufferedImage image;
    private Image img;

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
            ex.printStackTrace();
        }

        MonopolyGameController.getInstance().addGameStartedListener(this);
        img = new ImageIcon(image).getImage();
    }

    @Override
    public void paintComponent(Graphics G) {
        super.paintComponent(G);
        G.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
    }


    @Override
    public void onGameStartedEvent(ArrayList<String> pcl) {
        for (int i = 0; i < pcl.size(); i++) {
            String message = pcl.get(i);
            int sep = message.charAt('~');
            System.out.println("SUBSTRINGGGGGGGGGGGGGGG: " + sep);
            String pName = message.substring(0,sep);
            String cName = message.substring(sep+1);
            TokenLabel tl = TokenFactory.getInstance().getNewToken(pName,cName);
            tl.setOpaque(true);
            tl.setBounds(1070,150 + i*25,20,20);
            this.add(tl);
        }
    }
}
