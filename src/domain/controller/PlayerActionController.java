package domain.controller;

import domain.die.DiceCup;
import domain.player.Player;

import javax.xml.stream.FactoryConfigurationError;
import java.util.Deque;

public class PlayerActionController {
    private static PlayerActionController ourInstance;

    private Deque<Player> players;

    public static PlayerActionController getInstance() {
        if(ourInstance == null)
            ourInstance = new PlayerActionController();
        return ourInstance;
    }

    private PlayerActionController() {
    }

    public void setPlayers(Deque<Player> players){
        this.players = players;
    }

    public void roll() {
//        players.peekFirst().roll();
//        int[] faces = DiceCup.getFaces();
//        checkThirdDouble();
//        checkJail();
//        checkTriple();
//        if(faces[3] != 7 || faces[3] != 8){
//          move(DiceCup.getTotalFaceValue);
//        }
//        checkMrMonopoly();
//        checkBus();
        GameCommunicationHandler.getInstance().sendAction('R');
    }

    private void checkJail() {
    }

    private void checkBus() {
    }

    private void checkMrMonopoly() {
    }

    private void checkTriple() {
    }

    private void checkThirdDouble() {
    }

    public Deque<Player> getPlayers() {
        return players;
    }
}
