package gui.controlDisplay.panels;

import gui.controlDisplay.butons.*;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {
    private int width;
    private int height;

    private BuyButton buyButton;
    private PayRentButton payRentButton;
    private MortgageButton mortgageButton;
    private UnmortgageButton unmortgageButton;
    private FinishTurnButton finishTurnButton;
    private RollDiceButton rollDiceButton;

    public ButtonPanel(int width, int height) {
        this.width = width;
        this.height = height;

        this.setPreferredSize(new Dimension(width, height / 5));
        this.setLayout(new GridLayout(3, 2));

        initGui();

    }

    private void initGui() {
        buyButton = new BuyButton("Buy");
        payRentButton = new PayRentButton("Pay Rent");
        mortgageButton = new MortgageButton("Mortgage");
        unmortgageButton = new UnmortgageButton("Unmortgage");
        finishTurnButton = new FinishTurnButton("Finish Turn");
        rollDiceButton = new RollDiceButton("Roll Dice");

        this.add(buyButton);
        this.add(payRentButton);
        this.add(mortgageButton);
        this.add(unmortgageButton);
        this.add(finishTurnButton);
        this.add(rollDiceButton);
    }
}
