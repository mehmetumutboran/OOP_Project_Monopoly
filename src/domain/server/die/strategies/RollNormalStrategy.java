package domain.server.die.strategies;

import domain.server.die.DiceCup;
import domain.server.die.DiceRollStrategy;

public class RollNormalStrategy implements DiceRollStrategy {

    public RollNormalStrategy() {

    }

    @Override
    public int [] roll(DiceCup diceCup) {
        diceCup.getDice()[0].roll(diceCup, 0);
        diceCup.getDice()[1].roll(diceCup, 1);
        diceCup.getDice()[3].roll(diceCup, 2);
        //diceCup.setTotalFaceValue();
        int [] returnDie = new int[3];
        returnDie[0] = diceCup.getDice()[0].getFaceValue();
        returnDie[1] = diceCup.getDice()[1].getFaceValue();
        returnDie[2] = diceCup.getDice()[3].getFaceValue();
        return returnDie;
    }

}
