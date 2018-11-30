package gui.prompt;

import gui.prompt.promptStrategy.PromptStrategy;

import javax.swing.*;

public class FullPromptStrategy implements PromptStrategy {
    @Override
    public void show() {
        JOptionPane.showMessageDialog(null, "The Server is Full!",
                "Error", JOptionPane.WARNING_MESSAGE);
    }
}
