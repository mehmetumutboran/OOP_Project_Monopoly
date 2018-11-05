package gui.baseFrame;

import domain.MonopolyGameController;
import domain.PlayerListChangedListener;
import gui.baseFrame.buttons.lobbyButtons.ReadyButton;
import gui.baseFrame.buttons.multiplayerButtons.BackButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LobbyPanel extends JPanel implements PlayerListChangedListener {
    private ReadyButton readyButton;
    private BackButton backButton;


    private ArrayList<JLabel> playerLabels;
    private ArrayList<Color> colors = (ArrayList<Color>) Stream.of(Color.white,Color.lightGray,Color.gray,Color.blue,Color.cyan,Color.pink,Color.green,new Color(72,209,204),Color.orange,Color.magenta,Color.yellow,Color.red).collect(Collectors.toList());

    private int width;
    private int height;

    private BufferedImage image;
    private JLabel backgroundLabel;

    private final int BUTTON_WIDTH = 300;
    private final int BUTTON_HEIGHT = 50;

    private final int INITIAL_X = 60;
    private final int INITIAL_Y = 60;

    private final int SQUARE_EDGE = 80;

    public LobbyPanel(int width, int height) {
        this.width = width;
        this.height = height;
        playerLabels = new ArrayList<>();
        MonopolyGameController.getInstance().addPlayerListChangedListener(this);

        initGUI();

        try {
            image = ImageIO.read(new File("src\\gui\\baseFrame\\Monopoly Background.jpg"));
        } catch (IOException ex) {
            System.out.println(ex);
        }

        backgroundLabel = new JLabel(new ImageIcon(image));
        this.add(backgroundLabel);
        backgroundLabel.setBounds(0,0,width,height);
        //backgroundLabel.setOpaque(true);

        this.setVisible(true);
    }

    private void initButtons() {
        readyButton = new ReadyButton("Ready");
        backButton = new BackButton("Back");

        readyButton.setBounds((this.width - (-1) * BUTTON_WIDTH) / 2,
                (this.height - (-5) * BUTTON_HEIGHT) / 2, BUTTON_WIDTH, BUTTON_HEIGHT);
        backButton.setBounds((this.width - (-1) * BUTTON_WIDTH) / 2,
                (this.height - (-8) * BUTTON_HEIGHT) / 2, BUTTON_WIDTH, BUTTON_HEIGHT);

        readyButton.setBackground(Color.gray);
        backButton.setBackground(Color.gray);

        readyButton.setBorderPainted(false);
        backButton.setBorderPainted(false);

        this.add(readyButton);
        this.add(backButton);
    }

    public void initGUI() {
        this.setBackground(Color.GRAY);
        this.setLayout(null);

        initButtons();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < playerLabels.size(); i++) {
            playerLabels.get(i).setBounds(INITIAL_X + i * SQUARE_EDGE, INITIAL_Y, SQUARE_EDGE, SQUARE_EDGE);
            this.add(playerLabels.get(i));
        }
        initGUI();

        this.add(backgroundLabel);
        backgroundLabel.setBounds(0,0,width,height);
        backgroundLabel.setOpaque(false);
    }

    public void setPlayerLabelList(ArrayList<String> playerList) {
        JLabel temp;
        for (String name : playerList) {
            temp = new JLabel(name);
            temp.setBackground(Color.white);
            temp.setOpaque(true);
            if (!playerLabelscontains(name))
                playerLabels.add(temp);
        }
    }

    private boolean playerLabelscontains(String name) {
        for (JLabel pl : playerLabels) {
            if (pl == null) continue;
            else if (pl.getText().equals(name)) return true;
        }
        return false;
    }

    @Override
    public void onPlayerListChangedEvent() {
        setPlayerLabelList(MonopolyGameController.getInstance().getPlayerListName());
    }
}

