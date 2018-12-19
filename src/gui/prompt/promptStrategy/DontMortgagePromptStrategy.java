package gui.prompt.promptStrategy;

import domain.client.ClientCommunicationHandler;
import domain.client.PlayerActionController;
import domain.server.ReceivedChecker;
import domain.util.Flags;
import domain.util.MessageConverter;

import javax.swing.*;

public class DontMortgagePromptStrategy implements PromptStrategy {
    private int[] location;

    public DontMortgagePromptStrategy(int[] location) {
        this.location = location.clone();
    }

    @Override
    public void show() {
        int choice = JOptionPane.showOptionDialog(null,
                "You have buildings on this color group." +
                        "\nMortgaging will sell these buildings to the bank." +
                        "\nDo you really want to continue?",
                "Warning",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                new String[]{"Yes", "No"},
                "No");

        if (choice == 0) {
            ClientCommunicationHandler.getInstance().sendRequest(Flags.getFlag("ColorDowngrade"), MessageConverter.convertArrayToString(location));

            PlayerActionController.getInstance().mortgageSquare(location);
        }
    }

}
