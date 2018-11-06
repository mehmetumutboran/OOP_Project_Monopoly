package gui.controlDisplay;

import javax.swing.*;
import java.awt.*;

public class MessagePanel extends JPanel {
    private int width;
    private int height;

    public MessagePanel(int width, int height) {
        this.width = width;
        this.height = height;

        this.setPreferredSize(new Dimension(width, height/5));

        setBackground(Color.GRAY);

    }
}
