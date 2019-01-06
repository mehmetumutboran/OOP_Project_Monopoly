package domain.server.interpreter;

import domain.server.controller.ServerCommunicationHandler;
import domain.util.Flags;
import domain.util.SaveGameHandler;

public class SaveRequestInterpreter implements RequestInterpretable {
    @Override
    public void interpret(String[] message, int index) {
        //For test purposes
//        GameInfo.getInstance().getPlayerList().get(0).addDeed(Board.getInstance().getNameGivenSquare("Wall Street"));
//        GameInfo.getInstance().getPlayerList().get(0).addDeed(Board.getInstance().getNameGivenSquare("Fifth Avenue"));
//        ((Property) Board.getInstance().getNameGivenSquare("Wall Street")).setBuildingList(new ArrayList<>(Arrays.asList(new House(10), new Hotel(50))));
//        ((Property) Board.getInstance().getNameGivenSquare("Fifth Avenue")).setBuildingList(new ArrayList<>(Arrays.asList(new Skyscraper(190), new Hotel(540))));
//        ((Property) Board.getInstance().getNameGivenSquare("Wall Street")).setOwner(GameInfo.getInstance().getPlayerList().get(0).getName());
//        ((Property) Board.getInstance().getNameGivenSquare("Fifth Avenue")).setOwner(GameInfo.getInstance().getPlayerList().get(0).getName());


        String saveInfo = SaveGameHandler.getInstance().createSave();
        ServerCommunicationHandler.getInstance().
                sendResponse(Flags.getFlag("Save"), saveInfo);
    }
}
