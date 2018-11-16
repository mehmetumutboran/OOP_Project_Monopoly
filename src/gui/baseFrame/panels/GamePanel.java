package gui.baseFrame.panels;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel {

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


        img = new ImageIcon(image).getImage();
    }

    @Override
    public void paintComponent(Graphics G) {
        super.paintComponent(G);
        G.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
