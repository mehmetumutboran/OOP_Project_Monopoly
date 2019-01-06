package domain.server.interpreter;

import domain.server.ReceivedChecker;
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
        Square square = Board.getInstance().getSquare(loc[0], loc[1]);

        System.out.println("Square in pay rent request from " + name + " is " + square.getName());
        boolean paidRent = (square instanceof DeedSquare)
                && (player.getBalance() >= ((DeedSquare) square).getCurrentRent())
                && (((DeedSquare) square).getOwner() != null)
                && (!((DeedSquare) square).getOwner().equals(name));

        if (paidRent) {
            int customerCurrentMoney = GameInfo.getInstance().getPlayer(player.getName()).getBalance();
            int ownerCurrentMoney = GameInfo.getInstance().getPlayer(((DeedSquare) square).getOwner()).getBalance();
            int customerFinalMoney = customerCurrentMoney - ((DeedSquare) square).getCurrentRent();
            int ownerFinalMoney = ownerCurrentMoney + ((DeedSquare) square).getCurrentRent();

            ServerCommunicationHandler.getInstance()
                    .sendResponse(Flags.getFlag("PayRent"), name, customerFinalMoney, ownerFinalMoney, square.getName());

//            while (true){
//                try {
//                    String line = dis.readUTF();
//                    if(line.charAt(0)=='z') break;
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }

            ReceivedChecker.getInstance().check();

            if (!GameInfo.getInstance().isBot(name))
                if (GameInfo.getInstance().getPlayer(name).getFaceValues()[2] == 7 && !GameInfo.getInstance().getPlayer(name).isSecondMove()) // if player had mr monopoly before
                    ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Button"), index, "000000000111", name);
                else
                    ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Button"), index, "000010000110", name);
        } else {

//            while (true){
//                if(ReceivedChecker.getInstance().checkReceived()) {
//                    ReceivedChecker.getInstance().setReceived();
//                    break;
//                }
//            }

            ServerCommunicationHandler.getInstance()
                    .sendResponse(Flags.getFlag("DontPayRent"), index, name);

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
