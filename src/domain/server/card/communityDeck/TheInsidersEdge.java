package domain.server.card.communityDeck;


import domain.client.UIUpdater;
import domain.server.card.Community;
import domain.util.GameInfo;

public class TheInsidersEdge extends Community {


    public TheInsidersEdge(String name) {
        super(name);
    }


    @Override
    public boolean doAction(String name) {

        int loc[] = GameInfo.getInstance().getPlayer(name).getToken().getLocation().clone();
        if (loc[0] == 0) {
            GameInfo.getInstance().getPlayer(name).increaseMoney(250);
            UIUpdater.getInstance().setMessage(name + " is in Inner Track. So " + name + " collects 250 dollars from bank ");
        } else if (loc[0] == 2) {
            GameInfo.getInstance().getPlayer(name).increaseMoney(-50);
            UIUpdater.getInstance().setMessage(name + " is in Outer Track. So " + name + " pays 50 dollars to the bank ");
        } else if (loc[0] == 1) {
            UIUpdater.getInstance().setMessage(name + " is in Center Track. So " + name + " does nothing ");
        }

        return true;
    }
}
