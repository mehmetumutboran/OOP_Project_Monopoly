package gui.prompt;

import domain.util.Flags;
import gui.prompt.promptStrategy.*;

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
        else if (flag == Flags.getFlag("DontBuy")) {
            return new DontBuyStrategy();
        }
        else if (flag == Flags.getFlag("DontPayRent")) {
            return new DontPayRentStrategy();
        }
        return null;
    }


    public PromptStrategy getPromptStrategy(char flag, int count) {
        if (flag == Flags.getFlag("DontStart"))
            return new DontStartPromptStrategy(count);
        else return null;
    }


    public PromptStrategy getPromptStrategy(char flag, boolean b, String name) {
        if (flag == Flags.getFlag("Pause")) {
            return new PauseStrategy(b, name);
        }else return null;
    }

}
