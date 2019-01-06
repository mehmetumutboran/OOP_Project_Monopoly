package gui.baseFrame.panels;

import domain.client.UIUpdater;
import domain.server.listeners.AnimationPauseListener;
import domain.server.listeners.DiceRolledListener;
import domain.server.listeners.TokenMovementListener;
import domain.server.listeners.TokenStarterListener;
import gui.Animator.Animator;
import gui.baseFrame.DiceLabel;
import gui.baseFrame.SquareLabel;
import gui.baseFrame.TokenFactory;
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

public class GamePanel extends JPanel implements TokenMovementListener, DiceRolledListener, TokenStarterListener, AnimationPauseListener {

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
    public static Animator animator;


    public GamePanel(int width, int height) {
        this.width = width;
        this.height = height;
        this.setSize(width, height / 10);
        this.setLayout(new BorderLayout());
        this.setBackground(Color.white);
        UIUpdater.getInstance().addDiceRolledListener(this);
        UIUpdater.getInstance().addTokenStarterListener(this);
        UIUpdater.getInstance().addAnimationPauseListener(this);
        tokenlist = new ArrayList<>();
        diceList = new ArrayList<>(3);
        animator = new Animator(this, "");

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
        DiceLabel die1 = new DiceLabel();
        DiceLabel die2 = new DiceLabel();
        DiceLabel die3 = new DiceLabel();
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
            SquareLabel squareLabel = new SquareLabel((i == 0 || i == 14 || i == 28 || i == 42) ? 'B' : 'S', new int[]{0, i});
            squareLabels.add(squareLabel);
            this.add(squareLabel);
        }
        for (int i = 0; i < 40; i++) {
            SquareLabel squareLabel = new SquareLabel((i == 0 || i == 10 || i == 20 || i == 30) ? 'B' : 'S', new int[]{1, i});
            squareLabels.add(squareLabel);
            this.add(squareLabel);
        }
        for (int i = 0; i < 24; i++) {
            SquareLabel squareLabel = new SquareLabel((i == 0 || i == 6 || i == 12 || i == 18) ? 'B' : 'S', new int[]{2, i});
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
        for (int i = 0; i < tokenlist.size(); i++) {
            tokenlist.get(i).setCoordinates(this.getWidth(), this.getHeight());
            tokenlist.get(i).draw(G);
        }
        for (int i = 0; i < diceList.size(); i++) {
            diceList.get(i).draw(G, i, this.getWidth(), this.getHeight());
        }
        for (int i = 0; i < squareLabels.size(); i++) {
            squareLabels.get(i).draw(this.getWidth(), this.getHeight());
        }
    }

    @Override
    public void onTokenMovement(String pName, int x, int y) {
        for (TokenLabel t : tokenlist) {
            if (t.getOwner().equals(pName)) t.setNewLoc(new int[]{x, y});
        }
        animator = new Animator(this, pName);
        new Thread(animator, "Animator").start();
    }

    @Override
    public void onDiceRolledEvent(int[] faces) {
        for (int i = 0; i < diceList.size(); i++) {
            diceList.get(i).setImage(faces[i]);
            this.revalidate();
            this.repaint();
        }
    }

    @Override
    public void onTokenStarterEvent(ArrayList<String> pList) {
        for (int i = 0; i < pList.size(); i++) {
            String message = pList.get(i);
            TokenLabel tl = TokenFactory.getInstance().getNewToken(message);
            tl.setOpaque(false);
            this.add(tl);
            tokenlist.add(tl);
            tl.setCoordinates(width, height);
            this.revalidate();
            this.repaint();
        }
    }

    public void tokenDraw(Graphics G, String tokName) {
        for (TokenLabel aTokenlist : tokenlist) {
            if (aTokenlist.getOwner().equals(tokName)) {
                aTokenlist.setCoordinates(this.getWidth(), this.getHeight());
                aTokenlist.draw(G);
            }
        }
    }

    public void setPath(String tokName) {
        for (TokenLabel aTokenlist : tokenlist) {
            if (aTokenlist.getOwner().equals(tokName)) {
                aTokenlist.setPath();
            }
        }
    }

    @Override
    public void onAnimationPausedEvent(boolean b) {
//            if (b) {
//                try {
//                    animator.wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            } else {
//                animator.notify();
//            }
    }
}

