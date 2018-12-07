package domain.server.interpreter;

import domain.server.controller.ServerCommunicationHandler;
import domain.util.Flags;
import domain.util.GameInfo;
import network.server.serverFacade.ServerFacade;

import java.io.DataInputStream;
import java.io.IOException;

public class FinishRequestInterpreter implements RequestInterpretable {
    @Override
    public void interpret(DataInputStream dis, String[] message, int index) {
        String name = message[1];

        ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Finish"), name);

        while (true) {
            try {
                String line = dis.readUTF();
                if (line.charAt(0) == 'z') break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

<<<<<<< HEAD
        String nextPlayer = GameInfo.getInstance().getCurrentPlayer();
        if (!GameInfo.getInstance().isBot(nextPlayer))
            ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Button"), ServerFacade.getInstance().nameToIndex(nextPlayer), "000001000", name);
=======
        ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Button"), ServerFacade.getInstance().nameToIndex(GameInfo.getInstance().getCurrentPlayer()), "110001000", name);
>>>>>>> cdc078b780a675f7db5729062c14aab89663a282
    }
}
