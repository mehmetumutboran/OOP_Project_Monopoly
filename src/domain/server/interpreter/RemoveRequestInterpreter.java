package domain.server.interpreter;

import domain.server.ReceivedChecker;
import domain.server.controller.ServerCommunicationHandler;
import domain.util.Flags;
import domain.util.GameInfo;
import domain.util.MessageConverter;
import network.server.serverFacade.ServerFacade;

public class RemoveRequestInterpreter implements RequestInterpretable {
    @Override
    public void interpret(String[] message, int index) {
        String username = message[1];

        System.out.println("Exited " + username);


        if (!GameInfo.getInstance().isBot(username)) {
            System.out.println("In kick");
//            int i;
//            if(message[0].equals("Exit")) i = index;
//            else i = ServerFacade.getInstance().nameToIndex(username);
            int ind = ServerFacade.getInstance().nameToIndex(username);

            ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Kick"),
                    ind, username);


            ServerFacade.getInstance().kick(ServerFacade.getInstance().nameToIndex(username));
        }



        if (GameInfo.getInstance().isCurrentPlayer(username)) {
            System.out.println("In isCurrentPlayer");
            ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Finish"), username);

            while (true) {
                if (ReceivedChecker.getInstance().checkReceived()) {
                    ReceivedChecker.getInstance().setReceived();
                    break;
                }
            }

            String nextPlayer = GameInfo.getInstance().getCurrentPlayerName();
            if (!GameInfo.getInstance().isBot(nextPlayer))
                ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Button"), ServerFacade.getInstance().nameToIndex(nextPlayer), "001001110110", nextPlayer);

        }
        ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Remove"), username);

        System.out.println("Player remove for "+username+" is sent");

        ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("IP"), username, MessageConverter.convertArrayToString(ServerFacade.getInstance().getClientInfo()));

    }
}
