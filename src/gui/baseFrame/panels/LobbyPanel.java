package gui.baseFrame.panels;

import domain.controller.MonopolyGameController;
import domain.listeners.GameStartedListener;
import domain.listeners.PlayerListChangedListener;
import gui.ColorConverter;
import gui.baseFrame.BaseFrame;
import gui.baseFrame.ColorBox;
import gui.baseFrame.buttons.lobbyButtons.AddBotButton;
import gui.baseFrame.buttons.lobbyButtons.ReadyButton;
import gui.baseFrame.buttons.lobbyButtons.StartButton;
import gui.baseFrame.buttons.multiplayerButtons.BackButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LobbyPanel extends JPanel implements PlayerListChangedListener, GameStartedListener {
    private ReadyButton readyButton;
    private BackButton backButton;
    private StartButton startButton;
    private ColorBox colorBox;
    private AddBotButton addBotButton;
    private boolean isHost;

    private ArrayList<JLabel> playerLabels;

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
        isHost = false;
        this.width = width;
        this.height = height;
        playerLabels = new ArrayList<>();
        MonopolyGameController.getInstance().addPlayerListChangedListener(this);
        MonopolyGameController.getInstance().addGameStartedListener(this);
        initGUI();
        try {
            if (System.getProperty("os.name").startsWith("Windows")) {
                image = ImageIO.read(new File("res\\Monopoly Background 7.jpg"));
            } else {
                image = ImageIO.read(new File("res/Monopoly Background 7.jpg"));
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }

        backgroundLabel = new JLabel(new ImageIcon(image));
        this.add(backgroundLabel);
        backgroundLabel.setBounds(0, 0, width, height);
        //backgroundLabel.setOpaque(true);

        this.setVisible(true);
    }


    private void initButtons() {
        readyButton = new ReadyButton();
        backButton = new BackButton("Back");
        startButton = new StartButton("Start");
        addBotButton = new AddBotButton("Add Bot");
        colorBox = new ColorBox();

        readyButton.setBounds((this.width - (-1) * BUTTON_WIDTH) / 2,
                (this.height - (-5) * BUTTON_HEIGHT) / 2, BUTTON_WIDTH, BUTTON_HEIGHT);
        backButton.setBounds((this.width - (-1) * BUTTON_WIDTH) / 2,
                (this.height - (-8) * BUTTON_HEIGHT) / 2, BUTTON_WIDTH, BUTTON_HEIGHT);
        colorBox.setBounds((this.width - (-1) * BUTTON_WIDTH) / 2,
                (this.height - 3 * BUTTON_HEIGHT) / 2, BUTTON_WIDTH, BUTTON_HEIGHT);
        addBotButton.setBounds((this.width - (-1) * BUTTON_WIDTH) / 2,
                (this.height - (-2) * BUTTON_HEIGHT) / 2, BUTTON_WIDTH, BUTTON_HEIGHT);

        backButton.setBackground(Color.gray);
        startButton.setBackground(Color.gray);
        addBotButton.setBackground(Color.gray);

        readyButton.setBorderPainted(false);
        backButton.setBorderPainted(false);
        startButton.setBorderPainted(false);
        addBotButton.setBorderPainted(false);
        this.add(backButton);
        this.add(colorBox);
        this.add(addBotButton);
    }

    public void initGUI() {
        this.setBackground(Color.GRAY);
        this.setLayout(null);

        initButtons();
    }

    public synchronized void setHost(boolean b) {
        isHost = b;
        if (getHost()) {
            readyButton.setVisible(false);
            readyButton.setEnabled(false);
            startButton.setBounds((this.width - (-1) * BUTTON_WIDTH) / 2,
                    (this.height - (-5) * BUTTON_HEIGHT) / 2, BUTTON_WIDTH, BUTTON_HEIGHT);
            this.add(startButton);
            this.remove(readyButton);
        } else {
            startButton.setEnabled(false);
            startButton.setVisible(false);
            this.add(readyButton);
            this.remove(startButton);
        }
    }

    public synchronized boolean getHost() {
        return isHost;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < playerLabels.size(); i++) {
            playerLabels.get(i).setBounds(INITIAL_X + i * SQUARE_EDGE, INITIAL_Y, SQUARE_EDGE, SQUARE_EDGE);
            this.add(playerLabels.get(i));
        }
        this.add(backgroundLabel);
        backgroundLabel.setBounds(0, 0, width, height);
        backgroundLabel.setOpaque(false);

    }

    public void setPlayerLabelList(ArrayList<ArrayList<String>> playerAttributes) {
        for (ArrayList<String> player : playerAttributes) {
            getPlayer(player).setText("<HTML>" + player.get(0) + "<BR>" + player.get(2) + "</HTML>");
            getPlayer(player).setBackground(ColorConverter.getInstance().getColorMap().get(player.get(1)));
        }
        repaint();
    }

    private JLabel getPlayer(ArrayList<String> list) {
        for (int i = 0; i < playerLabels.size(); i++) {
            if (playerLabels.get(i) == null) continue;
            else if (playerLabels.get(i).getText().contains(list.get(0))) {
                return playerLabels.get(i);
            }
        }
        JLabel temp = new JLabel(list.get(0));
        temp.setBackground(ColorConverter.getInstance().getColorMap().get(list.get(1)));
        temp.setOpaque(true);
        this.playerLabels.add(temp);
        return getPlayer(list);
    }

    @Override
    public void onPlayerListChangedEvent() {
        setPlayerLabelList(MonopolyGameController.getInstance().getPlayerConnectAttributes());
    }

    @Override
    public void onGameStartedEvent(){
        BaseFrame.setStatus("Game");
    }
}

