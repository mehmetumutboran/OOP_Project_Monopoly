package gui.baseFrame.panels;

import domain.client.UIUpdater;
import domain.server.controller.MonopolyGameController;
import domain.server.listeners.GameStartedListener;
import domain.server.listeners.TokenMovementListener;
import gui.baseFrame.TokenFactory;
import gui.baseFrame.TokenLabel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GamePanel extends JPanel implements GameStartedListener, TokenMovementListener {

    private int width;
    private int height;
    private static final int VERTEX = 20;

    private BufferedImage image;
    private Image img;
    private ArrayList<TokenLabel> tokenlist;
    JPanel jPanel;

    public GamePanel(int width, int height) {
        this.width = width;
        this.height = height;
        this.setSize(width, height/10);
        this.setLayout(new BorderLayout());
        this.setBackground(Color.white);
        tokenlist = new ArrayList<>();

        try {
            if (System.getProperty("os.name").startsWith("Windows")) {
                image = ImageIO.read(new File("res\\Monopoly Game Board.png"));
            } else {
                image = ImageIO.read(new File("res/Monopoly Game Board.png"));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        MonopolyGameController.getInstance().addGameStartedListener(this);
        img = new ImageIcon(image).getImage();

        jPanel = new JPanel();
        jPanel.setLayout(null);


//        JLabel label = new JLabel();
//        label.setBackground(new Color(255, 0, 0, 90));
//        label.setOpaque(true);
//        label.setVisible(true);
//        label.setBounds(50, 50, 1400, 1000);
//        label.addMouseListener(new MouseListener() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                System.out.println("\n\n====================\nFURKAN\n=====================\n\n");
//            }
//
//            @Override
//            public void mousePressed(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseReleased(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseEntered(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//
//            }
//        });
//
//        jPanel.add(label);



        jPanel.setOpaque(false);
        jPanel.setVisible(true);
        this.add(jPanel);


        UIUpdater.getInstance().addTokenMovementListeners(this);
    }

    @Override
    public void paintComponent(Graphics G) {
        super.paintComponent(G);
        G.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
        for (int i = 0; i < tokenlist.size(); i++) {
            TokenLabel token = tokenlist.get(i);
            token.draw(G, i);
        }
    }


    @Override
    public void onGameStartedEvent(ArrayList<String> playerListName,ArrayList<String> playerListColor) {
        for (int i = 0; i < playerListColor.size(); i++) {
            String message = playerListColor.get(i);
            int sep = message.indexOf('@');
            System.out.println(sep);
            String pName = message.substring(0, sep);
            String cName = message.substring(sep + 1);
            TokenLabel tl = TokenFactory.getInstance().getNewToken(pName, cName);
            tl.setOpaque(false);
            jPanel.add(tl);
            if (i >= 6) tl.setCoordinates(1070 + (i - 6) * 25, 125);
            else tl.setCoordinates(1070 + i * 25, 150);

            tokenlist.add(tl);
        }
    }

    @Override
    public void onTokenMovement(String pName, int x, int y) {
        int [] coor = indexToCoor(x,y,pName);
        for (TokenLabel t : tokenlist) {
            if (t.getOwner().equals(pName))
                t.setCoordinates(coor[0], coor[1]);
            this.revalidate();
            this.repaint();
        }

    }

    public int [] indexToCoor(int label,int index, String name){
        int xLoc = 0;
        int yLoc = 0;
        int pIndex = 0;
        int widthZero = this.getWidth();
        int heightZero = this.getHeight();
        int widthOne = 4*widthZero/5;
        int heightOne = 3*heightZero/4;
        int widthTwo = widthZero/2;
        int heightTwo = heightZero/2;
        for (int i = 0; i < tokenlist.size(); i++) {
            if(tokenlist.get(i).getOwner().equals(name)){
                pIndex = i;
                break;
            }
        }

        if (label == 0) {
            if (index >= 0 && index < 14) {
                if (pIndex > 6) {
                    yLoc = heightZero*9/100 + index * heightZero/17 + 15; //59
                    xLoc = widthZero - 2*widthZero/17 + (pIndex - 6) * 15;
                } else {
                    yLoc = heightZero*9/100 + index * heightZero/17;
                    xLoc = widthZero - 2*widthZero/17 + pIndex * 15;
                }
            } else if (index>= 14 && index < 28) {
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
                    yLoc = 210 + index * heightOne/13 + 15; //heightZero*11/100 + heightOne*9/130
                    xLoc = widthZero - 2*(widthOne/13+widthZero/17) + (pIndex - 6) * 15;
                } else {
                    yLoc = heightZero*9/100 + heightOne/13 + index * heightOne/13;
                    xLoc = widthZero - 2*(widthOne/13+widthZero/17) + pIndex * 15;
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
        int [] coor = new int[2];
        coor[0] = xLoc;
        coor[1] = yLoc;
        return coor;
    }
}
