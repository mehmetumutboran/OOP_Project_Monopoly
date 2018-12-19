package domain.server.die.strategies;

import domain.server.die.DiceCup;
import domain.server.die.DiceRollStrategy;

public class RollInJailStrategy implements DiceRollStrategy {

    public RollInJailStrategy() {

    }

    @Override
    public int[] roll(DiceCup diceCup) {
        diceCup.getDice()[0].roll(diceCup);
        diceCup.getDice()[1].roll(diceCup);
        int[] returnDie = new int[3];
        returnDie[0] = diceCup.getDice()[0].getFaceValue();
        returnDie[1] = diceCup.getDice()[1].getFaceValue();
        return returnDie;
    }
}
