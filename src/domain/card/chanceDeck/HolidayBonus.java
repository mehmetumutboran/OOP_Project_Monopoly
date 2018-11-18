package domain.card.chanceDeck;

import domain.GameLogic;
import domain.card.ChanceCard;

public class HolidayBonus extends ChanceCard {

    public HolidayBonus(String name) {
        super(name);
    }

    @Override
    public boolean doAction() {
        GameLogic.getInstance().getPlayers().peekFirst().increaseMoney(100);
        return true;
    }
}
