package domain.server.interpreter;

import domain.server.GameLogic;

public class RollRequestInterpreter implements RequestInterpretable {
    @Override
    public void interpret(String[] message, int index) {
        System.out.println("\n\nRollResponseInterpreter: interpret\n\n");

        String name = message[1];

        GameLogic.getInstance().roll(name);

    }
}
