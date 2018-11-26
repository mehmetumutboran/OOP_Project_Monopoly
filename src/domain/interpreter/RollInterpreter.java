package domain.interpreter;

import domain.GameLogic;

public class RollInterpreter implements Interpreter {
    @Override
    public void interpret(String[] message) {
        System.out.println("\n\nRollInterpreter: interpret\n\n");

        String name = message[1];

        GameLogic.getInstance().roll(name);

    }
}
