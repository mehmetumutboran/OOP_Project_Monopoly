package gui.baseFrame.panels;

import domain.client.UIUpdater;
import domain.server.listeners.DiceRolledListener;
import domain.server.listeners.GameStartedListener;
import domain.server.listeners.TokenMovementListener;
import gui.baseFrame.DiceLabel;
import gui.baseFrame.SquareLabel;
import gui.baseFrame.TokenLabel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GamePanel extends JPanel implements GameStartedListener, TokenMovementListener, DiceRolledListener {

    private int width;
    private int height;
    private static final int VERTEX = 20;

    private BufferedImage image;
    private Image img;
    private ArrayList<DiceLabel> diceList;
    private ArrayList<TokenLabel> tokenlist;
    JPanel jPanel;
    private ArrayList<SquareLabel> squareLabels = new ArrayList<>();
    private JLabel j;


    public GamePanel(int width, int height) {
        this.width = width;
        this.height = height;
        this.setSize(width, height / 10);
        this.setLayout(new BorderLayout());
        this.setBackground(Color.white);
        UIUpdater.getInstance().addGameStartedListener(this);
        UIUpdater.getInstance().addDiceRolledListener(this);
        tokenlist = new ArrayList<>();
        diceList = new ArrayList<>(3);

        try {
            if (System.getProperty("os.name").startsWith("Windows")) {
                image = ImageIO.read(new File("res\\Monopoly Game Board.png"));
            } else {
                image = ImageIO.read(new File("res/Monopoly Game Board.png"));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        img = new ImageIcon(image).getImage();

//        jPanel = new JPanel();
//        jPanel.setLayout(null);

        //this.setLayout(null);

        initsquareLabels();
        initDieLabels();
//        SquareLabel label = new SquareLabel();
//        label.setBounds(1240, 7, this.width / 17, this.height / 17);
//
//        jPanel.add(label);
//        jPanel.setOpaque(false);
//        jPanel.setVisible(true);
//        this.add(jPanel);

        TimerTask timerTaskPaint = new TimerTask() {
            @Override
            public void run() {
                repaint();
                //System.out.println("Timer is working!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            }
        };

        Timer timer = new Timer();
//        long delay = 500;

        timer.scheduleAtFixedRate(timerTaskPaint, 0, 100);

        UIUpdater.getInstance().addTokenMovementListeners(this);
    }

    private void initDieLabels() {
        DiceLabel die1 = new DiceLabel(this.getHeight()*10,this.getWidth());
        DiceLabel die2 = new DiceLabel(this.getHeight()*10,this.getWidth());
        DiceLabel die3 = new DiceLabel(this.getHeight()*10,this.getWidth());
        this.add(die1);
        this.add(die2);
        this.add(die3);
        diceList.add(die1);
        diceList.add(die2);
        diceList.add(die3);
    }

    private void initsquareLabels() {

        for (int i = 0; i < 56; i++) {
            //squares in the 0th layer right side
            SquareLabel squareLabel = new SquareLabel((i==0 || i==14 || i == 28||  i == 42) ? 'B' : 'S', new int[]{0, i});
            squareLabels.add(squareLabel);
            this.add(squareLabel);
        }
        for (int i=0; i <40 ; i++){
            SquareLabel squareLabel = new SquareLabel((i==0 || i==10 || i==20||i==30) ? 'B' : 'S', new int[]{1, i});
            squareLabels.add(squareLabel);
            this.add(squareLabel);
        }
        for (int i=0 ; i< 24; i++){
            SquareLabel squareLabel = new SquareLabel((i==0 || i==6 || i==12 || i==18) ? 'B' : 'S', new int[]{2, i});
            squareLabels.add(squareLabel);
            this.add(squareLabel);
        }

//        JLabel upRight = new JLabel();
//        upRight.setBackground(new Color(255, 0, 0, 90));
//        upRight.setOpaque(true);
//        upRight.setVisible(true);
//        upRight.setBounds(1240, 7, 2 * (this.width / 17), 2 * (this.height / 17));
//        jPanel.add(upRight);
//
//        for (int i = 0; i < 13; i++) {
//            SquareLabel j = new SquareLabel();
//
//            j.setBounds(1240, 7 + (2 * (this.height / 17)) + (i * this.height / 17), 2 * (this.width / 17), this.height / 17);
//            jPanel.add(j);
//            squareLabels.add(j);
//        }
        System.out.println(squareLabels.size());
    }

    @Override
    public void paintComponent(Graphics G) {
        super.paintComponent(G);
        G.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
//        for (int i = 0; i < tokenlist.size(); i++) {
//            tokenlist.get(i).draw(G, i);
//        }
        for (int i = 0; i < diceList.size(); i++){
            diceList.get(i).draw(G,i);
        }


        for (int i = 0; i < squareLabels.size(); i++) {
            squareLabels.get(i).draw(this.getWidth(), this.getHeight());
        }
//        repaint();

    }


    @Override
    public void onGameStartedEvent() {
//        for (int i = 0; i < playerListColor.size(); i++) {
//            String message = playerListColor.get(i);
//            int sep = message.indexOf('@');
//            System.out.println(sep);
//            String pName = message.substring(0, sep);
//            String cName = message.substring(sep + 1);
//            TokenLabel tl = TokenFactory.getInstance().getNewToken(pName, cName);
//            tl.setOpaque(false);
//            jPanel.add(tl);
//            if (i >= 6) tl.setCoordinates(1070 + (i - 6) * 25, 125);
//            else tl.setCoordinates(1070 + i * 25, 150);
//
//            tokenlist.add(tl);
//        }
    }

    @Override
    public void onTokenMovement(String pName, int x, int y) {
        int[] coor = indexToCoor(x, y, pName);
        for (TokenLabel t : tokenlist) {
            if (t.getOwner().equals(pName))
                t.setCoordinates(coor[0], coor[1]);
            this.revalidate();
            this.repaint();
        }

    }

    public int[] indexToCoor(int label, int index, String name) {
        int xLoc = 0;
        int yLoc = 0;
        int pIndex = 0;
        int widthZero = this.getWidth();
        int heightZero = this.getHeight();
        int widthOne = 4 * widthZero / 5;
        int heightOne = 3 * heightZero / 4;
        int widthTwo = widthZero / 2;
        int heightTwo = heightZero / 2;
        for (int i = 0; i < tokenlist.size(); i++) {
            if (tokenlist.get(i).getOwner().equals(name)) {
                pIndex = i;
                break;
            }
        }

        if (label == 0) {
            if (index >= 0 && index < 14) {
                if (pIndex > 6) {
                    yLoc = heightZero * 9 / 100 + index * heightZero / 17 + 15; //59
                    xLoc = widthZero - 2 * widthZero / 17 + (pIndex - 6) * 15;
                } else {
                    yLoc = heightZero * 9 / 100 + index * heightZero / 17;
                    xLoc = widthZero - 2 * widthZero / 17 + pIndex * 15;
                }
            } else if (index >= 14 && index < 28) {
                if (pIndex > 6) {
                    yLoc = 905 + (pIndex - 6) * 15;
                    xLoc = (1240 - (index - 14) * 89) + 44 + 15; //14
                } else {
                    yLoc = 905 + pIndex * 15;
                    xLoc = (1240 - (index - 14) * 89) + 44;
                }//14
            } else if (index >= 28 && index < 42) {
                if (pIndex > 6) {
                    yLoc = 905 - (index - 28) * 59 + 15;
                    xLoc = 30 + (pIndex - 6) * 15;
                } else {
                    yLoc = 905 - (index - 28) * 59;
                    xLoc = 30 + pIndex * 15;
                }
            } else if (index >= 42 && index < 56) {
                if (pIndex > 6) {
                    yLoc = 90 - (pIndex - 6) * 15;
                    xLoc = (30 + (index - 42) * 89) + 44 + 15; //42
                } else {
                    yLoc = 90 - pIndex * 15;
                    xLoc = (30 + (index - 42) * 89) + 44; //42
                }
            }
        } else if (label == 1) {
            if (index >= 0 && index < 10) {
                if (pIndex > 6) {
                    yLoc = 210 + index * heightOne / 13 + 15; //heightZero*11/100 + heightOne*9/130
                    xLoc = widthZero - 2 * (widthOne / 13 + widthZero / 17) + (pIndex - 6) * 15;
                } else {
                    yLoc = heightZero * 9 / 100 + heightOne / 13 + index * heightOne / 13;
                    xLoc = widthZero - 2 * (widthOne / 13 + widthZero / 17) + pIndex * 15;
                }
            } else if (index >= 10 && index < 20) {
                if (pIndex > 6) {
                    yLoc = 790 + (pIndex - 6) * 15;
                    xLoc = (1070 - (index - 10) * 78) + 39 + 15; //10
                } else {
                    yLoc = 790 + +pIndex * 15;
                    xLoc = (1070 - (index - 10) * 78) + 39; //10
                }
            } else if (index >= 20 && index < 30) {
                if (pIndex > 6) {
                    yLoc = 790 - (index - 20) * 57 + 15;
                    xLoc = 200 + (pIndex - 6) * 15;
                } else {
                    yLoc = 790 - (index - 20) * 57;
                    xLoc = 200 + pIndex * 15;
                }
            } else if (index >= 30 && index < 40) {
                if (pIndex > 6) {
                    yLoc = 210 - (pIndex - 6) * 15;
                    xLoc = (200 + (index - 30) * 78) + 39 + 15; //30
                } else {
                    yLoc = 210 - pIndex * 15;
                    xLoc = (200 + (index - 30) * 78) + 39; //30
                }
            }
        } else if (label == 2) {
            if (index >= 0 && index < 6) {
                if (pIndex > 6) {
                    yLoc = 325 + index * 58 + 15;
                    xLoc = 905 + (pIndex - 6) * 15;
                } else {
                    yLoc = 325 + index * 58;
                    xLoc = 905 + pIndex * 15;
                }
            } else if (index >= 6 && index < 12) {
                if (pIndex > 6) {
                    yLoc = 675 + (pIndex - 6) * 15;
                    xLoc = (905 - (index - 6) * 75) + 37 + 15; //6
                } else {
                    yLoc = 675 + pIndex * 15;
                    xLoc = (905 - (index - 6) * 75) + 37; //6
                }
            } else if (index >= 12 && index < 18) {
                if (pIndex > 6) {
                    yLoc = 675 - (index - 12) * 58 + 15;
                    xLoc = 370 + (pIndex - 6) * 15;
                } else {
                    yLoc = 675 - (index - 12) * 58;
                    xLoc = 370 + pIndex * 15;
                }
            } else if (index >= 18 && index < 24) {
                if (pIndex > 6) {
                    yLoc = 325 - (pIndex - 6) * 15;
                    xLoc = (370 + (index - 18) * 75) + 37 + 15; //18
                } else {
                    yLoc = 325 - pIndex * 15;
                    xLoc = (370 + (index - 18) * 75) + 37; //18
                }
            }
        }
        int[] coor = new int[2];
        coor[0] = xLoc;
        coor[1] = yLoc;
        return coor;
    }

    @Override
    public void onDiceRolledEvent(int [] faces) {
        for (int i = 0; i < diceList.size(); i++) {
            diceList.get(i).setImage(faces[i]);
            this.revalidate();
            this.repaint();
        }
    }
}

