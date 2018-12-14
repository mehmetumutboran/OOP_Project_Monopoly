package gui.UIFacade;

import gui.baseFrame.BaseFrame;
import gui.prompt.PromptFactory;
import gui.prompt.promptStrategy.PauseStrategy;
import gui.prompt.promptStrategy.PromptStrategy;

public class UIFacade {
    private static UIFacade ourInstance;

    private PauseStrategy pauseStrategy;

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

    public void generatePrompt(char flag, int[] location) {
        PromptFactory.getInstance().getPromptStrategy(flag, location).show();
    }

    public void generatePrompt(char flag, boolean b, String name) {
        pauseStrategy = (PauseStrategy) PromptFactory.getInstance().getPromptStrategy(flag, b, name);
        pauseStrategy.show();
    }

    public void closePrompt(){
        pauseStrategy.close();
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
