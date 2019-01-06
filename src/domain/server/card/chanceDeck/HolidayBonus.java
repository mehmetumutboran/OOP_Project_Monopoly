package domain.server.card.chanceDeck;

import domain.client.UIUpdater;
import domain.server.card.ChanceCard;
import domain.util.GameInfo;

public class HolidayBonus extends ChanceCard {

    public HolidayBonus(String name) {
        super(name);
    }

    @Override
    public boolean doAction(String name) {
//        GameLogic.getInstance().getCurrentPlayerName().increaseMoney(100);
//        UIUpdater.getInstance().setMessage(name + " draw " + drawnCard);
        GameInfo.getInstance().getCurrentPlayer().increaseMoney(100);
        UIUpdater.getInstance().setMessage(name + " collected $100 from the bank" );
        return true;
    }
}
