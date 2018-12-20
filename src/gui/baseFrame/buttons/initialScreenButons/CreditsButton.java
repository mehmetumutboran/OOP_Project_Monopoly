package gui.baseFrame.buttons.initialScreenButons;

import gui.baseFrame.BaseFrame;
import gui.baseFrame.buttons.BaseButton;

import java.awt.event.ActionEvent;

public class CreditsButton extends BaseButton {

    public CreditsButton(String text) {
        super(text);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("Credits Button Pressed");
        BaseFrame.setStatus("Credits");
    }
}
