package domain.server.die;


/**
 * Holds the game dice
 * Has roll methods
 */
public class DiceCup {
    private Die[] dice;
    private final int speedDieIndex = 3;

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

    }

    public static DiceCup getInstance() {
        if (dc == null) {
            dc = new DiceCup();
        }
        return dc;
    }

    public Die[] getDice() {
        return dice;
    }

    public int[] rollDice(String locName) {
        return DiceRollFactory.getInstance().chooseDiceRollStrategy(locName).roll(this);
    }

}
