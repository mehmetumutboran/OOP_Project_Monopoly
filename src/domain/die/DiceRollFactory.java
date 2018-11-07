package domain.die;

import domain.die.strategies.RollInitialStrategy;

public class DiceRollFactory {
    private static DiceRollFactory df;

    private DiceRollStrategy diceRollStrategy;

    private DiceRollFactory() {
        setDiceRollStrategy(new RollInitialStrategy()); //TODO the roll strategy choosing will change
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

    public void setDiceRollStrategy(DiceRollStrategy diceRollStrategy) {
        this.diceRollStrategy = diceRollStrategy;
    }
}
