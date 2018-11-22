package domain.interpreter;

import domain.GameLogic;

public class JailInterpreter implements Interpreter {

    @Override
    public void interpret(String[] message) {
        if(message[0].equals(GameLogic.jailFlag)){

        }else if (message[0].equals(GameLogic.goOutJailFlag)){

        }
    }
}
