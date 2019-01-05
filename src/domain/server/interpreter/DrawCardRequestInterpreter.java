package domain.server.interpreter;

import domain.server.board.Board;
import domain.server.board.specialSquares.Chance;
import domain.server.board.specialSquares.CommunityChest;
import domain.server.card.Card;
import domain.server.controller.ServerCommunicationHandler;
import domain.util.Flags;
import domain.util.GameInfo;

public class DrawCardRequestInterpreter implements RequestInterpretable {
    @Override
    public void interpret(String[] message, int index) {
        String name = message[1];
        int[] loc = GameInfo.getInstance().getCurrentPlayer().getToken().getLocation().clone();
        Card drawnCard = null;
        if (Board.getInstance().getSquare(loc[0], loc[1]) instanceof Chance) {
            drawnCard = Board.getInstance().getChanceList()[0];
        } else if (Board.getInstance().getSquare(loc[0], loc[1]) instanceof CommunityChest) {
            drawnCard = Board.getInstance().getCommunityList()[0];
        }

        String cardName = "theInsidersEdge";

        if (drawnCard != null) cardName = drawnCard.getName();
        ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Draw"), name, cardName);

        //ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Money") , name , );
//        int[] loc = GameInfo.getInstance().getCurrentPlayer().getToken().getLocation().clone();
//        if(Board.getInstance().getSquare(loc[0] , loc[1]) instanceof Chance){
//            ChanceCard card = Board.getInstance().getChanceDeckList().pollFirst();
//            Board.getInstance().getChanceDeckList().addLast(card);
//            card.doAction();}
        //       else if(Board.getInstance().getSquare(loc[0] , loc[1]) instanceof CommunityChest){
        //           Community card = Board.getInstance().getCommunityDeckList().pollFirst();
        //           Board.getInstance().getCommunityDeckList().addLast(card);
        //           card.doAction();        }

        //flag : draw card  ->  sendResponse


        if (!GameInfo.getInstance().isBot(name))
            if (GameInfo.getInstance().getPlayer(name).getFaceValues()[2] != 7)
                ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Button"), index, "000010000110", name);
            else
                ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Button"), index, "000010000111", name);

    }
}
