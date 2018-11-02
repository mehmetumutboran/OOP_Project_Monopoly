package gui.baseFrame;

import gui.baseFrame.buttons.lobbyButtons.ReadyButton;
import gui.baseFrame.buttons.multiplayerButtons.BackButton;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class LobbyPanel extends JPanel {
    private ReadyButton readyButton;
    private BackButton backButton;


    private ArrayList<JLabel> playerLabels;

    private int width;
    private int height;

    private final int BUTTON_WIDTH = 300;
    private final int BUTTON_HEIGHT = 50;

    private final int INITIAL_X = 60;
    private final int INITIAL_Y = 60;

    private final int SQUARE_EDGE = 80;

    public LobbyPanel(int width, int height) {
        this.width = width;
        this.height = height;
        playerLabels = new ArrayList<>();
//        ArrayList<String> playerNames = new ArrayList<>();
//        playerNames.add("    Taha");
//        playerNames.add("    Umut");
//        playerNames.add("    Agabey");
//        playerNames.add("    Furkan");
//        playerNames.add("    Enes");
//        setPlayerLabelList(playerNames);

        initGUI();

        this.setVisible(true);
    }

    private void initButtons() {
        readyButton = new ReadyButton("Ready");
        backButton = new BackButton("Back");

        readyButton.setBounds((this.width - (-2) * BUTTON_WIDTH) / 2,
                (this.height - (-6) * BUTTON_HEIGHT) / 2, BUTTON_WIDTH, BUTTON_HEIGHT);
        backButton.setBounds((this.width - (-2) * BUTTON_WIDTH) / 2,
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
    }

    public void setPlayerLabelList(ArrayList<String> playerList) {
        JLabel temp;
        Random rn = new Random();
        for (String name : playerList) {
            temp = new JLabel(name);
            temp.setBackground(new Color(rn.nextInt(256), rn.nextInt(256), rn.nextInt(256)));
            temp.setOpaque(true);
            playerLabels.add(temp);
        }
    }

}

