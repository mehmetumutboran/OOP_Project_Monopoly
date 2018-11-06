package gui.controlDisplay;

import domain.controller.GamePlayHandler;
import domain.listeners.MessageChangedListener;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MessagePanel extends JPanel implements MessageChangedListener {
    private int width;
    private int height;

    private final int OFFSET = 17;

    private static StringBuilder message;
    private JLabel messageLabel;

    public MessagePanel(int width, int height) {
        this.width = width;
        this.height = height;

        this.setPreferredSize(new Dimension(width, height / 5));
        message = new StringBuilder("<html><body><div></div></body></html>");
        messageLabel = new JLabel();
        this.add(messageLabel);
        GamePlayHandler.getInstance().addMessageChangedListener(this);
        setBackground(Color.WHITE);

    }

    @Override
    public void onMessageChangedEvent() {
//        String s = GamePlayHandler.getInstance().getMessage();
        String s = "Hello";
        message.insert(OFFSET, LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + " - " + s + "<br>");
        System.out.println(message.toString());
        messageLabel.setText(message.toString());
    }
}
