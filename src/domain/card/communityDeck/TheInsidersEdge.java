package domain.card.communityDeck;


import domain.GameLogic;
import domain.card.Community;
import domain.controller.ServerCommunicationHandler;

import static domain.GameLogic.poolFlag;

public class TheInsidersEdge extends Community {


    public TheInsidersEdge(String name) {
        super(name);
    }


    @Override
    public boolean doAction() {

        int loc[] = GameLogic.getInstance().getCurrentPlayer().getToken().getLocation();
        if (loc[0] == 0) {
            GameLogic.getInstance().getCurrentPlayer().increaseMoney(250);
            ServerCommunicationHandler.getInstance().sendResponse(poolFlag, GameLogic.getInstance().getCurrentPlayer().getName(), 250);
        } else if (loc[0] == 2) {

            GameLogic.getInstance().getCurrentPlayer().decreaseMoney(50);
            ServerCommunicationHandler.getInstance().sendResponse(poolFlag, GameLogic.getInstance().getCurrentPlayer().getName(), -50);


        }
        return true;
    }
}
