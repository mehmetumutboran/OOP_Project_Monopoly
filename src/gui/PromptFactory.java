package gui;

import domain.util.Flags;
import gui.promptStrategy.ClosePromptStrategy;
import gui.promptStrategy.DontChangeColorPromptStrategy;
import gui.promptStrategy.DontStartPromptStrategy;
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
        }else if(flag == Flags.getFlag("DontChangeColor")){
            return new DontChangeColorPromptStrategy();
        }
        return null;
    }


    public PromptStrategy getPromptStrategy(char flag, int count) {
        if(flag == Flags.getFlag("DontStart"))
            return new DontStartPromptStrategy(count);
        else return null;
    }
}
