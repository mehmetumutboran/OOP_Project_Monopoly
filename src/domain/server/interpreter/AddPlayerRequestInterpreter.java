package domain.server.interpreter;

import domain.server.ReceivedChecker;
import domain.server.controller.ServerCommunicationHandler;
import domain.util.Flags;
import domain.util.GameInfo;
import domain.util.MessageConverter;
import network.server.serverFacade.ServerFacade;

public class AddPlayerRequestInterpreter implements RequestInterpretable {
    @Override
    public void interpret(String[] message, int index) {
        String name = message[1];
//        System.out.println("\nIS Multi  " + ServerFacade.getInstance().isMulti());

        ServerFacade.getInstance().setClientInfo(name);

        if (!ServerFacade.getInstance().isMulti() && index != 0) {
            ServerCommunicationHandler.getInstance()
                    .sendResponse(Flags.getFlag("Kick"), index, name);
            return;
        }

        if (GameInfo.getInstance().isFull()) {
            ServerCommunicationHandler.getInstance()
                    .sendResponse(Flags.getFlag("Full"), index, name);
            return;
        }

        if (GameInfo.getInstance().hasPlayer(name)) {
            ServerCommunicationHandler.getInstance()
                    .sendResponse(Flags.getFlag("Close"), index, name);

            while (true) {
                if (ReceivedChecker.getInstance().checkReceived(index)) {
                    ReceivedChecker.getInstance().setReceived();
                    break;
                }
            }

            ServerFacade.getInstance().kick(index);
            return;
        }

        if (!GameInfo.getInstance().isListEmpty())
            ServerCommunicationHandler.getInstance()
                    .sendResponse(Flags.getFlag("AddPlayerList"), index, GameInfo.getInstance().getPlayersAsString());

        ServerCommunicationHandler.getInstance()
                .sendResponse(Flags.getFlag("AddPlayer"), name);

//        System.out.println("-------------------\n" +  MessageConverter.convertArrayToString(ServerFacade.getInstance().getClientInfo()));

        ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("IP"), name, MessageConverter.convertArrayToString(ServerFacade.getInstance().getClientInfo()));
    }
}
