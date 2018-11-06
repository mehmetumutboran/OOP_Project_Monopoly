package domain;

/**
 * Planned as a Class that interprets received Message then updates game state
 */
public class MessageInterpreter {
    private static MessageInterpreter instance;

    private String message;

    private MessageInterpreter() {

    }

    public static MessageInterpreter getInstance() {
        return instance;
    }


}