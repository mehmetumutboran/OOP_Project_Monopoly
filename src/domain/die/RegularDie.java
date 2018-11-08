package domain.die;

import java.util.Random;

public class RegularDie extends Die {

    private Random rn; // Random number generator added.

    public RegularDie() {
        super();
        setFaces(new int[]{1, 2, 3, 4, 5, 6});
        rn = new Random();
    }

    @Override
    public void roll(DiceCup diceCup, int index) { //TODO Implementation of this method may change
        int roll = rn.nextInt(6);
        this.setFaceValue(this.getFaces()[roll]);
        diceCup.getInstance().getFaceValues()[index] = this.getFaces()[roll];
    }


}
