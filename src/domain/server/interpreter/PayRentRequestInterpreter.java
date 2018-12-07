package domain.server.interpreter;

import domain.server.board.Board;
import domain.server.board.DeedSquare;
import domain.server.board.Square;
import domain.server.controller.ServerCommunicationHandler;
import domain.server.player.Player;
import domain.util.Flags;
import domain.util.GameInfo;

public class PayRentRequestInterpreter implements RequestInterpretable {
    @Override
    public void interpret(String[] message, int index) {


        String name = message[1];

        Player player = GameInfo.getInstance().getPlayer(name);
        int[] loc = player.getToken().getLocation().clone();
        Square square = Board.getInstance().getSquare(loc[0] , loc[1]);

        Boolean paidRent = (player.getBalance() >= ((DeedSquare)square).getCurrentRent() )
                && ( ((DeedSquare) square).getOwner()!= name)
                && ( ((DeedSquare) square).getOwner()!= null) ;

        if(paidRent){
            int customerCurrentMoney = GameInfo.getInstance().getPlayer(player.getName()).getBalance();
            int ownerCurrentMoney = GameInfo.getInstance().getPlayer(((DeedSquare) square).getOwner()).getBalance();
            int customerFinalMoney = customerCurrentMoney - ((DeedSquare)square).getCurrentRent();
            int ownerFinalMoney = ownerCurrentMoney + ((DeedSquare)square).getCurrentRent();

            ServerCommunicationHandler.getInstance()
                    .sendResponse(Flags.getFlag("PayRent"),name , customerFinalMoney ,ownerFinalMoney, square.getName());
        }
        else{
            ServerCommunicationHandler.getInstance()
                    .sendResponse(Flags.getFlag("DontPayRent") , index );

        }
        //       String name = message[1];
//        String sqName = message[2];
//
//        Player player = GameInfo.getInstance().getPlayer(name);
//        DeedSquare square = (DeedSquare) Board.getInstance().getNameGivenSquare(sqName);
//
//        player.decreaseMoney(square.getCurrentRent());
//
//        GameInfo.getInstance().getPlayer(square.getOwner()).increaseMoney(square.getCurrentRent());
//        UIUpdater.getInstance().setMessage(name + " paid rent " + square.getCurrentRent() + " dollars to " + square.getOwner());

    }
}
