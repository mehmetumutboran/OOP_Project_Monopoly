package domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.board.Board;
import domain.board.Property;
import domain.board.Railroad;
import domain.board.Utility;
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
        Property sq1 = null;
        Railroad sq2 = null;
        Utility sq3 = null;
        int money = 0;
        int newBalance = 0;
        try {

            name = objectMapper2.readValue(message, Player.class).getName();
            System.out.println("name");

            location = objectMapper2.readValue(message, Player.class).getToken().getLocation();
            /* sq will be done for other classes */
            System.out.println("location");

            if(Board.getInstance().getSquare(location[0] , location[1]) instanceof Property){
            sq1 = (Property) Board.getInstance().getSquare(location[0] , location[1]);
                money = sq1.getBuyValue();
                newBalance = GameLogic.getInstance().getPlayer(name).getBalance()-money;

            }
            else if(Board.getInstance().getSquare(location[0] , location[1]) instanceof Railroad){
                sq2 = (Railroad)Board.getInstance().getSquare(location[0] , location[1]);
                money = sq2.getBuyValue();
                newBalance = GameLogic.getInstance().getPlayer(name).getBalance()-money;

            }
            else if(Board.getInstance().getSquare(location[0] , location[1]) instanceof Utility){
                sq3 = (Utility)Board.getInstance().getSquare(location[0] , location[1]);
                money = sq3.getBuyValue();
                newBalance = GameLogic.getInstance().getPlayer(name).getBalance()-money;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        GameLogic.getInstance().getPlayer(name).setBalance(newBalance);

        if(Board.getInstance().getSquare(location[0] , location[1]) instanceof Property){
            GameLogic.getInstance().getPlayer(name).getOwnedProperties().add(sq1);
            UIUpdater.getInstance().setMessage(name + " bought " + sq1 ); //TODO Mrmonopoly
            ((Property) Board.getInstance().getSquareList()[location[0] ][location[1]]).setOwner(GameLogic.getInstance().getPlayer(name));


        }
        else if(Board.getInstance().getSquare(location[0] , location[1]) instanceof Railroad){
            GameLogic.getInstance().getPlayer(name).getOwnedRailroads().add(sq2);
            UIUpdater.getInstance().setMessage(name + " bought " + sq2 ); //TODO Mrmonopoly
            ((Railroad) Board.getInstance().getSquareList()[location[0] ][location[1]]).setOwner(GameLogic.getInstance().getPlayer(name));


        }
        else if(Board.getInstance().getSquare(location[0] , location[1]) instanceof Utility){
            GameLogic.getInstance().getPlayer(name).getOwnedUtilities().add(sq3);
            UIUpdater.getInstance().setMessage(name + " bought " + sq3 ); //TODO Mrmonopoly
            ((Utility) Board.getInstance().getSquareList()[location[0] ][location[1]]).setOwner(GameLogic.getInstance().getPlayer(name));

        }






    }




    public void interpretRent(String message){
        System.out.println("in interpretRent");

        ObjectMapper objectMapper3 = new ObjectMapper();
        objectMapper3.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        int[] location = null;
        String name = null;
        Property sq1 = null;
        Railroad sq2 = null;
        Utility sq3 = null;

        int money = 0;
        int newBalance = 0;
        int newBalanceTaker=0;
        try {
            name = objectMapper3.readValue(message, Player.class).getName();

            location = objectMapper3.readValue(message, Player.class).getToken().getLocation();
            /* sq will be done for other classes */
            if(Board.getInstance().getSquare(location[0], location[1]) instanceof  Property){
            sq1 = (Property) Board.getInstance().getSquare(location[0] , location[1]);
                money = sq1.getRent();
                newBalance = GameLogic.getInstance().getPlayer(name).getBalance()-money;
                newBalanceTaker = GameLogic.getInstance().getPlayer(((Property) Board.getInstance().getSquareList()[location[0] ][location[1]]).getOwner().getName()).getBalance()+money;

            }

            else if(Board.getInstance().getSquare(location[0], location[1]) instanceof  Railroad){
                sq2 = (Railroad) Board.getInstance().getSquare(location[0] , location[1]);
                money = sq2.getRent();
                newBalance = GameLogic.getInstance().getPlayer(name).getBalance()-money;
                newBalanceTaker = GameLogic.getInstance().getPlayer(((Railroad) Board.getInstance().getSquareList()[location[0] ][location[1]]).getOwner().getName()).getBalance()+money;

            }
            else if(Board.getInstance().getSquare(location[0], location[1]) instanceof  Utility){
                sq3 = (Utility) Board.getInstance().getSquare(location[0] , location[1]);
                money = sq3.getRent();
                newBalance = GameLogic.getInstance().getPlayer(name).getBalance()-money;
                newBalanceTaker = GameLogic.getInstance().getPlayer(((Utility) Board.getInstance().getSquareList()[location[0] ][location[1]]).getOwner().getName()).getBalance()+money;

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        /*payer*/
        GameLogic.getInstance().getPlayer(name).setBalance(newBalance);
        /*taker*/

        if(Board.getInstance().getSquare(location[0], location[1]) instanceof  Property){
            GameLogic.getInstance().getPlayer(((Property) Board.getInstance().getSquareList()[location[0] ][location[1]]).getOwner().getName()).setBalance(newBalanceTaker);
            UIUpdater.getInstance().setMessage(name + " paid rent " + money + " dollars to " + sq1.getOwner().getName() ); //TODO Mrmonopoly

        }

        else if(Board.getInstance().getSquare(location[0], location[1]) instanceof  Railroad){
            GameLogic.getInstance().getPlayer(((Railroad) Board.getInstance().getSquareList()[location[0] ][location[1]]).getOwner().getName()).setBalance(newBalanceTaker);
            UIUpdater.getInstance().setMessage(name + " paid rent " + money + " dollars to " + sq2.getOwner().getName() ); //TODO Mrmonopoly

        }
        else if(Board.getInstance().getSquare(location[0], location[1]) instanceof  Utility){
            GameLogic.getInstance().getPlayer(((Utility) Board.getInstance().getSquareList()[location[0] ][location[1]]).getOwner().getName()).setBalance(newBalanceTaker);
            UIUpdater.getInstance().setMessage(name + " paid rent " + money + " dollars to " + sq3.getOwner().getName() ); //TODO Mrmonopoly

        }




    }


}
