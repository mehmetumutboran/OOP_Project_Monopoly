package gui;

import domain.util.Flags;
import gui.promptStrategy.ClosePromptStrategy;
import gui.promptStrategy.PromptStrategy;

public class PromptFactory {
    private static PromptFactory ourInstance;

    public static PromptFactory getInstance() {
        if (ourInstance == null) {
            ourInstance = new PromptFactory();
        }
        return ourInstance;
    }

    private PromptFactory() {
    }

    public PromptStrategy getPromptStrategy(char flag) {
        if(flag == Flags.getFlag("Close")){
            return new ClosePromptStrategy();
        }
        return null;
    }
}
