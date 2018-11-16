package domain.die.strategies;

import domain.die.DiceCup;
import domain.die.DiceRollStrategy;

public class RollNormalStrategy implements DiceRollStrategy {

    public RollNormalStrategy() {

    }

    @Override
    public void roll(DiceCup diceCup) {
        diceCup.getInstance().getDice()[0].roll(diceCup, 0);
        diceCup.getInstance().getDice()[1].roll(diceCup, 1);
        diceCup.getInstance().getDice()[3].roll(diceCup, 2);
        diceCup.getInstance().setTotalFaceValue();
    }

}
