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
import java.util.HashMap;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LobbyPanel extends JPanel implements PlayerListChangedListener {
    private ReadyButton readyButton;
    private BackButton backButton;
    private ColorBox colorBox;

    private ArrayList<Color> colorList = (ArrayList<Color>) (Stream.of(Color.white,Color.lightGray,Color.gray,Color.blue,Color.cyan,Color.pink,Color.green,
            Color.orange,Color.magenta,Color.yellow,Color.red).collect(Collectors.toList()));

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
        this.width = width;
        this.height = height;
        playerLabels = new ArrayList<>();
        MonopolyGameController.getInstance().addPlayerListChangedListener(this);
        initGUI();

        try {
            if(System.getProperty("os.name").startsWith("Windows")) {
                image = ImageIO.read(new File("src\\gui\\baseFrame\\Monopoly Background.jpg"));
            }else{
                image = ImageIO.read(new File("src/gui/baseFrame/Monopoly Background.jpg"));
            }
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
        colorBox = new ColorBox(colorList);

        readyButton.setBounds((this.width - (-1) * BUTTON_WIDTH) / 2,
                (this.height - (-5) * BUTTON_HEIGHT) / 2, BUTTON_WIDTH, BUTTON_HEIGHT);
        backButton.setBounds((this.width - (-1) * BUTTON_WIDTH) / 2,
                (this.height - (-8) * BUTTON_HEIGHT) / 2, BUTTON_WIDTH, BUTTON_HEIGHT);
        colorBox.setBounds((this.width - (-1) * BUTTON_WIDTH) / 2,
                (this.height - 2 * BUTTON_HEIGHT) / 2, BUTTON_WIDTH, BUTTON_HEIGHT);

        readyButton.setBackground(Color.gray);
        backButton.setBackground(Color.gray);

        readyButton.setBorderPainted(false);
        backButton.setBorderPainted(false);

        this.add(readyButton);
        this.add(backButton);
        this.add(colorBox);
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
            System.out.println("Paint component: "+ playerLabels.get(i).getBackground());
        }
        initGUI();

        this.add(backgroundLabel);
        backgroundLabel.setBounds(0,0,width,height);
        backgroundLabel.setOpaque(false);

        for (JLabel mylabel:playerLabels) {
            System.out.println("After init gui "+mylabel.getBackground());
        }
    }

    public void setPlayerLabelList(ArrayList<ArrayList<String>> playerAttributes) {
        JLabel temp;
        playerLabels = new ArrayList<>();
        for (ArrayList<String> player : playerAttributes) {
            temp = new JLabel("<HTML>"+player.get(0)+"<BR>"+player.get(2)+"</HTML>");
            temp.setBackground(colorBox.getColorMap().get(player.get(1)));
            System.out.println("player attribute: "+temp.getBackground());
            // TODO use readiness
            temp.setOpaque(true);
            playerLabels.add(temp);
        }
        repaint();
        for (JLabel mylabel:playerLabels) {
            System.out.println("Mylabel "+mylabel.getBackground());
        }

        //System.out.println(playerAttributes.isEmpty());
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
        //setPlayerLabelList(MonopolyGameController.getInstance().getPlayerListName());
        setPlayerLabelList(MonopolyGameController.getInstance().getPlayerConnectAttributes());
        System.out.println(MonopolyGameController.getInstance().getPlayerConnectAttributes());
        //colorBox.setPlayerLabel(playerLabels.get(0));
    }
}

