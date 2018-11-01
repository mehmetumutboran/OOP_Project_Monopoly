package gui.baseFrame;

import gui.baseFrame.buttons.initialScreenButons.ExitButton;
import gui.baseFrame.buttons.initialScreenButons.MultiplayerButton;
import gui.baseFrame.buttons.initialScreenButons.SinglePlayerButton;

import javax.swing.*;

public class InitialScreenPanel extends JPanel {
    private SinglePlayerButton singlePlayerButton;
    private MultiplayerButton multiPlayerButton;
    private ExitButton exitButton;

    public InitialScreenPanel() {
        singlePlayerButton = new SinglePlayerButton("Single Player");
        multiPlayerButton = new MultiplayerButton("Multiplayer");
        exitButton = new ExitButton("Exit");


    }
}
