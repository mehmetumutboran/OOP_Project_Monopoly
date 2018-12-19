package domain.client.interpreter;

import domain.client.ClientCommunicationHandler;
import domain.client.UIUpdater;
import domain.server.board.Board;
import domain.server.board.DeedSquare;
import domain.server.board.Square;
import domain.util.Flags;
import domain.util.GameInfo;

import java.util.ArrayList;

public class MortgageResponseInterpreter implements ResponseInterpretable {
    @Override
    public void interpret(String[] message) {
        String name = message[1];
        String sqName = message[2];

        ((DeedSquare) Board.getInstance().getNameGivenSquare(sqName)).setMortgaged(true);
        GameInfo.getInstance().getPlayer(name).addMortgagedSquare((DeedSquare) Board.getInstance().getNameGivenSquare(sqName));
        GameInfo.getInstance().getPlayer(name).increaseMoney(((DeedSquare) Board.getInstance().getNameGivenSquare(sqName)).getMortgageValue());

        UIUpdater.getInstance().setMessage(name + " mortgaged " + sqName);

        ArrayList<int[]> locationList = new ArrayList<>();
        for(Square s : GameInfo.getInstance().getPlayer(name).getOwnedProperties()){
            locationList.add(s.getLocation());
        }

        for(Square s : GameInfo.getInstance().getPlayer(name).getOwnedRailroads()){
            locationList.add(s.getLocation());
        }

        for(Square s : GameInfo.getInstance().getPlayer(name).getOwnedUtilities()){
            locationList.add(s.getLocation());
        }


        UIUpdater.getInstance().updateLabels(locationList, String.valueOf(Flags.getFlag("Mortgage")), 0);
        ClientCommunicationHandler.getInstance().sendReceived();
    }
}
