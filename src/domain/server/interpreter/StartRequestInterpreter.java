package domain.server.interpreter;

import domain.server.ReceivedChecker;
import domain.server.controller.ServerCommunicationHandler;
import domain.server.die.DiceCup;
import domain.server.player.Player;
import domain.util.Flags;
import domain.util.GameInfo;
import domain.util.LoadGameHandler;
import domain.util.MessageConverter;
import network.server.serverFacade.ServerFacade;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.*;

public class StartRequestInterpreter implements RequestInterpretable {

    @Override
    public void interpret(DataInputStream dis, String[] message, int index) {
        String name = message[1];
        int count = GameInfo.getInstance().checkReadiness();
        if (count != 0) {
            ServerCommunicationHandler.getInstance()
                    .sendResponse(Flags.getFlag("DontStart"), index, count, name);
            return;
        }

        if (LoadGameHandler.getInstance().isNewGame()) {
            synchronized (this) {
                ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Start"), name);

                String line;

                while (true){
                    try {
                        line = dis.readUTF();
                        if(line.charAt(0)=='z') break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("InitQueue"), name, MessageConverter.convertQueueToString(playerOrder()));

                while (true){
                    try {
                        line = dis.readUTF();
                        if(line.charAt(0)=='z') break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Finish"), name);

                while (true){
                    try {
                        line = dis.readUTF();
                        if(line.charAt(0)=='z') break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("\n\nCurrPlayer:" + GameInfo.getInstance().getCurrentPlayer() + "\n");

<<<<<<< HEAD
                String nextPlayer = GameInfo.getInstance().getCurrentPlayer();
                if (!GameInfo.getInstance().isBot(nextPlayer))
                    ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Button"), ServerFacade.getInstance().nameToIndex(nextPlayer), "000001000", name);
=======
                ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Button"), ServerFacade.getInstance().nameToIndex(GameInfo.getInstance().getCurrentPlayer()), "010001000", name); //TODO
>>>>>>> cdc078b780a675f7db5729062c14aab89663a282
            }
        } else {
            LoadGameHandler.getInstance().sendLoad();
        }
    }

    private Deque<String> playerOrder() {

        for (Player p : GameInfo.getInstance().getPlayerList()) {
            p.setFaceValues(DiceCup.getInstance().rollDice("Init"));
            System.out.println(p.getName() + " " + Arrays.toString(p.getFaceValues()));
        }

        ArrayList<Player> playerSortList = new ArrayList<>(GameInfo.getInstance().getPlayerList());
        Collections.sort(playerSortList);

        Deque<String> pQueue = new LinkedList<>();

        for (Player p : playerSortList) {
            pQueue.addLast(p.getName());
        }

        pQueue.addFirst(pQueue.removeLast()); // Added last player to first place since everyone will first pop the queue

        return pQueue;
    }
}
