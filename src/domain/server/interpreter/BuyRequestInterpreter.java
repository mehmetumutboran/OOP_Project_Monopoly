package domain.server.interpreter;

import domain.server.ReceivedChecker;
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

        Player player = GameInfo.getInstance().getPlayer(name);
        int[] loc = player.getToken().getLocation().clone();
        Square square = Board.getInstance().getSquare(loc[0], loc[1]);

        Boolean sold = (square instanceof DeedSquare) && player.getBalance() >= ((DeedSquare) square).getBuyValue()
                && (((DeedSquare) square).getOwner() == null);
        if (sold) {

            int currentMoney = GameInfo.getInstance().getPlayer(player.getName()).getBalance();
            int finalMoney = currentMoney - ((DeedSquare) square).getBuyValue();

            ServerCommunicationHandler.getInstance()
                    .sendResponse(Flags.getFlag("Buy"), name, finalMoney, square.getName());

            // if( all squares for one color bought or trainstations -> change rent )

            while (true) {
                if (ReceivedChecker.getInstance().checkReceived()) {
                    ReceivedChecker.getInstance().setReceived();
                    break;
                }
            }

            if (!GameInfo.getInstance().isBot(name))
                ServerCommunicationHandler.getInstance()
                        .sendResponse(Flags.getFlag("ChangeOneButton"), index, "0", "0");

        } else {
            ServerCommunicationHandler.getInstance()
                    .sendResponse(Flags.getFlag("DontBuy"), index, name);
        }
    }

}
