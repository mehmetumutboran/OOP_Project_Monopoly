package domain.server.card.chanceDeck;

import domain.server.card.ChanceCard;

public class HolidayBonus extends ChanceCard {

    public HolidayBonus(String name) {
        super(name);
    }

    @Override
    public boolean doAction() {
//        GameLogic.getInstance().getCurrentPlayer().increaseMoney(100);
        return true;
    }
}
