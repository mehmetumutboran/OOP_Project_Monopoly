package gui.controlDisplay.panels;

import domain.client.UIUpdater;
import domain.server.listeners.MessageChangedListener;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MessagePanel extends JPanel implements MessageChangedListener {
    private int width;
    private int height;

    private final int OFFSET = 22;

    private static StringBuilder message;
    private JLabel messageLabel;
    private JScrollPane scrollPane;

    public MessagePanel(int width, int height) {
        this.width = width;
        this.height = height;

        this.setPreferredSize(new Dimension(width, height / 5));
        this.setLayout(new BorderLayout());

        messageLabel = new JLabel();
        this.add(messageLabel);

        scrollPane = new JScrollPane(messageLabel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        message = new StringBuilder("<html><body><div><br/></div></body></html>");


        this.add(scrollPane);

        UIUpdater.getInstance().addMessageChangedListener(this);
        setBackground(Color.WHITE);

    }

    @Override
    public void onMessageChangedEvent() {
        String s = UIUpdater.getInstance().getMessage();
        message.insert(OFFSET, LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + " - " + s + "<br>");
        messageLabel.setText(message.toString());
    }
}
