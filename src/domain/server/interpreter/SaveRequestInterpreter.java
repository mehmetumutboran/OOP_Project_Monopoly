package domain.server.interpreter;

import domain.server.board.Board;
import domain.server.board.Property;
import domain.server.building.Hotel;
import domain.server.building.House;
import domain.server.building.Skyscraper;
import domain.server.building.TrainDepot;
import domain.server.controller.ServerCommunicationHandler;
import domain.util.Flags;
import domain.util.GameInfo;
import domain.util.SaveGameHandler;

import java.util.ArrayList;
import java.util.Arrays;

public class SaveRequestInterpreter implements RequestInterpretable {
    @Override
    public void interpret(String[] message, int index) {
        GameInfo.getInstance().getPlayerList().get(0).addDeed(Board.getInstance().getNameGivenSquare("Wall Street"));
        GameInfo.getInstance().getPlayerList().get(0).addDeed(Board.getInstance().getNameGivenSquare("Fifth Avenue"));
        ((Property)Board.getInstance().getNameGivenSquare("Wall Street")).setBuildingList(new ArrayList<>(Arrays.asList(new House(10), new Hotel(50))));
        ((Property)Board.getInstance().getNameGivenSquare("Fifth Avenue")).setBuildingList(new ArrayList<>(Arrays.asList(new Skyscraper(190), new TrainDepot(540))));

        String saveInfo = SaveGameHandler.getInstance().createSave();
        ServerCommunicationHandler.getInstance().
                sendResponse(Flags.getFlag("Save"), saveInfo);
    }
}
