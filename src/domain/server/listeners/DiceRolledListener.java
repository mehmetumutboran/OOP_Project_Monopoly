package domain.server.listeners;

public interface DiceRolledListener {

    void onDiceRolledEvent(int[] faces);
}
