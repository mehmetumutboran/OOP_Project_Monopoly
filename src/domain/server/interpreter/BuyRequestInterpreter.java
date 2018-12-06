package domain.server.interpreter;

import domain.server.board.Board;
import domain.server.board.DeedSquare;
import domain.server.board.Square;
import domain.server.controller.ServerCommunicationHandler;
import domain.server.player.Player;
import domain.util.Flags;
import domain.util.GameInfo;

public class BuyRequestInterpreter implements RequestInterpretable {

    @Override
    public void interpret(String[] message, int index) {

        String name = message[1];

//        String name = message[1];
//        String sqName = message[2];
//
          Player player = GameInfo.getInstance().getPlayer(name);
          int[] loc = player.getToken().getLocation().clone();
          Square square = Board.getInstance().getSquare(loc[0] , loc[1]);

          Boolean sold = player.getBalance() >= ((DeedSquare)square).getBuyValue()
                                && ( ((DeedSquare) square).getOwner()==null);
          if(sold ){

              int currentMoney = GameInfo.getInstance().getPlayer(player.getName()).getBalance();
              int finalMoney = currentMoney - ((DeedSquare)square).getBuyValue();
              ServerCommunicationHandler.getInstance()
                      .sendResponse(Flags.getFlag("Buy"),name , finalMoney);


          }
          else{

          }
//        Player player = GameLogic.getInstance().getPlayer(name);
//        Square square = Board.getInstance().getNameGivenSquare(sqName);
//
//        player.decreaseMoney(((DeedSquare) square).getBuyValue());
//        player.addDeed(square);
//
//        ((DeedSquare) square).setOwner(player);
//
//        UIUpdater.getInstance().setMessage(name + " bought " + sqName);
    }

}
