package gui.controlDisplay;

import gui.controlDisplay.panels.ButtonPanel;
import gui.controlDisplay.panels.MessagePanel;
import gui.controlDisplay.panels.PlayerStatusPanel;

import javax.swing.*;
import java.awt.*;

public class ControlFrame extends JDialog {
    private final int FRAME_WIDTH = 560; // 10 + 6*90 + 10
    private final int FRAME_HEIGHT = 720;

    private PlayerStatusPanel playerStatusPanel;
    private MessagePanel messagePanel;
    private ButtonPanel buttonPanel;

    public ControlFrame(JFrame owner) {
        super(owner);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setLayout(new BorderLayout());

        initGUI();

        this.setVisible(false);

    }

    private void initGUI() {
        playerStatusPanel = new PlayerStatusPanel(FRAME_WIDTH, FRAME_HEIGHT);
        messagePanel = new MessagePanel(FRAME_WIDTH, FRAME_HEIGHT);
        buttonPanel = new ButtonPanel(FRAME_WIDTH, FRAME_HEIGHT);

        add(playerStatusPanel, BorderLayout.NORTH);
        add(messagePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void setHost(boolean b) {
        buttonPanel.setHost(b);
    }
}
