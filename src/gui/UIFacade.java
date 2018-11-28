package gui;

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
}
