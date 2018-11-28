package gui.promptStrategy;

import javax.swing.*;

public class DontStartPromptStrategy implements PromptStrategy {
    private int count;
    public DontStartPromptStrategy(int count) {
        this.count = count;
    }


    @Override
    public void show() {
        if(count==1)
            JOptionPane.showMessageDialog(null, "1 Player is not ready!",
                "Error", JOptionPane.WARNING_MESSAGE);
        else
            JOptionPane.showMessageDialog(null, count+" Players are not ready!",
                    "Error", JOptionPane.WARNING_MESSAGE);
    }
}
