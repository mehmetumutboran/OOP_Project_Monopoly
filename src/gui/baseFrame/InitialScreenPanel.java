package gui.baseFrame;

import gui.baseFrame.buttons.initialScreenButons.ExitButton;
import gui.baseFrame.buttons.initialScreenButons.MultiplayerButton;
import gui.baseFrame.buttons.initialScreenButons.SinglePlayerButton;

import javax.swing.*;
import java.awt.*;

public class InitialScreenPanel extends JPanel {
    private SinglePlayerButton singlePlayerButton;
    private MultiplayerButton multiPlayerButton;
    private ExitButton exitButton;

    private int width;
    private int height;


    private final int BUTTON_WIDTH = 300;
    private final int BUTTON_HEIGHT = 50;



    public InitialScreenPanel(int width, int height) {
        this.width = width;
        this.height = height;

        this.setLayout(null);
        this.setBackground(Color.GRAY);

        initButtons();




        this.setVisible(true);
    }

    private void initButtons(){
        singlePlayerButton = new SinglePlayerButton("Single Player");
        multiPlayerButton = new MultiplayerButton("Multiplayer");
        exitButton = new ExitButton("Exit");

        singlePlayerButton.setBounds((this.width-BUTTON_WIDTH)/2,
                (this.height-4*BUTTON_HEIGHT)/2, BUTTON_WIDTH, BUTTON_HEIGHT);
        multiPlayerButton.setBounds((this.width-BUTTON_WIDTH)/2,
                (this.height-BUTTON_HEIGHT)/2, BUTTON_WIDTH, BUTTON_HEIGHT);
        exitButton.setBounds((this.width-BUTTON_WIDTH)/2,
                (this.height-(-2)*BUTTON_HEIGHT)/2, BUTTON_WIDTH, BUTTON_HEIGHT);

        singlePlayerButton.setBackground(Color.gray);
        multiPlayerButton.setBackground(Color.gray);
        exitButton.setBackground(Color.gray);

        singlePlayerButton.setBorderPainted(false);
        multiPlayerButton.setBorderPainted(false);
        exitButton.setBorderPainted(false);

        this.add(singlePlayerButton);
        this.add(multiPlayerButton);
        this.add(exitButton);
    }


}