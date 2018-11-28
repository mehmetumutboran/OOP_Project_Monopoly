package domain.server.die.strategies;

import domain.server.die.DiceCup;
import domain.server.die.DiceRollStrategy;

public class RollInJailStrategy implements DiceRollStrategy {

    public RollInJailStrategy() {

    }

    @Override
    public int[] roll(DiceCup diceCup) {
        diceCup.getInstance().getDice()[0].roll(diceCup, 0);
        diceCup.getInstance().getDice()[1].roll(diceCup, 1);
        //diceCup.getInstance().getFaceValues()[2] = 0;
        //diceCup.getInstance().setTotalFaceValue();
        int [] returnDie = new int[3];
        returnDie[0] = diceCup.getDice()[0].getFaceValue();
        returnDie[1] = diceCup.getDice()[1].getFaceValue();
        return returnDie;
    }
}
