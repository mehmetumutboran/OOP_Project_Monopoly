package domain.server.interpreter;

import domain.server.controller.ServerCommunicationHandler;
import domain.server.die.DiceCup;
import domain.server.player.Player;
import domain.util.GameInfo;
import domain.util.Flags;
import domain.util.MessageConverter;

import java.util.*;

public class StartRequestInterpreter implements RequestInterpretable {
    @Override
    public void interpret(String[] message, int index) {
        String name = message[1];
        int count = GameInfo.getInstance().checkReadiness();
        if(count!=0){
            ServerCommunicationHandler.getInstance()
                    .sendResponse(Flags.getFlag("DontStart"), index, count, name);
            return;
        }

        ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Start"), name);

        ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("InitQueue"), name, MessageConverter.convertQueueToString(playerOrder()));
    }

    private Deque<String> playerOrder(){

        for (Player p : GameInfo.getInstance().getPlayerList()) {
            p.setFaceValues(DiceCup.getInstance().rollDice("Init"));
            System.out.println(p.getName() + " " + Arrays.toString(p.getFaceValues()));
        }

        ArrayList<Player> playerSortList = new ArrayList<>(GameInfo.getInstance().getPlayerList());
        Collections.sort(playerSortList);

        Deque<String> pQueue = new LinkedList<>();

        for (Player p:playerSortList) {
            pQueue.add(p.getName());
        }

        return pQueue;
    }
}
