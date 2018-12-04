package domain.server.interpreter;

import domain.server.controller.ServerCommunicationHandler;
import domain.server.die.DiceCup;
import domain.server.player.Player;
import domain.util.Flags;
import domain.util.GameInfo;
import domain.util.LoadGameHandler;
import domain.util.MessageConverter;
import network.server.serverFacade.ServerFacade;

import java.util.*;

public class StartRequestInterpreter implements RequestInterpretable {
    private int newGameStage = 1;
    private int stage = 1;

    @Override
    public void interpret(String[] message, int index) {
        String name = message[1];
        if (stage == 1) {
            int count = GameInfo.getInstance().checkReadiness();
            if (count != 0) {
                ServerCommunicationHandler.getInstance()
                        .sendResponse(Flags.getFlag("DontStart"), index, count, name);
                return;
            }
            stage++;
        }
        if (stage == 2) {
            if (LoadGameHandler.getInstance().isNewGame()) {
                if (newGameStage == 1) {
                    ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Start"), name);
                    newGameStage++;
                    return;
                }

                if (newGameStage == 2) {
                    ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("InitQueue"), name, MessageConverter.convertQueueToString(playerOrder()));
                    newGameStage++;
                    return;
                }

                if (newGameStage == 3) {
                    ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Finish"), name);
                    newGameStage++;
                    return;
                }
                System.out.println("\n\nCurrPlayer:" + GameInfo.getInstance().getCurrentPlayer() + "\n");

                if (newGameStage == 4) {
                    ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Button"), ServerFacade.getInstance().nameToIndex(GameInfo.getInstance().getCurrentPlayer()), "000001000", name);
                    newGameStage = 1;
                }

            } else {
                LoadGameHandler.getInstance().sendLoad();
            }
            stage = 1;
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
