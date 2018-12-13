package gui.prompt.promptStrategy;

import domain.client.ClientCommunicationHandler;

import javax.swing.*;

public class DontMortgagePromptStrategy implements PromptStrategy {
    private int count;

    public DontMortgagePromptStrategy(int count) {
        this.count = count;
    }


    @Override
    public void show() {
        int choice = JOptionPane.showOptionDialog(null,
                "You have " + count + " buildings on this square." +
                        "\nMortgaging will sell these buildings to the bank." +
                        "\nDo you really want to continue?",
                "Warning",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                new String[]{"Yes", "No"},
                "No");

        if(choice == 0){
            for (int i = 0; i < count; i++) {
                //TODO
            }
        }
    }

}
