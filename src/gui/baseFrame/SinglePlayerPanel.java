package gui.baseFrame;

import gui.baseFrame.buttons.multiplayerButtons.BackButton;
import gui.baseFrame.buttons.singlePlayerButtons.EnterButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SinglePlayerPanel extends JPanel {
    private EnterButton enterButton;
    private BackButton backButton;
    private JTextField userNameField;
    private JLabel userNameLabel;
    private JLabel title;

    private int width;
    private int height;

    private BufferedImage image;
    private JLabel backgroundLabel;

    private final int BUTTON_WIDTH = 300;
    private final int BUTTON_HEIGHT = 50;


    public SinglePlayerPanel(int width, int height) {
        this.width = width;
        this.height = height;


        initGUI();

        try {
            image = ImageIO.read(new File("C:\\Users\\TULPAR\\Desktop\\Universite\\COMP 302\\Group Project Monopoly\\Workspace\\302_2018_project_ByteMe\\src\\gui\\baseFrame\\Monopoly Background.jpg"));
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
        enterButton = new EnterButton("Enter");
        backButton = new BackButton("Back");
        userNameField = new JTextField();
        userNameLabel = new JLabel("Username");

        userNameField.setBounds((this.width - BUTTON_WIDTH) / 2,
                (this.height - 4 * BUTTON_HEIGHT) / 2, BUTTON_WIDTH, BUTTON_HEIGHT);
        userNameLabel.setBounds((this.width - 2 * BUTTON_WIDTH) / 2,
                (this.height - 4 * BUTTON_HEIGHT) / 2, BUTTON_WIDTH, BUTTON_HEIGHT);

        enterButton.setBounds((this.width - BUTTON_WIDTH) / 2,
                (this.height - BUTTON_HEIGHT) / 2, BUTTON_WIDTH, BUTTON_HEIGHT);
        backButton.setBounds((this.width - BUTTON_WIDTH) / 2,
                (this.height + 2 * BUTTON_HEIGHT) / 2, BUTTON_WIDTH, BUTTON_HEIGHT);

        enterButton.setBackground(Color.gray);
        backButton.setBackground(Color.gray);

        enterButton.setBorderPainted(false);
        backButton.setBorderPainted(false);

        this.add(enterButton);
        this.add(backButton);
        this.add(userNameField);
        this.add(userNameLabel);
    }

    public void initGUI() {
        this.setLayout(null);
        this.setBackground(Color.GRAY);

        title = new JLabel("Single Player");
        title.setBounds(500, 200, 200, 40);
        this.add(title);

        initButtons();
    }

}
