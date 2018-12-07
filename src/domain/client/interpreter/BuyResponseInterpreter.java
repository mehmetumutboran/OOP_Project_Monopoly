package domain.client.interpreter;

import domain.client.ClientCommunicationHandler;

public class BuyResponseInterpreter implements ResponseInterpretable {

    @Override
    public void interpret(String[] message) {
//        String name = message[1];
//        String sqName = message[2];
//
//        Player player = GameLogic.getInstance().getPlayer(name);
//        Square square = Board.getInstance().getNameGivenSquare(sqName);
//
//        player.decreaseMoney(((DeedSquare) square).getBuyValue());
//        player.addDeed(square);
//
//        ((DeedSquare) square).setOwner(player);
//
//        UIUpdater.getInstance().setMessage(name + " bought " + sqName);
        ClientCommunicationHandler.getInstance().sendReceived();

    }

}
