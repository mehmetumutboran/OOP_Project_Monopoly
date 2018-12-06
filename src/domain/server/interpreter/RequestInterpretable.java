package domain.server.interpreter;

import java.io.DataInputStream;

public interface RequestInterpretable {
    void interpret(DataInputStream dis, String[] message, int index);
}
