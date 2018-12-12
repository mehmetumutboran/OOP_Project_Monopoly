package domain.server.interpreter;

import domain.server.board.Property;
import domain.server.board.Railroad;
import domain.server.board.Square;
import domain.server.controller.ServerCommunicationHandler;
import domain.util.Flags;
import domain.util.GameInfo;
import domain.util.MessageConverter;

import java.util.ArrayList;


public class LabelLighterRequestInterpreter implements RequestInterpretable{

    @Override
    public void interpret(String[] message, int index) {

        String name = message[1];
        String actionType = message[2];
        ArrayList<String> sq = new ArrayList<>();
        if(actionType.equals("UP")) {
            for (Square p : GameInfo.getInstance().getPlayer(name).getOwnedProperties()) {
                if (GameInfo.getInstance().getCurrentPlayer().checkMajority((Property) p) && ((Property) p).isUpgradable((Property) p, name)) {
                    sq.add(p.getName());
                }
            }
            for (Square p : GameInfo.getInstance().getPlayer(name).getOwnedRailroads()) {
                if (!((Railroad) p).isHasDepot()) {
                    sq.add(p.getName());
                }
            }
        }else if(actionType.equals("DOWN")){
            for(Square p: GameInfo.getInstance().getPlayer(name).getOwnedProperties()){
                if(GameInfo.getInstance().getCurrentPlayer().checkMajority((Property)p) && ((Property)p).isDowngradable((Property)p)){
                    sq.add(p.getName());
                }
            }
            for(Square p : GameInfo.getInstance().getPlayer(name).getOwnedRailroads()){
                if(((Railroad)p).isHasDepot()){
                    sq.add(p.getName());
                }
            }
        }
        String [] squares = sq.toArray(new String[0]);
        ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("LabelLighter"),index, MessageConverter.convertArrayToString(squares),actionType);
    }
}
