package domain;

import domain.die.Die;
import domain.player.Player;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class Monopoly {
    private ArrayList<Player> playerList;
    private Deque<Player> playerQueue;
    private Die[] die; //TODO Array or single var?

    private Monopoly() {
        playerList = new ArrayList<>();
        playerQueue = new LinkedList<>();
        die = new Die[3];
    }


    public Die[] getDie() {
        return die;
    }
}
