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

    private MonopolyGameController() {
        playerList = new ArrayList<>();
        playerQueue = new LinkedList<>();
        cup = new DiceCup();
    }


}
