package domain.server.interpreter;

import domain.server.ReceivedChecker;
import domain.server.controller.ServerCommunicationHandler;
import domain.util.Flags;
import domain.util.GameInfo;
import network.client.clientFacade.ClientFacade;
import network.server.serverFacade.ServerFacade;

public class ReconnectRequestInterpreter implements RequestInterpretable {
    @Override
    public void interpret(String[] message, int index) {
        String name = message[1];

        System.out.println(name + " requested to reconnect");

        if (GameInfo.getInstance().hasPlayer(name))
            ServerFacade.getInstance().setClientInfo(name);

        String oldHostName = ClientFacade.getInstance().getOldHostName();
        ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Reconnect"), index,
                ClientFacade.getInstance().getUsername(), oldHostName);

        System.out.println("Reconnect Request Interpreter!!!");

        while (true) {
            if (ReceivedChecker.getInstance().checkReceived(index)) {
                ReceivedChecker.getInstance().setReceived(index);
                break;
            }
        }

        System.out.println(oldHostName + " was the old host!");

        if (GameInfo.getInstance().WasHostPeekBefore()) {
            ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Finish"), index, name);

            while (true) {
                if (ReceivedChecker.getInstance().checkReceived(index)) {
                    ReceivedChecker.getInstance().setReceived(index);
                    break;
                }
            }

            System.out.println("After having received in finish in reconnect!!!");

        }

        String nextPlayer = GameInfo.getInstance().getCurrentPlayerName();
        if (nextPlayer.equals(name) && !GameInfo.getInstance().isBot(nextPlayer))
            ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Button"),
                    index, "001001110110", nextPlayer);
    }
}
