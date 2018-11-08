package domain.die;


/**
 * Holds the game dice
 * Has roll methods
 */
public class DiceCup {
    private Die[] dice;
    private final int speedDieIndex = 3;

    private int[] faceValues;
    private int totalFaceValue;

    private static DiceCup dc;

    private DiceCup() {
        dice = new Die[4];
        for (int i = 0; i < dice.length; i++) {
            if (i == speedDieIndex) {
                dice[i] = new SpeedDie();
                continue;
            }
            dice[i] = new RegularDie();
        }

        faceValues = new int[3];

    }

    public static DiceCup getInstance(){
        if(dc == null){
            dc = new DiceCup();
        }
        return dc;
    }

    public Die[] getDice() {
        return dice;
    }

    public int[] getFaceValues() {
        return faceValues;
    }

    public void setTotalFaceValue() {
        if(7 > this.getFaceValues()[2]) this.totalFaceValue = this.getFaceValues()[0] + this.getFaceValues()[1] + this.getFaceValues()[2];
        else this.totalFaceValue = this.getFaceValues()[0] + this.getFaceValues()[1];
    }

    public int getTotalFaceValue() {
        return totalFaceValue;
    }

    public void setFaceValues(int[] faceValues) {
        this.faceValues = faceValues;
    }

    public void rollDice(String locName) {
        DiceRollFactory.getInstance().chooseDiceRollStrategy(locName).roll(this);
    }

//    public static void main(String args[]){
//        DiceCup d1 = new DiceCup();
//        System.out.println(d1.getTotalFaceValue());
//        d1.rollDice();
//        System.out.println(d1.getFaceValues()[0]);
//        System.out.println(d1.getFaceValues()[1]);
//        System.out.println(d1.getFaceValues()[2]);
//        System.out.println(d1.getTotalFaceValue());
//    }


}
