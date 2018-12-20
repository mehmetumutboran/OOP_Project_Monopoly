package domain.client.interpreter;

public interface ResponseInterpretable {
    /**
     * Interprets response from server.
     * Every different interpreter has this method and interprets messages accordingly.
     *
     * @param message String array of the form [Flag, args...] Generated in {@link domain.server.util.GameState}
     */
    void interpret(String[] message);
}
