package gui.baseFrame.buttons.initialScreenButons;

import gui.baseFrame.BaseFrame;
import gui.baseFrame.buttons.BaseButton;

import java.awt.event.ActionEvent;

public class SinglePlayerButton extends BaseButton {

    public SinglePlayerButton(String text) {
        super(text);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("Single Button Pressed");
        BaseFrame.setStatus("Single");
    }
}
