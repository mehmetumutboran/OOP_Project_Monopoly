package gui.baseFrame;

import gui.baseFrame.buttons.lobbyButtons.AddBotButton;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BotDifficultyBox extends JComboBox implements ActionListener {

    public BotDifficultyBox() {
        super();
        this.addItem("Easy");
        this.addItem("Medium");
        this.addItem("Hard");
        this.addActionListener(this);
        this.setSelectedItem("Medium");
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        AddBotButton.setDifficulty((String) this.getSelectedItem());
    }

}
