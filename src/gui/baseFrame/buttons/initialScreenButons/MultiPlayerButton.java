package gui.baseFrame.buttons.initialScreenButons;

import gui.baseFrame.BaseFrame;
import gui.baseFrame.buttons.BaseButton;

import java.awt.event.ActionEvent;

public class MultiPlayerButton extends BaseButton {

    public MultiPlayerButton(String text) {
        super(text);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("Multi Button Pressed");
        BaseFrame.setStatus("Multi");
    }
}
