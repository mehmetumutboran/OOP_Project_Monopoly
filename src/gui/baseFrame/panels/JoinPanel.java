package gui.baseFrame.panels;

import gui.baseFrame.buttons.hostJoinButtons.JoinButton;
import gui.baseFrame.buttons.multiplayerButtons.BackButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class JoinPanel extends JPanel {
    private JoinButton joinButton;
    private BackButton backButton;
    private JTextField userNameField;
    private JTextField ipField;
    private JTextField portField;
    private JLabel userNameLabel;
    private JLabel ipLabel;
    private JLabel portLabel;
    private JLabel title;

    private int width;
    private int height;

    private BufferedImage image;
    private JLabel backgroundLabel;

    private final int BUTTON_WIDTH = 300;
    private final int BUTTON_HEIGHT = 50;

    public JoinPanel(int width, int height) {
        this.width = width;
        this.height = height;

        initGUI();

        try {
            if (System.getProperty("os.name").startsWith("Windows")) {
                image = ImageIO.read(new File("res\\Monopoly Background 6.jpg"));
            } else {
                image = ImageIO.read(new File("res/Monopoly Background 6.jpg"));

            }
        } catch (IOException ex) {
            System.out.println(ex);
        }

        backgroundLabel = new JLabel(new ImageIcon(image));
        this.add(backgroundLabel);
        backgroundLabel.setBounds(0, 0, width, height);
        backgroundLabel.setOpaque(true);

        this.setVisible(true);
    }

    private void initButtons() {
        joinButton = new JoinButton("Join", userNameField, ipField, portField);
        backButton = new BackButton("Back");

        joinButton.setBounds((this.width - BUTTON_WIDTH) / 2,
                (this.height - (-5) * BUTTON_HEIGHT) / 2, BUTTON_WIDTH, BUTTON_HEIGHT);
        backButton.setBounds((this.width - BUTTON_WIDTH) / 2,
                (this.height - (-8) * BUTTON_HEIGHT) / 2, BUTTON_WIDTH, BUTTON_HEIGHT);

        joinButton.setBackground(Color.lightGray);
        backButton.setBackground(Color.lightGray);

        joinButton.setBorderPainted(false);
        backButton.setBorderPainted(false);


        this.add(joinButton);
        this.add(backButton);
        this.add(userNameField);
        this.add(ipField);
        this.add(portField);
        this.add(userNameLabel);
        this.add(ipLabel);
        this.add(portLabel);
    }

    public void initGUI() {
        this.setLayout(null);
        this.setBackground(Color.GRAY);

        title = new JLabel("Join a Game", SwingConstants.CENTER);
        title.setBounds(440, 200, 200, 40);
        title.setFont(new Font("Serif", Font.BOLD, 30));
        title.setForeground(Color.black);
        this.add(title);

        userNameField = new JTextField();
        ipField = new JTextField();
        portField = new JTextField();
        userNameLabel = new JLabel("Username: ");
        ipLabel = new JLabel("IP: ");
        portLabel = new JLabel("Port: ");

        userNameLabel.setFont(new Font("Serif", Font.BOLD, 30));
        userNameLabel.setForeground(Color.black);

        portLabel.setFont(new Font("Serif", Font.BOLD, 30));
        portLabel.setForeground(Color.black);

        ipLabel.setFont(new Font("Serif", Font.BOLD, 30));
        ipLabel.setForeground(Color.black);

        userNameField.setBounds((this.width - BUTTON_WIDTH) / 2,
                (this.height - 4 * BUTTON_HEIGHT) / 2, BUTTON_WIDTH, BUTTON_HEIGHT);
        userNameLabel.setBounds((this.width - 2 * BUTTON_WIDTH) / 2,
                (this.height - 4 * BUTTON_HEIGHT) / 2, BUTTON_WIDTH, BUTTON_HEIGHT);
        ipField.setBounds((this.width - BUTTON_WIDTH) / 2,
                (this.height - BUTTON_HEIGHT) / 2, BUTTON_WIDTH, BUTTON_HEIGHT);
        ipLabel.setBounds((this.width - 2 * BUTTON_WIDTH) / 2,
                (this.height - BUTTON_HEIGHT) / 2, BUTTON_WIDTH, BUTTON_HEIGHT);
        portField.setBounds((this.width - BUTTON_WIDTH) / 2,
                (this.height - (-2) * BUTTON_HEIGHT) / 2, BUTTON_WIDTH, BUTTON_HEIGHT);
        portLabel.setBounds((this.width - 2 * BUTTON_WIDTH) / 2,
                (this.height - (-2) * BUTTON_HEIGHT) / 2, BUTTON_WIDTH, BUTTON_HEIGHT);

        ipField.setText("localhost");
        portField.setText("2222");

        initButtons();
    }
}
