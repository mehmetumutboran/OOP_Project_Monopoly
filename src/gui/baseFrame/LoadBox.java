package gui.baseFrame;

import domain.util.LoadGameHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadBox extends JComboBox implements ActionListener {
    public LoadBox() {
        this.addActionListener(this);
        initComponents();
    }

    private void initComponents() {
        this.addItem("New Game");
        String[] files = LoadGameHandler.getInstance().getSaveFiles();
        for (String file : files) {
            this.addItem(file);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        LoadGameHandler.getInstance().setLoadFile((String) this.getSelectedItem());
    }
}
