package gui.baseFrame;

import gui.baseFrame.buttons.hostJoinButtons.JoinButton;
import gui.baseFrame.buttons.multiplayerButtons.BackButton;

import javax.swing.*;
import java.awt.*;

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

    private final int BUTTON_WIDTH = 300;
    private final int BUTTON_HEIGHT = 50;

    public JoinPanel(int width, int height) {
        this.width = width;
        this.height = height;

        initGUI();

        this.setVisible(true);
    }

    private void initButtons() {
        joinButton = new JoinButton("Join", userNameField, ipField, portField);
        backButton = new BackButton("Back");
        userNameField = new JTextField();
        ipField = new JTextField();
        portField = new JTextField();
        userNameLabel = new JLabel("Username: ");
        ipLabel = new JLabel("IP: ");
        portLabel = new JLabel("Port: ");

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
        joinButton.setBounds((this.width - BUTTON_WIDTH) / 2,
                (this.height - (-5) * BUTTON_HEIGHT) / 2, BUTTON_WIDTH, BUTTON_HEIGHT);
        backButton.setBounds((this.width - BUTTON_WIDTH) / 2,
                (this.height - (-8) * BUTTON_HEIGHT) / 2, BUTTON_WIDTH, BUTTON_HEIGHT);

        joinButton.setBackground(Color.gray);
        backButton.setBackground(Color.gray);

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

        title = new JLabel("Join a Game");
        title.setBounds(500, 200, 200, 40);
        this.add(title);

        initButtons();
    }
}
