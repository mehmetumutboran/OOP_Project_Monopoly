package gui.baseFrame.buttons.multiplayerButtons;

import domain.util.GameInfo;
import gui.baseFrame.BaseFrame;
import gui.baseFrame.buttons.BaseButton;
import network.client.clientFacade.ClientFacade;
import network.server.serverFacade.ServerFacade;

import java.awt.event.ActionEvent;

/**
 * Allows player to return previous menu
 */
public class BackButton extends BaseButton {
    public BackButton(String text) {
        super(text);
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
