package domain.client.interpreter;

import domain.client.ClientCommunicationHandler;
import domain.client.UIUpdater;
import domain.server.board.Board;
import domain.server.board.Square;
import domain.server.board.Upgradable;
import domain.server.player.Player;
import domain.util.GameInfo;

import java.util.ArrayList;

public class UpgradeResponseInterpreter implements ResponseInterpretable {
    @Override
    public void interpret(String[] message) {
        //@requires message[2]message[3] not null

        Player currentPlayer = GameInfo.getInstance().getPlayer(message[1]);
        Upgradable selectedSquareForUpgrade = (Upgradable) Board.getInstance().getNameGivenSquare(message[2]);

        selectedSquareForUpgrade.upgrade();

        currentPlayer.decreaseMoney(selectedSquareForUpgrade.getBuildingCost());

        ArrayList<int[]> locationList = new ArrayList<>();
        for (Square s : currentPlayer.getOwnedProperties()) {
            locationList.add(s.getLocation());
        }

        for (Square s : currentPlayer.getOwnedRailroads()) {
            locationList.add(s.getLocation());
        }


        /////Skyscrapper oldugunda hata var
        UIUpdater.getInstance().setMessage(currentPlayer.getName() + " upgraded " + ((Square) selectedSquareForUpgrade).getName()
                + " to " + message[3]);

        UIUpdater.getInstance().updateLabels(locationList, "UP", 0);

        ClientCommunicationHandler.getInstance().sendReceived();


    }
}
