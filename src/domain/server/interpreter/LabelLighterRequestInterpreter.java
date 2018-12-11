package domain.server.interpreter;

import domain.server.board.Property;
import domain.server.board.Railroad;
import domain.server.board.Square;
import domain.server.controller.ServerCommunicationHandler;
import domain.server.player.Player;
import domain.util.GameInfo;

import java.util.ArrayList;
import java.util.Arrays;


public class LabelLighterRequestInterpreter implements RequestInterpretable{

    @Override
    public void interpret(String[] message, int index) {

        String name = message[1];
        ArrayList<Square> sq = new ArrayList<>();
        for(Square p : GameInfo.getInstance().getPlayer(name).getOwnedProperties()){
            if(GameInfo.getInstance().getCurrentPlayer().checkMajority((Property)p) && ((Property) p).isUpgradable((Property)p)){
                sq.add(p);
            }
        }
        for (Square p : GameInfo.getInstance().getPlayer(name).getOwnedRailroads()){
            if(!((Railroad)p).isHasDepot()){
                sq.add(p);
            }
        }
        Square [] squares = sq.toArray(new Square[0]);

    }
}
