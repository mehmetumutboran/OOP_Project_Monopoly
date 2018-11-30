package domain.server.interpreter;

public class JailRequestInterpreter implements RequestInterpretable {

    @Override
    public void interpret(String[] message, int index) {
//        if(message[0].charAt(0) == GameLogic.jailFlag){
//            GameLogic.getInstance().getPlayer(message[1]).setInJail(true);
//            GameLogic.getInstance().getPlayer(message[1]).getToken().setLocation(Board.getInstance().getNameGivenSquare("Jail").getLocation());
//            //System.out.println("In the Jail ResponseInterpretable" + GameLogic.getInstance().getPlayer(message[1]).isInJail() + " in the location" + Board.getInstance().getNameGivenSquare("Jail").getLocation()[0] + " " + Board.getInstance().getNameGivenSquare("Jail").getLocation()[1]);
//            //UIUpdater.getInstance().setTokenLocation(message[1], Board.getInstance().getNameGivenSquare("Jail").getLocation()[0], Board.getInstance().getNameGivenSquare("Jail").getLocation()[1]);
//            UIUpdater.getInstance().setMessage(GameLogic.getInstance().getCurrentPlayer().getName() + " goes to jail!!!");
//        }else if (message[0].charAt(0) == GameLogic.goOutJailFlag){
//            GameLogic.getInstance().getCurrentPlayer().setInJail(false);
//            UIUpdater.getInstance().setMessage(GameLogic.getInstance().getCurrentPlayer().getName() + " is out of jail!!!");
//        }
    }
}
