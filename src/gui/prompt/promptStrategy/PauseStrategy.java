package gui.prompt.promptStrategy;

import domain.client.PlayerActionController;

import javax.swing.*;

public class PauseStrategy implements PromptStrategy {
    private boolean b;
    private String name;

    private JDialog dialog;

    public PauseStrategy(boolean b, String name) {
        this.b = b;
        this.name = name;
    }

    @Override
    public void show() {
        JOptionPane optionPane;
        if (b) {
            optionPane = new JOptionPane("You paused the game, click resume to continue!",
                    JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new String[]{}, "Resume");
            dialog = new JDialog();
            final JButton resumeButton = new JButton("Resume");

            resumeButton.addActionListener(e -> PlayerActionController.getInstance().resume());
            resumeButton.setVisible(true);
            optionPane.add(resumeButton);
        } else {
            optionPane = new JOptionPane("Game is paused by " + name + "!", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
            dialog = new JDialog();
        }
        dialog.setTitle("Pause");
        dialog.setContentPane(optionPane);

        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.pack();

        dialog.setVisible(true);
    }

    public void close() {
        dialog.dispose();
    }
}
