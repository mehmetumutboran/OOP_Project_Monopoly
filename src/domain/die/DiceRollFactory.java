package domain.die;

public class DiceRollFactory {
    private static DiceRollFactory df;

    private DiceRollStrategy diceRollStrategy;

    private DiceRollFactory() {
    }

    public static DiceRollFactory getInstance() {
        if (df == null) {
            df = new DiceRollFactory();
        }
        return df;
    }

    public DiceRollStrategy getDiceRollStrategy() {
        return diceRollStrategy;
    }
}
