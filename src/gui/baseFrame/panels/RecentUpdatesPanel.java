package gui.baseFrame.panels;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class RecentUpdatesPanel extends JPanel {
    private int width;
    private int height;
    private String updateNotes;

    private JLabel updateLabel;
    private JScrollPane scrollPane;

    public RecentUpdatesPanel(int width, int height){
        this.width = width;
        this.height = height;

        this.updateNotes = readCommits();
        initGUI();
    }

    private void initGUI() {
        this.setPreferredSize(new Dimension(width, height / 5));
        this.setLayout(new BorderLayout());

        updateLabel = new JLabel(updateNotes);
        this.add(updateLabel);

        scrollPane = new JScrollPane(updateLabel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        this.add(scrollPane);

        this.setBackground(Color.WHITE);
    }

    private String readCommits() {
        StringBuilder sb =new StringBuilder();
        sb.append("<HTML>");
        try {
            BufferedReader bufferedReader;
            if (System.getProperty("os.name").startsWith("Windows")) {
                bufferedReader = new BufferedReader(new FileReader("res\\commit.log"));
            } else {
                bufferedReader = new BufferedReader(new FileReader("res/commit.log"));
            }
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                sb.append("<BR>");
                sb.append(line);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        sb.append("<HTML/>");
        return sb.toString();
    }
}
