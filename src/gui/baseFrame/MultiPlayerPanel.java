package gui.baseFrame;

import gui.baseFrame.buttons.multiplayerButtons.BackButton;
import gui.baseFrame.buttons.multiplayerButtons.HostGameButton;
import gui.baseFrame.buttons.multiplayerButtons.JoinGameButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MultiPlayerPanel extends JPanel {
    private HostGameButton hostGameButton;
    private JoinGameButton joinGameButton;
    private BackButton backButton;
    private JLabel title;

    private int width;
    private int height;

    private BufferedImage image;
    private JLabel backgroundLabel;


    private final int BUTTON_WIDTH = 300;
    private final int BUTTON_HEIGHT = 50;


    public MultiPlayerPanel(int width, int height) {
        this.width = width;
        this.height = height;

        initGUI();

        try {
            image = ImageIO.read(new File("C:\\Users\\TULPAR\\Desktop\\Universite\\COMP 302\\Group Project Monopoly\\Workspace\\302_2018_project_ByteMe\\src\\gui\\baseFrame\\Monopoly Background 3.jpg"));
        } catch (IOException ex) {
            System.out.println(ex);
        }

        backgroundLabel = new JLabel(new ImageIcon(image));
        this.add(backgroundLabel);
        backgroundLabel.setBounds(0,0,width,height);
        backgroundLabel.setOpaque(true);

        this.setVisible(true);
    }

    private void initButtons() {
        hostGameButton = new HostGameButton("Host");
        joinGameButton = new JoinGameButton("Join");
        backButton = new BackButton("Back");

        hostGameButton.setBounds((this.width - BUTTON_WIDTH) / 2,
                (this.height - 4 * BUTTON_HEIGHT) / 2, BUTTON_WIDTH, BUTTON_HEIGHT);
        joinGameButton.setBounds((this.width - BUTTON_WIDTH) / 2,
                (this.height - BUTTON_HEIGHT) / 2, BUTTON_WIDTH, BUTTON_HEIGHT);
        backButton.setBounds((this.width - BUTTON_WIDTH) / 2,
                (this.height - (-2) * BUTTON_HEIGHT) / 2, BUTTON_WIDTH, BUTTON_HEIGHT);

        hostGameButton.setBackground(Color.lightGray);
        joinGameButton.setBackground(Color.lightGray);
        backButton.setBackground(Color.lightGray);

        hostGameButton.setBorderPainted(false);
        joinGameButton.setBorderPainted(false);
        backButton.setBorderPainted(false);

        this.add(hostGameButton);
        this.add(joinGameButton);
        this.add(backButton);
    }

    public void initGUI() {
        this.setLayout(null);
        this.setBackground(Color.GRAY);

        title = new JLabel("Multiplayer");
        title.setBounds(500, 200, 200, 40);
        this.add(title);

        initButtons();
    }


}
