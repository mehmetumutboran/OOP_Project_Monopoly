package gui.prompt;

import domain.util.Flags;
import gui.prompt.promptStrategy.ClosePromptStrategy;
import gui.prompt.promptStrategy.DontChangeColorPromptStrategy;
import gui.prompt.promptStrategy.DontStartPromptStrategy;
import gui.prompt.promptStrategy.PromptStrategy;

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
        if (flag == Flags.getFlag("Close")) {
            return new ClosePromptStrategy();
        } else if (flag == Flags.getFlag("DontChangeColor")) {
            return new DontChangeColorPromptStrategy();
        } else if (flag == Flags.getFlag("Kick")) {
            return new KickPromptStrategy();
        } else if (flag == Flags.getFlag("Full")) {
            return new FullPromptStrategy();
        }
        return null;
    }


    public PromptStrategy getPromptStrategy(char flag, int count) {
        if (flag == Flags.getFlag("DontStart"))
            return new DontStartPromptStrategy(count);
        else return null;
    }
}
