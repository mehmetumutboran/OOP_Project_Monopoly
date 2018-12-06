package domain.server.interpreter;

import java.io.DataInputStream;

public class SpecialSquareRequestInterpreter implements RequestInterpretable {
    @Override
    public void interpret(DataInputStream dis, String[] message, int index) {
//        String name = message[1];
//
//        int[] loc = GameLogic.getInstance().getPlayer(name).getToken().getLocation();
//        if (Board.getInstance().getSquare(loc[0], loc[1]) instanceof Chance) {
//            UIUpdater.getInstance().setMessage(name + " picked " + Board.getInstance().getChanceDeckList()[0].getName());
//        } else if (Board.getInstance().getSquare(loc[0], loc[1]) instanceof CommunityChest) {
//            UIUpdater.getInstance().setMessage(name + " picked " + Board.getInstance().getCommunityDeckList()[0].getName());
//        }

    }
}
