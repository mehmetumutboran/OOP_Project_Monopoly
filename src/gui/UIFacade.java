package gui;

import gui.baseFrame.BaseFrame;

public class UIFacade {
    private static UIFacade ourInstance;

    public static UIFacade getInstance() {
        if(ourInstance==null)
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
        BaseFrame.setStatus(panel);
    }

}
