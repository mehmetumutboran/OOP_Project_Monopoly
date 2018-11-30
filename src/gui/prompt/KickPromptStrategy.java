package gui.prompt;

import gui.prompt.promptStrategy.PromptStrategy;

import javax.swing.*;

public class KickPromptStrategy implements PromptStrategy {
    @Override
    public void show() {
        JOptionPane.showMessageDialog(null, "You are kicked!",
                "Error", JOptionPane.WARNING_MESSAGE);
    }
}
