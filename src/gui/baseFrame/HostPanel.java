package gui.baseFrame;

import gui.baseFrame.buttons.hostJoinButtons.HostButton;
import gui.baseFrame.buttons.multiplayerButtons.BackButton;

import javax.swing.*;
import java.awt.*;

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

    private final int BUTTON_WIDTH = 300;
    private final int BUTTON_HEIGHT = 50;

    public HostPanel(int width, int height) {
        this.width = width;
        this.height = height;

        initGUI();

        this.setVisible(true);
    }

    private void initButtons() {
        hostButton = new HostButton("Host");
        backButton = new BackButton("Back");

        hostButton.setBounds((this.width - BUTTON_WIDTH) / 2,
                (this.height - (-6) * BUTTON_HEIGHT) / 2, BUTTON_WIDTH, BUTTON_HEIGHT);
        backButton.setBounds((this.width - BUTTON_WIDTH) / 2,
                (this.height - (-8) * BUTTON_HEIGHT) / 2, BUTTON_WIDTH, BUTTON_HEIGHT);

        hostButton.setBackground(Color.gray);
        backButton.setBackground(Color.gray);

        hostButton.setBorderPainted(false);
        backButton.setBorderPainted(false);

        this.add(hostButton);
        this.add(backButton);
    }

    public void initGUI() {
        this.setLayout(null);
        this.setBackground(Color.GRAY);

        title = new JLabel("Host Game");
        title.setBounds(500, 150, 200, 40);

        usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(300, 200, 175, 40);
        usernameField = new JTextField(50);
        usernameField.setBounds(500, 200, 200, 40);

        portLabel = new JLabel("Port");
        portLabel.setBounds(300, 300, 175, 40);
        portField = new JTextField(50);
        portLabel.setBounds(500, 300, 200, 40);

        this.add(title);
        this.add(portField);
        this.add(portLabel);
        this.add(usernameField);
        this.add(usernameLabel);

        initButtons();
    }

}
