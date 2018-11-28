package gui.promptStrategy;

import javax.swing.*;

public class DontChangeColorPromptStrategy implements PromptStrategy {
    @Override
    public void show() {
        JOptionPane.showMessageDialog(null, "This color is already in use!",
                "Error", JOptionPane.WARNING_MESSAGE);
    }
}
