package gui.baseFrame.buttons.multiplayerButtons;

import domain.util.GameInfo;
import gui.baseFrame.BaseFrame;
import network.client.clientFacade.ClientFacade;
import network.server.serverFacade.ServerFacade;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Allows player to return previous menu
 */
public class BackButton extends JButton implements ActionListener {
    public BackButton(String text) {
        super(text);
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("Back Button Pressed");
        if (BaseFrame.getStatus().equals("Lobby")) {
            if (GameInfo.getInstance().isMyselfHost())
                ServerFacade.getInstance().shutDown();
            ClientFacade.getInstance().terminate();
        }

        BaseFrame.setStatus("Init");
    }
}
