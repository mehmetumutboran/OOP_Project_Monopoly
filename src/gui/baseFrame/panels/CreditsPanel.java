package gui.baseFrame.panels;

import gui.baseFrame.buttons.multiplayerButtons.BackButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CreditsPanel extends JPanel {
    private JLabel titleLabel;
    private JLabel contributorsLabel;
    private RecentUpdatesPanel recentUpdates;
    private JLabel recentUpdatesLabel;
    private BackButton backButton;
    private JLabel backgroundLabel;
    private BufferedImage image;
    private Image img;

    private int width;
    private int height;
    private String version;
    private JLabel versionLabel;

    private final int BUTTON_WIDTH = 300;
    private final int BUTTON_HEIGHT = 50;

    public CreditsPanel(int width, int height, String version){
        this.width = width;
        this.height = height;
        this.version = version;

        try {
            if (System.getProperty("os.name").startsWith("Windows")) {
                image = ImageIO.read(new File("res\\Monopoly Background 8.jpg"));
            } else {
                image = ImageIO.read(new File("res/Monopoly Background 8.jpg"));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        img = new ImageIcon(image).getImage();

        initGUI();
    }

    private void initGUI() {
        this.setLayout(null);
        this.setBackground(Color.GRAY);

        versionLabel = new JLabel(version);
        versionLabel.setHorizontalAlignment(JLabel.CENTER);
        versionLabel.setVerticalAlignment(JLabel.CENTER);
        versionLabel.setFont(new Font("Serif", Font.BOLD, 20));
        versionLabel.setBounds((this.width - BUTTON_WIDTH) / 2,
                (this.height - 14 * BUTTON_HEIGHT) / 2, BUTTON_WIDTH, BUTTON_HEIGHT);
        versionLabel.setOpaque(false);

        titleLabel = new JLabel("Contributors");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setVerticalAlignment(JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 25));
        titleLabel.setBounds((this.width - BUTTON_WIDTH) / 2,
                (this.height - 12 * BUTTON_HEIGHT) / 2, BUTTON_WIDTH, BUTTON_HEIGHT);


        contributorsLabel = new JLabel();
        contributorsLabel.setText("<HTML> Agabey Alioglu"+
                "<BR> Enes Kosar"+
                "<BR> Furkan Yakal"+
                "<BR> Mehmet Taha Bastug"+
                "<BR> Mehmet Umut Boran </HTML>");
        contributorsLabel.setHorizontalAlignment(JLabel.CENTER);
        contributorsLabel.setVerticalAlignment(JLabel.CENTER);
        contributorsLabel.setFont(new Font("Serif", Font.BOLD, 20));
        contributorsLabel.setBounds((this.width - BUTTON_WIDTH) / 2,
                (this.height - 10 * BUTTON_HEIGHT) / 2, BUTTON_WIDTH, 3*BUTTON_HEIGHT);

        recentUpdatesLabel = new JLabel("Recent Changes");
        recentUpdatesLabel.setHorizontalAlignment(JLabel.CENTER);
        recentUpdatesLabel.setVerticalAlignment(JLabel.CENTER);
        recentUpdatesLabel.setFont(new Font("Serif", Font.BOLD, 25));
        recentUpdatesLabel.setBounds((this.width - BUTTON_WIDTH) / 2,
                (this.height - 5 * BUTTON_HEIGHT) / 2, BUTTON_WIDTH, 3*BUTTON_HEIGHT);

        recentUpdates = new RecentUpdatesPanel(width, height);
        recentUpdates.setBounds((this.width - BUTTON_WIDTH) / 2,
                (this.height - BUTTON_HEIGHT) / 2, BUTTON_WIDTH, 5*BUTTON_HEIGHT);

        backButton = new BackButton("Back");
        backButton.setBounds((this.width - BUTTON_WIDTH) / 2,
                (this.height - (-10) * BUTTON_HEIGHT) / 2, BUTTON_WIDTH, BUTTON_HEIGHT);
        backButton.setBackground(Color.lightGray);

        backgroundLabel = new JLabel(new ImageIcon(img));
        backgroundLabel.setBounds(0, 0, width, height);
        backgroundLabel.setOpaque(true);

        this.add(titleLabel);
        this.add(backButton);
        this.add(versionLabel);
        this.add(contributorsLabel);
        this.add(recentUpdates);
        this.add(recentUpdatesLabel);
        this.add(backgroundLabel);
    }

}
