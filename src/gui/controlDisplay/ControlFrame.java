package gui.controlDisplay;

import javax.swing.*;

public class ControlFrame extends JDialog {
    private final int FRAME_WIDTH = 400;
    private final int FRAME_HEIGHT = 720;

    public ControlFrame(JFrame owner) {
        super(owner);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);


        this.setVisible(true);
    }
}
