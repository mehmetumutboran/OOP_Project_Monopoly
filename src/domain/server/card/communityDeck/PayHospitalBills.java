package domain.server.card.communityDeck;

import domain.client.UIUpdater;
import domain.server.card.Community;
import domain.util.GameInfo;

public class PayHospitalBills extends Community {


    public PayHospitalBills(String name) {
        super(name);
    }


    @Override
    public boolean doAction(String name) {
        GameInfo.getInstance().getPlayer(name).increaseMoney(-100);
        UIUpdater.getInstance().setMessage(name + " paid $100 Hospital Bills ");
        return true;
    }
}
