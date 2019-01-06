package gui.prompt.promptStrategy;

import javax.swing.*;

public class DontBuyStrategy implements PromptStrategy {
    @Override
    public void show() {
        JOptionPane.showMessageDialog(null, "You cannot buy this square! " +
                        "Either it has owner or You do not have enough money.",
                "Error", JOptionPane.WARNING_MESSAGE);
    }
}
