package domain.die.strategies;

import domain.die.DiceCup;
import domain.die.DiceRollStrategy;

// This strategy is for getting dice values for ordering players
public class RollInitialStrategy implements DiceRollStrategy {

    public RollInitialStrategy(){

    }

    @Override
    public void roll(DiceCup diceCup) {
        diceCup.getInstance().getDice()[0].roll(diceCup,0);
        diceCup.getInstance().getDice()[1].roll(diceCup,1);
        diceCup.getInstance().setTotalFaceValue();
    }
}
