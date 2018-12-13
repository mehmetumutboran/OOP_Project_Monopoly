package domain.server.die.strategies;

import domain.server.die.DiceCup;
import domain.server.die.DiceRollStrategy;

public class RollThreeStrategy implements DiceRollStrategy {

    public RollThreeStrategy() {

    }

    @Override
    public int[] roll(DiceCup diceCup) {
        diceCup.getDice()[0].roll(diceCup);
        diceCup.getDice()[1].roll(diceCup);
        diceCup.getDice()[2].roll(diceCup);
        //diceCup.getInstance().setTotalFaceValue();
        int[] returnDie = new int[3];
        returnDie[0] = diceCup.getDice()[0].getFaceValue();
        returnDie[1] = diceCup.getDice()[1].getFaceValue();
        returnDie[2] = diceCup.getDice()[2].getFaceValue();
        return returnDie;
    }
}
