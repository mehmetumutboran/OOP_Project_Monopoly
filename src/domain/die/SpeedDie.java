package domain.die;

import java.util.Random;

public class SpeedDie extends Die{
    private int mrMonopolyFace = 7;
    private int busFace = 8;

    private Random rn;


    public SpeedDie() {
        super();
        setFaces(new int[]{1, 2, 3, mrMonopolyFace, mrMonopolyFace, busFace});
        rn = new Random();
    }

    @Override
    public void roll() {
        this.setFaceValue(this.getFaces()[rn.nextInt(6)]);
    }
}
