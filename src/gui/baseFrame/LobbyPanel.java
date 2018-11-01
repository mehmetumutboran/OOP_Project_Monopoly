package gui.baseFrame;

import gui.baseFrame.buttons.lobbyButtons.ReadyButton;

import javax.swing.*;

public class LobbyPanel extends JPanel {
    private ReadyButton readyButton;


    public LobbyPanel (){
        readyButton = new ReadyButton("Ready");
    }


}

