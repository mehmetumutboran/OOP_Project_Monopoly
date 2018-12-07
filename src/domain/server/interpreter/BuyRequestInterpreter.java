package domain.server.interpreter;

<<<<<<< src/domain/server/interpreter/BuyRequestInterpreter.java
import domain.server.board.Board;
import domain.server.board.DeedSquare;
import domain.server.board.Square;
import domain.server.controller.ServerCommunicationHandler;
import domain.server.player.Player;
import domain.util.Flags;
import domain.util.GameInfo;
import java.io.DataInputStream;

public class BuyRequestInterpreter implements RequestInterpretable {

    @Override
    public void interpret(DataInputStream dis, String[] message, int index) {  

        String name = message[1];

          Player player = GameInfo.getInstance().getPlayer(name);
          int[] loc = player.getToken().getLocation().clone();
          Square square = Board.getInstance().getSquare(loc[0] , loc[1]);

          Boolean sold = player.getBalance() >= ((DeedSquare)square).getBuyValue()
                                && ( ((DeedSquare) square).getOwner()==null);
          if(sold ){

              int currentMoney = GameInfo.getInstance().getPlayer(player.getName()).getBalance();
              int finalMoney = currentMoney - ((DeedSquare)square).getBuyValue();

              ServerCommunicationHandler.getInstance()
                      .sendResponse(Flags.getFlag("Buy"),name , finalMoney , square.getName());

                // if( all squares for one color bought or trainstations -> change rent )

          }
          else{
              ServerCommunicationHandler.getInstance()
                      .sendResponse(Flags.getFlag("DontBuy") , index );
          }
    }

}
