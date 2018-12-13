package domain.server.card.communityDeck;


import domain.server.card.Community;

public class TheInsidersEdge extends Community {


    public TheInsidersEdge(String name) {
        super(name);
    }


//    @Override
//    public boolean doAction() {
//
//        int loc[] = GameLogic.getInstance().getCurrentPlayerName().getToken().getLocation();
//        if (loc[0] == 0) {
//            GameLogic.getInstance().getCurrentPlayerName().increaseMoney(250);
//            ServerCommunicationHandler.getInstance().sendResponse(poolFlag, GameLogic.getInstance().getCurrentPlayerName().getName(), 250);
//        } else if (loc[0] == 2) {
//
//            GameLogic.getInstance().getCurrentPlayerName().decreaseMoney(50);
//            ServerCommunicationHandler.getInstance().sendResponse(poolFlag, GameLogic.getInstance().getCurrentPlayerName().getName(), -50);
//
//
//        }
//        return true;
//    }
}
