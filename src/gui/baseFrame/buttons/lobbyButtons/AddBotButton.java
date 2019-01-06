package gui.baseFrame.buttons.lobbyButtons;

import domain.server.controller.ConnectGameHandler;
import domain.util.GameInfo;
import gui.baseFrame.ColorBox;
import gui.baseFrame.buttons.BaseButton;

import java.awt.event.ActionEvent;
import java.util.Random;

public class AddBotButton extends BaseButton {
    private String[] names = {"Annie", "Buddy", "John", "Amelia", "Tom", "Sophia", "Jessie", "Amy", "Luna", "Eric", "Ross"};
    private static int count = 0;
    private static String difficulty = "Medium";

    public AddBotButton(String text) {
        super(text);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        count++;
        Random random = new Random();
        String color = ColorBox.colorList.get(random.nextInt(ColorBox.colorList.size()));
        while (GameInfo.getInstance().hasColor(color)) {
            color = ColorBox.colorList.get(random.nextInt(ColorBox.colorList.size()));
        }
        ConnectGameHandler.getInstance().connectBot(difficulty.charAt(0)+"Bot " + names[count % 11],
                color, difficulty);
    }

    public static void setDifficulty(String diff) {
        difficulty = diff;
    }
}
