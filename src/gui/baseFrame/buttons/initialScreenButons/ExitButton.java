package gui.baseFrame.buttons.initialScreenButons;

import gui.baseFrame.buttons.BaseButton;

import java.awt.event.ActionEvent;

public class ExitButton extends BaseButton {
    public ExitButton(String text) {
        super(text);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.exit(0);
    }
}
