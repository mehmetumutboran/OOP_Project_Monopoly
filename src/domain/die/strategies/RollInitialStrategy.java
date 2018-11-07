package domain.die.strategies;

import domain.die.DiceCup;
import domain.die.DiceRollStrategy;

// This strategy is for getting dice values for ordering players
public class RollInitialStrategy implements DiceRollStrategy {

    public RollInitialStrategy(){

    }

    @Override
    public void roll(DiceCup diceCup) {
        diceCup.getDice()[0].roll(diceCup,0);
        diceCup.getDice()[1].roll(diceCup,1);
        diceCup.setTotalFaceValue();
    }
}
