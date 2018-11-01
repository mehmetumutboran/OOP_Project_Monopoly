package domain;

import domain.die.DiceCup;
import domain.die.Die;
import domain.player.Player;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class Monopoly {
    private ArrayList<Player> playerList;
    private Deque<Player> playerQueue;
    private DiceCup cup;

    private Monopoly() {
        playerList = new ArrayList<>();
        playerQueue = new LinkedList<>();
        cup = new DiceCup();
    }


}
