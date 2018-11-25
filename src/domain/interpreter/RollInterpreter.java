package domain.interpreter;

import domain.GameLogic;

public class RollInterpreter implements Interpreter {
    @Override
    public void interpret(String[] message) {
        String name = message[1];

        GameLogic.getInstance().roll(name);

    }
}
