package domain.server.interpreter;

import domain.server.ReceivedChecker;
import domain.server.controller.ServerCommunicationHandler;
import domain.util.Flags;
import domain.util.GameInfo;
import network.server.serverFacade.ServerFacade;

public class RemoveRequestInterpreter implements RequestInterpretable {
    @Override
    public void interpret(String[] message, int index) {
        String username = message[1];

        System.out.println("Eited " + username);


        if (!GameInfo.getInstance().isBot(username)) {
                ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Kick"),
                        ServerFacade.getInstance().nameToIndex(username), username);

            ServerFacade.getInstance().kick(username);
        }

        if (GameInfo.getInstance().isCurrentPlayer(username)) {
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
    }
}
