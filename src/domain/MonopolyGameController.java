package domain;

import domain.die.DiceCup;
import domain.player.Player;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class MonopolyGameController {
    private ArrayList<Player> playerList;
    private Deque<Player> playerQueue;
    private DiceCup cup;

    private static MonopolyGameController monopolyGameController;

    private MonopolyGameController() {
        playerList = new ArrayList<>();
        playerQueue = new LinkedList<>();
        cup = new DiceCup();
    }

    public boolean addPlayer(String username){
        return playerList.add(new Player(username));
    }

    public static MonopolyGameController getInstance(){
        if(monopolyGameController == null){
            monopolyGameController = new MonopolyGameController();
        }

        return monopolyGameController;
    }

}
