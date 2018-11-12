package gui.baseFrame.buttons.lobbyButtons;

import domain.controller.ConnectGameHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddBotButton extends JButton implements ActionListener {
    private static int count = 0;

    public AddBotButton(String text) {
        super(text);
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        ConnectGameHandler.getInstance().connectBot("Bot " + count++);
    }
}
