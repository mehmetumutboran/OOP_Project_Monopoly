package domain.server.die;

public interface DiceRollStrategy {
    int[] roll(DiceCup diceCup);
}
