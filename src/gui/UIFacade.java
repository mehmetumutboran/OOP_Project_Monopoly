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

    public void changePanel() {
        BaseFrame.setStatus("Lobby");
    }
}
