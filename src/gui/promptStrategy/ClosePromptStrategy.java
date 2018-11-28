package gui.promptStrategy;

import javax.swing.*;

public class ClosePromptStrategy implements PromptStrategy {
    @Override
    public void show() {
        JOptionPane.showMessageDialog(null, "Username already exists!",
                    "Error", JOptionPane.WARNING_MESSAGE);
    }
}
