package gui.baseFrame.buttons.lobbyButtons;

import domain.server.controller.ConnectGameHandler;
import domain.util.GameInfo;
import gui.baseFrame.ColorBox;
import gui.baseFrame.buttons.BaseButton;

import java.awt.event.ActionEvent;
import java.util.Random;

public class AddBotButton extends BaseButton {
    private String[] names = {"Bot Annie", "Bot Buddy", "Bot John", "Bot Amelia", "Bot Tom",
            "Bot Sophia", "Bot Jessie", "Bot Amy", "Bot Luna", "Bot Eric", "Bot Ross"};

    public AddBotButton(String text) {
        super(text);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Random random = new Random();

        String color = ColorBox.colorList.get(random.nextInt(ColorBox.colorList.size()));
        while (GameInfo.getInstance().hasColor(color)) {
            color = ColorBox.colorList.get(random.nextInt(ColorBox.colorList.size()));
        }

        String name = names[random.nextInt(names.length)];
        while (GameInfo.getInstance().hasPlayer(name)) {
            name = names[random.nextInt(names.length)];
        }

        ConnectGameHandler.getInstance().connectBot(name, color);
    }
}
