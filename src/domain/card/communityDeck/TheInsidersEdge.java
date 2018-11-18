package domain.card.communityDeck;


import domain.GameLogic;
import domain.board.Board;
import domain.card.Community;
import domain.controller.GameCommunicationHandler;

import static domain.GameLogic.poolFlag;

public class TheInsidersEdge extends Community {


    public TheInsidersEdge(String name) {
        super(name);
    }


    @Override
    public boolean doAction() {

        int loc []= GameLogic.getInstance().getPlayers().peekFirst().getToken().getLocation();
        if(loc[0] == 0 ) {
            GameLogic.getInstance().getPlayers().peekFirst().increaseMoney(250);
        }

        else if(loc[0] == 2){

            GameLogic.getInstance().getPlayers().peekFirst().decreaseMoney(50);
            GameCommunicationHandler.getInstance().sendPoolAction(poolFlag , 50);


        }
        return true;
    }
}
