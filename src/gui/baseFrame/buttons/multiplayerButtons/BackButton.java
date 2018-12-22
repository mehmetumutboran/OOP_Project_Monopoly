package gui.baseFrame.buttons.multiplayerButtons;

import domain.client.UIUpdater;
import domain.server.listeners.ReadinessChangedListener;
import domain.util.GameInfo;
import gui.baseFrame.BaseFrame;
import gui.baseFrame.buttons.BaseButton;
import network.client.clientFacade.ClientFacade;
import network.server.serverFacade.ServerFacade;

import java.awt.event.ActionEvent;

/**
 * Allows player to return previous menu
 */
public class BackButton extends BaseButton implements ReadinessChangedListener {
    public BackButton(String text) {
        super(text);
        UIUpdater.getInstance().addReadinessChangedListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("Back Button Pressed");
        if (BaseFrame.getInstance().getStatus().equals("Lobby")) {
            if (GameInfo.getInstance().isMyselfHost()) {
                System.out.println("ShutDown INITIATED!!!!!!!!!!!!!!!!!!!!!");
                ServerFacade.getInstance().shutDown();
            }
            ClientFacade.getInstance().terminate();
        }

        BaseFrame.getInstance().setStatus("Init");
        BaseFrame.getInstance().setFrameTitle("");
    }

    @Override
    public void onReadinessChangedEvent(boolean isReady) {
        this.setEnabled(!isReady);
    }
}
