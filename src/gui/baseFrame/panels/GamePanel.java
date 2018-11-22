package gui.baseFrame.panels;

import domain.UIUpdater;
import domain.controller.MonopolyGameController;
import domain.listeners.GameStartedListener;
import domain.listeners.TokenMovementListener;
import gui.baseFrame.TokenFactory;
import gui.baseFrame.TokenLabel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GamePanel extends JPanel implements GameStartedListener, TokenMovementListener {

    private int width;
    private int height;
    private static final int VERTEX = 20;

    private BufferedImage image;
    private Image img;
    private ArrayList<TokenLabel> tlist;
    JPanel jPanel;

    public GamePanel(int width, int height) {
        this.width = width;
        this.height = height;
        this.setSize(width, height/10);
        this.setLayout(new BorderLayout());
        this.setBackground(Color.white);
        tlist = new ArrayList<>();

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

        jPanel = new JPanel();
        jPanel.setLayout(null);


        JLabel label = new JLabel();
        label.setBackground(new Color(255, 0, 0, 90));
        label.setOpaque(true);
        label.setVisible(true);
        label.setBounds(50, 50, 1400, 1000);
        label.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("\n\n====================\nFURKAN\n=====================\n\n");
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        jPanel.add(label);



        jPanel.setOpaque(false);
        jPanel.setVisible(true);
        this.add(jPanel);


        UIUpdater.getInstance().addTokenMovementListeners(this);
    }

    @Override
    public void paintComponent(Graphics G) {
        super.paintComponent(G);
        G.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
        for (int i = 0; i < tlist.size(); i++) {
            TokenLabel token = tlist.get(i);
            token.draw(G, i);
        }
    }


    @Override
    public void onGameStartedEvent(ArrayList<String> pcl) {
        for (int i = 0; i < pcl.size(); i++) {
            String message = pcl.get(i);
            int sep = message.indexOf('@');
            System.out.println(sep);
            String pName = message.substring(0, sep);
            String cName = message.substring(sep + 1);
            TokenLabel tl = TokenFactory.getInstance().getNewToken(pName, cName);
            tl.setOpaque(false);
            jPanel.add(tl);
            if (i >= 6) tl.setCoordinates(1070 + (i - 6) * 25, 125);
            else tl.setCoordinates(1070 + i * 25, 150);

            tlist.add(tl);
        }
    }

    @Override
    public void onTokenMovement(String pname, int x, int y) {
        for (TokenLabel t : tlist) {
            if (t.getOwner().equals(pname))
                t.setCoordinates(x, y);
            this.revalidate();
            this.repaint();
        }

    }
}
