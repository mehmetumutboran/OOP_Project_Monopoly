package gui.baseFrame;

import gui.baseFrame.buttons.hostJoinButtons.HostButton;
import gui.baseFrame.buttons.multiplayerButtons.BackButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HostPanel extends JPanel {
    private HostButton hostButton;
    private BackButton backButton;

    private JLabel title;

    private JLabel usernameLabel;
    private JLabel portLabel;

    private JTextField usernameField;
    private JTextField portField;

    private int width;
    private int height;

    private BufferedImage image;
    private JLabel backgroundLabel;

    private final int BUTTON_WIDTH = 300;
    private final int BUTTON_HEIGHT = 50;

    public HostPanel(int width, int height) {
        this.width = width;
        this.height = height;

        initGUI();

        try {
            image = ImageIO.read(new File("src\\gui\\baseFrame\\Monopoly Background 5.jpg"));
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
        hostButton = new HostButton("Host", usernameField, portField);
        backButton = new BackButton("Back");

        hostButton.setBounds((this.width - BUTTON_WIDTH) / 2,
                (this.height - (-4) * BUTTON_HEIGHT) / 2, BUTTON_WIDTH, BUTTON_HEIGHT);
        backButton.setBounds((this.width - BUTTON_WIDTH) / 2,
                (this.height - (-8) * BUTTON_HEIGHT) / 2, BUTTON_WIDTH, BUTTON_HEIGHT);

        hostButton.setBackground(Color.lightGray);
        backButton.setBackground(Color.lightGray);

        hostButton.setBorderPainted(false);
        backButton.setBorderPainted(false);

        this.add(hostButton);
        this.add(backButton);
    }

    public void initGUI() {
        this.setLayout(null);
        this.setBackground(Color.GRAY);

        title = new JLabel("Host Game" , SwingConstants.CENTER);
        title.setBounds(440, 150, 200, 40);
        title.setFont(new Font("Serif", Font.BOLD, 30));
        title.setForeground(Color.black);

        usernameLabel = new JLabel("Username");

        usernameLabel.setFont(new Font("Serif", Font.BOLD, 30));
        usernameLabel.setForeground(Color.black);

        usernameLabel.setBounds((this.width - 2 * BUTTON_WIDTH) / 2,
                (this.height - 4 * BUTTON_HEIGHT) / 2, BUTTON_WIDTH, BUTTON_HEIGHT);
        usernameField = new JTextField(50);
        usernameField.setBounds((this.width - BUTTON_WIDTH) / 2,
                (this.height - 4 * BUTTON_HEIGHT) / 2, BUTTON_WIDTH, BUTTON_HEIGHT);

        portLabel = new JLabel("Port");

        portLabel.setFont(new Font("Serif", Font.BOLD, 30));
        portLabel.setForeground(Color.black);

        portLabel.setBounds((this.width - 2 * BUTTON_WIDTH) / 2,
                (this.height - BUTTON_HEIGHT) / 2, BUTTON_WIDTH, BUTTON_HEIGHT);
        portField = new JTextField(50);
        portField.setBounds((this.width - BUTTON_WIDTH) / 2,
                (this.height - BUTTON_HEIGHT) / 2, BUTTON_WIDTH, BUTTON_HEIGHT);

        this.add(title);
        this.add(portField);
        this.add(portLabel);
        this.add(usernameField);
        this.add(usernameLabel);

        initButtons();
    }
}
