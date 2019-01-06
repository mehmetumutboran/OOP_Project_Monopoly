package gui.prompt.promptStrategy;

import javax.swing.*;

public class DontPayRentStrategy implements PromptStrategy {
    @Override
    public void show() {
        JOptionPane.showMessageDialog(null, "You cannot pay rent",
                "Error", JOptionPane.WARNING_MESSAGE);
    }
}
