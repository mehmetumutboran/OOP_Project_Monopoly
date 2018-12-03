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
    private UpgradeButton upgradeButton;
    private DowngradeButton downgradeButton;
    private PauseButton pauseButton;
    private SaveButton saveButton;
    private DrawCardButton drawCardButton;


    public ButtonPanel(int width, int height) {
        this.width = width;
        this.height = height;

        this.setPreferredSize(new Dimension(width, height / 5));
        this.setLayout(new GridLayout(4, 2));

        initGui();

    }

    private void initGui() {
        buyButton = new BuyButton("Buy");
        payRentButton = new PayRentButton("Pay Rent");
        mortgageButton = new MortgageButton("Mortgage");
        unmortgageButton = new UnmortgageButton("Unmortgage");
        finishTurnButton = new FinishTurnButton("Finish Turn");
        rollDiceButton = new RollDiceButton("Roll Dice");
        upgradeButton = new UpgradeButton("Upgrade");
        downgradeButton = new DowngradeButton("Downgrade");
        drawCardButton = new DrawCardButton("Draw Card");
        saveButton = new SaveButton("Save Game");
        pauseButton = new PauseButton("Pause");



        this.add(buyButton);
        this.add(payRentButton);
        this.add(mortgageButton);
        this.add(unmortgageButton);
        this.add(finishTurnButton);
        this.add(rollDiceButton);
        this.add(upgradeButton);
        this.add(downgradeButton);
        this.add(drawCardButton);
        this.add(pauseButton);
        this.add(saveButton);
    }

    public void setHost(boolean b) {
        saveButton.setEnabled(b);
    }
}
