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

    public DiceCup() {
        dice = new Die[4];
        for (int i = 0; i < dice.length; i++) {
            if (i == speedDieIndex) {
                dice[i] = new SpeedDie();
                continue;
            }
            dice[i] = new RegularDie();
        }

        faceValues = new int[6];

    }

    public int getTotalFaceValue() {
        return totalFaceValue;
    }

    private void roll() {
        DiceRollFactory.getInstance().getDiceRollStrategy().roll();
    }

    /**
     * Method for RollThree Squares
     */
    public void rollThree() {
        roll();

        for (int i = 0; i < speedDieIndex; i++) {
            totalFaceValue += dice[i].getFaceValue();
        }
    }

    /**
     * Normal roll at the beginning of the turn
     */
    public void turnRoll() {
        roll();

    }

}
