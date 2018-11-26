package domain.server.card.communityDeck;


import domain.server.GameLogic;
import domain.server.card.Community;
import domain.server.controller.ServerCommunicationHandler;

import static domain.server.GameLogic.poolFlag;

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
