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

    private static final int OFFSET = 22;
    private static final int MAX_LINES = 20;

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
    public void onMessageChangedEvent() { // Add Log file
        String s = UIUpdater.getInstance().getMessage();
        if (getLineCount() >= MAX_LINES) {
            message.delete(message.lastIndexOf(" - ") - 8, message.lastIndexOf("</div>"));
        }
        message.insert(OFFSET, LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + " - " + s + "<br>");
        messageLabel.setText(message.toString());
    }


    private int getLineCount() {
        return message.toString().split("<br>|<br/>").length - 2;
    }



    public static void main(String[] args) {
        MessagePanel messagePanel = new MessagePanel(10, 10);

        System.out.println(message.toString());
        for (int i = 0; i < 300; i++) {
            if (messagePanel.getLineCount() >= MAX_LINES) {
                message.delete(message.lastIndexOf(" - ") - 8, message.lastIndexOf("</div>"));
            }

            message.insert(OFFSET, LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + " - " + i + "<br>");
            System.out.println(message.toString());
            System.out.println(messagePanel.getLineCount());
        }


    }

}
