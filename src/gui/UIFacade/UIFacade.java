package gui.UIFacade;

import gui.baseFrame.BaseFrame;
import gui.prompt.PromptFactory;

public class UIFacade {
    private static UIFacade ourInstance;

    public static UIFacade getInstance() {
        if (ourInstance == null)
            ourInstance = new UIFacade();
        return ourInstance;
    }

    private UIFacade() {
    }


    public void generatePrompt(char flag) {
        PromptFactory.getInstance().getPromptStrategy(flag).show();
    }

    public void generatePrompt(char flag, int count) {
        PromptFactory.getInstance().getPromptStrategy(flag, count).show();
    }

    public void changePanel(String panel) {
        if (!BaseFrame.getStatus().equals(panel))
            BaseFrame.setStatus(panel);
    }

    public void setTitle(String username) {
        BaseFrame.setFrameTitle(username);
    }

//    public void generateList(ArrayList<String> playerListName,ArrayList<String> playerListColor) {
//        PlayerLabelsPanel.setPlayerLabel(playerListName,playerListColor);
//    }
}
