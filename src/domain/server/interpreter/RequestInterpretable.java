package domain.server.interpreter;

public interface RequestInterpretable {
    void interpret(String[] message, int index);
}
