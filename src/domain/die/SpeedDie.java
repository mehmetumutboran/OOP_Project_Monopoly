package domain.die;

import java.util.Random;

public class SpeedDie extends Die {
    private int mrMonopolyFace = 7;
    private int busFace = 8;

    private Random rn;


    public SpeedDie() {
        super();
        setFaces(new int[]{1, 2, 3, mrMonopolyFace, mrMonopolyFace, busFace});
        rn = new Random();
    }

    @Override
    public void roll(DiceCup diceCup, int index) {
        int roll = rn.nextInt(6);
        this.setFaceValue(this.getFaces()[roll]);
        diceCup.getInstance().getFaceValues()[index] = this.getFaces()[roll];
    }
}
