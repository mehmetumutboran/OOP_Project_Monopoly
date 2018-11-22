package domain.die.strategies;

import domain.die.DiceCup;
import domain.die.DiceRollStrategy;

public class RollNormalStrategy implements DiceRollStrategy {

    public RollNormalStrategy() {

    }

    @Override
    public void roll(DiceCup diceCup) {
        diceCup.getDice()[0].roll(diceCup, 0);
        diceCup.getDice()[1].roll(diceCup, 1);
        diceCup.getDice()[3].roll(diceCup, 2);
        diceCup.setTotalFaceValue();
    }

}
