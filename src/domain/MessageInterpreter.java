package domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.board.Board;
import domain.board.Property;
import domain.controller.GameCommunicationHandler;
import domain.player.Player;
import gui.controlDisplay.PlayerLabel;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Planned as a Class that interprets received Message then updates game state
 */
public class MessageInterpreter {
    private static MessageInterpreter instance;


    private MessageInterpreter() {

    }

    public static MessageInterpreter getInstance() {
        if(instance == null)
            instance = new MessageInterpreter();
        return instance;
    }


    public void interpret(String m) {
        System.out.println("in interpreter");

        char flag = m.charAt(0);
        switch (flag) {
            case GameLogic.rollFlag:
                interpretRoll(m.substring(1));
                break;
            case GameLogic.buyFlag:
                interpretBuy(m.substring(1));
                break;
            case GameLogic.payRentFlag:
                interpretRent(m.substring(1));
                break;
            case GameLogic.finishTurnFlag:
                interpretFinishTurn();
                break;
            case GameLogic.bonusFlag:
                break;
            case GameLogic.queueFlag:
                interpretQueue(m.substring(1));
                break;
            case GameLogic.closeFlag:
                interpretClose();
                break;
            default:
                break;
        }
    }

    private void interpretClose() {
        UIUpdater.getInstance().close();
    }

    private void interpretQueue(String q) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        try {
            Player[] players = objectMapper.readValue(q, Player[].class);
            System.out.println(Arrays.toString(objectMapper.readValue(q, Player[].class)));
            Deque<Player> temp = new LinkedList<>();
            for (int i = 0; i < players.length; i++) {
                temp.addLast(players[i]);
            }
            GameLogic.getInstance().setPlayers(temp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        UIUpdater.getInstance().turnUpdate();
    }

    private void interpretFinishTurn() {
        GameLogic.getInstance().switchTurn();

        UIUpdater.getInstance().turnUpdate();
    }

    private void interpretRoll(String message) {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        int[] location = new int[2];
        String name = null;
        int[] faceValues = new int[3];
        try {
            location = objectMapper.readValue(message, Player.class).getToken().getLocation();
            name = objectMapper.readValue(message, Player.class).getName();
            faceValues = objectMapper.readValue(message, Player.class).getFaceValues();
        } catch (IOException e) {
            e.printStackTrace();
        }

        GameLogic.getInstance().getPlayer(name).getToken().setLocation(location);

        UIUpdater.getInstance().setMessage(name + " rolled " + faceValues[0] + " " + faceValues[1] + " " + faceValues[2]); //TODO Mrmonopoly
    }



    public void interpretBuy(String message){
        /*may be wrong !!!! */
        System.out.println("in interpret buy flag");

        ObjectMapper objectMapper2 = new ObjectMapper();
        objectMapper2.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        int[] location = null;
        String name = null;
        Property sq = null;
        int money = 0;
        int newBalance = 0;
        try {
            System.out.println("inside try catch");

            name = objectMapper2.readValue(message, Player.class).getName();
            System.out.println("name");

            location = objectMapper2.readValue(message, Player.class).getToken().getLocation();
            /* sq will be done for other classes */
            System.out.println("location");

            sq = (Property) Board.getInstance().getSquare(location[0] , location[1]);
            System.out.println("square");

            money = sq.getBuyValue();
            System.out.println("money");

            newBalance = GameLogic.getInstance().getPlayer(name).getBalance()-money;
            System.out.println("new balamce");

        } catch (IOException e) {
            e.printStackTrace();
        }

        GameLogic.getInstance().getPlayer(name).setBalance(newBalance);
        System.out.println("set balance");

        GameLogic.getInstance().getPlayer(name).getOwnedProperties().add(sq);
        System.out.println("added property");

        ((Property) Board.getInstance().getSquareList()[location[0] ][location[1]]).setOwner(GameLogic.getInstance().getPlayer(name));

        UIUpdater.getInstance().setMessage(name + " bought " + sq ); //TODO Mrmonopoly
        System.out.println("UI updated");




    }




    public void interpretRent(String message){
        System.out.println("in interpretRent");

        ObjectMapper objectMapper3 = new ObjectMapper();
        objectMapper3.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        int[] location = null;
        String name = null;
        Property sq = null;
        int money = 0;
        int newBalance = 0;
        int newBalanceTaker=0;
        try {
            name = objectMapper3.readValue(message, Player.class).getName();

            location = objectMapper3.readValue(message, Player.class).getToken().getLocation();
            /* sq will be done for other classes */

            sq = (Property) Board.getInstance().getSquare(location[0] , location[1]);

            money = sq.getRent();

            newBalance = GameLogic.getInstance().getPlayer(name).getBalance()-money;
            newBalanceTaker = GameLogic.getInstance().getPlayer(((Property) Board.getInstance().getSquareList()[location[0] ][location[1]]).getOwner().getName()).getBalance()+money;


        } catch (IOException e) {
            e.printStackTrace();
        }
        /*payer*/
        GameLogic.getInstance().getPlayer(name).setBalance(newBalance);
        /*taker*/
        GameLogic.getInstance().getPlayer(((Property) Board.getInstance().getSquareList()[location[0] ][location[1]]).getOwner().getName()).setBalance(newBalanceTaker);



        UIUpdater.getInstance().setMessage(name + " paid rent " + money + " dollars to " + sq.getOwner().getName() ); //TODO Mrmonopoly




    }


}
