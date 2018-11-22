package domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.board.*;
import domain.board.specialSquares.Chance;
import domain.board.specialSquares.CommunityChest;
import domain.interpreter.*;
import domain.player.Player;
import domain.player.Token;

import java.io.IOException;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Planned as a Class that interprets received Message then updates game state
 */
public class MessageInterpreter {
    private static MessageInterpreter instance;

    private HashMap<Character, Interpreter> interpreterMap;

    private MessageInterpreter() {
        Interpreter moveInterpreter = new MoveInterpreter();
        Interpreter moneyChangeInterpreter = new MoneyChangeInterpreter();
        Interpreter buyInterpreter = new BuyInterpreter();
        Interpreter payRentInterpreter = new PayRentInterpreter();
        Interpreter queueInterpreter = new QueueInterpreter();
        Interpreter upDownInterpreter = new UpDownInterpreter();
        Interpreter tokenMovementInterpreter = new TokenMovementInterpreter();
        Interpreter rollInterpreter = new RollInterpreter();

        interpreterMap = new HashMap<>();
        interpreterMap.put(GameLogic.moveFlag, moveInterpreter);
        interpreterMap.put(GameLogic.moneyFlag, moneyChangeInterpreter);
        interpreterMap.put(GameLogic.buyFlag, buyInterpreter);
        interpreterMap.put(GameLogic.payRentFlag, payRentInterpreter);
        interpreterMap.put(GameLogic.queueFlag, queueInterpreter);
        interpreterMap.put(GameLogic.upgradeFlag, upDownInterpreter);
        interpreterMap.put(GameLogic.downgradeFlag, upDownInterpreter);
        interpreterMap.put(GameLogic.tokenFlag, tokenMovementInterpreter);
        interpreterMap.put(GameLogic.rollFlag, rollInterpreter);
    }

    public static MessageInterpreter getInstance() {
        if (instance == null)
            instance = new MessageInterpreter();
        return instance;
    }


    public synchronized void interpret(String m) {
        char flag = m.charAt(0);

        if(interpreterMap.keySet().contains(flag))
            interpreterMap.get(flag).interpret(m.substring(1).split("[|]"));

        switch (flag) {
            case GameLogic.finishTurnFlag:
                interpretFinishTurn();
                break;
            case GameLogic.closeFlag:
                interpretClose();
                break;
            case GameLogic.removeFlag:
                interpretRemove(m.substring(1));
                break;
            case GameLogic.specialSquareFlag:
                interpretSpecial(m.substring(1));
                break;
//            case GameLogic.poolFlag:
//                interpretPool(m.substring(1));
//                break;
            case GameLogic.tokenFlag:
                interpretTokenMovement(m.substring(1));
                break;
            default:
                break;
        }
    }

    private void interpretRemove(String name) {
        GameLogic.getInstance().removePlayer(name);
        UIUpdater.getInstance().removeUpdate(name);
    }

//    private void interpretPool(String message) {
//        GameLogic.getInstance().changePool(message);
//        UIUpdater.getInstance().setMessage("Pool money is increased by :  " + message + " \nCurrent pool balance is : " + Board.getInstance().getPool());
//
//    }


    private void interpretSpecial(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        String name = null;

        try {
            name = objectMapper.readValue(message, Player.class).getName();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int[] loc = GameLogic.getInstance().getPlayer(name).getToken().getLocation();
        if (Board.getInstance().getSquare(loc[0], loc[1]) instanceof Chance) {
            UIUpdater.getInstance().setMessage(name + " picked " + Board.getInstance().getChanceDeckList()[0].getName());
        } else if (Board.getInstance().getSquare(loc[0], loc[1]) instanceof CommunityChest) {
            UIUpdater.getInstance().setMessage(name + " picked " + Board.getInstance().getCommunityDeckList()[0].getName());
        }

    }

    private void interpretTokenMovement(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        String name = null;
        Token token = null;
        Player p = null;
        try {
            name = objectMapper.readValue(message, Player.class).getName();
            token = objectMapper.readValue(message, Player.class).getToken();
            p = objectMapper.readValue(message, Player.class);
        } catch (IOException e) {
            e.printStackTrace();
        }


        int xLoc = 0;
        int yLoc = 0;
        int[] newLoc = token.getLocation();

        int pIndex = GameLogic.getInstance().getPlayerList().indexOf(p);

        if (newLoc[0] == 0) {
            if (newLoc[1] >= 0 && newLoc[1] < 14) {
                if (pIndex > 6) {
                    yLoc = 90 + newLoc[1] * 59 + 15;
                    xLoc = 1240 + (pIndex - 6) * 15;
                } else {
                    yLoc = 90 + newLoc[1] * 59;
                    xLoc = 1240 + pIndex * 15;
                }
            } else if (newLoc[1] >= 14 && newLoc[1] < 28) {
                if (pIndex > 6) {
                    yLoc = 905 + (pIndex - 6) * 15;
                    xLoc = (1240 - (newLoc[1] - 14) * 89) + 44 + 15; //14
                } else {
                    yLoc = 905 + pIndex * 15;
                    xLoc = (1240 - (newLoc[1] - 14) * 89) + 44;
                }//14
            } else if (newLoc[1] >= 28 && newLoc[1] < 42) {
                if (pIndex > 6) {
                    yLoc = 905 - (newLoc[1] - 28) * 59 + 15;
                    xLoc = 30 + (pIndex - 6) * 15;
                } else {
                    yLoc = 905 - (newLoc[1] - 28) * 59;
                    xLoc = 30 + pIndex * 15;
                }
            } else if (newLoc[1] >= 42 && newLoc[1] < 56) {
                if (pIndex > 6) {
                    yLoc = 90 - (pIndex - 6) * 15;
                    xLoc = (30 + (newLoc[1] - 42) * 89) + 44 + 15; //42
                } else {
                    yLoc = 90 - pIndex * 15;
                    xLoc = (30 + (newLoc[1] - 42) * 89) + 44; //42
                }
            }
        } else if (newLoc[0] == 1) {
            if (newLoc[1] >= 0 && newLoc[1] < 10) {
                if (pIndex > 6) {
                    yLoc = 210 + newLoc[1] * 57 + 15;
                    xLoc = 1070 + (pIndex - 6) * 15;
                } else {
                    yLoc = 210 + newLoc[1] * 57;
                    xLoc = 1070 + pIndex * 15;
                }
            } else if (newLoc[1] >= 10 && newLoc[1] < 20) {
                if (pIndex > 6) {
                    yLoc = 790 + (pIndex - 6) * 15;
                    xLoc = (1070 - (newLoc[1] - 10) * 78) + 39 + 15; //10
                } else {
                    yLoc = 790 + +pIndex * 15;
                    xLoc = (1070 - (newLoc[1] - 10) * 78) + 39; //10
                }
            } else if (newLoc[1] >= 20 && newLoc[1] < 30) {
                if (pIndex > 6) {
                    yLoc = 790 - (newLoc[1] - 20) * 57 + 15;
                    xLoc = 200 + (pIndex - 6) * 15;
                } else {
                    yLoc = 790 - (newLoc[1] - 20) * 57;
                    xLoc = 200 + pIndex * 15;
                }
            } else if (newLoc[1] >= 30 && newLoc[1] < 40) {
                if (pIndex > 6) {
                    yLoc = 210 - (pIndex - 6) * 15;
                    xLoc = (200 + (newLoc[1] - 30) * 78) + 39 + 15; //30
                } else {
                    yLoc = 210 - pIndex * 15;
                    xLoc = (200 + (newLoc[1] - 30) * 78) + 39; //30
                }
            }
        } else if (newLoc[0] == 2) {
            if (newLoc[1] >= 0 && newLoc[1] < 6) {
                if (pIndex > 6) {
                    yLoc = 325 + newLoc[1] * 58 + 15;
                    xLoc = 905 + (pIndex - 6) * 15;
                } else {
                    yLoc = 325 + newLoc[1] * 58;
                    xLoc = 905 + pIndex * 15;
                }
            } else if (newLoc[1] >= 6 && newLoc[1] < 12) {
                if (pIndex > 6) {
                    yLoc = 675 + (pIndex - 6) * 15;
                    xLoc = (905 - (newLoc[1] - 6) * 75) + 37 + 15; //6
                } else {
                    yLoc = 675 + pIndex * 15;
                    xLoc = (905 - (newLoc[1] - 6) * 75) + 37; //6
                }
            } else if (newLoc[1] >= 12 && newLoc[1] < 18) {
                if (pIndex > 6) {
                    yLoc = 675 - (newLoc[1] - 12) * 58 + 15;
                    xLoc = 370 + (pIndex - 6) * 15;
                } else {
                    yLoc = 675 - (newLoc[1] - 12) * 58;
                    xLoc = 370 + pIndex * 15;
                }
            } else if (newLoc[1] >= 18 && newLoc[1] < 24) {
                if (pIndex > 6) {
                    yLoc = 325 - (pIndex - 6) * 15;
                    xLoc = (370 + (newLoc[1] - 18) * 75) + 37 + 15; //18
                } else {
                    yLoc = 325 - pIndex * 15;
                    xLoc = (370 + (newLoc[1] - 18) * 75) + 37; //18
                }
            }
        }
        UIUpdater.getInstance().setTokenLocation(name, xLoc, yLoc);
    }

    private void interpretUpDownGrade(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        String name = null;
        DeedSquare square = null;
        int balance = 0;

        String playerMessage = message.substring(1, message.indexOf('~'));
        String squareMessage = message.substring(message.indexOf('~') + 1);

        try {
            name = objectMapper.readValue(playerMessage, Player.class).getName();
            square = objectMapper.readValue(squareMessage, DeedSquare.class);
            balance = objectMapper.readValue(playerMessage, Player.class).getBalance();
        } catch (IOException e) {
            e.printStackTrace();
        }
        GameLogic.getInstance().getPlayer(name).setBalance(balance);
        if (Board.getInstance().getNameGivenSquare(square.getName()) instanceof Property) {
            try {
                square = objectMapper.readValue(squareMessage, Property.class);
                ((Property) Board.getInstance().getNameGivenSquare(square.getName())).setBuildingList(((Property) square).getBuildingList());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                square = objectMapper.readValue(squareMessage, Railroad.class);
                ((Railroad) Board.getInstance().getNameGivenSquare(square.getName())).setHasDepot(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        int index;
        if (square instanceof Railroad) {
            index = GameLogic.getInstance().getPlayer(name).getOwnedRailroads().indexOf(square);
            GameLogic.getInstance().getPlayer(name).getOwnedRailroads().get(index).setHasDepot(true);

        } else if (square instanceof Property) {
            index = GameLogic.getInstance().getPlayer(name).getOwnedProperties().indexOf(square);
            GameLogic.getInstance().getPlayer(name).getOwnedProperties().get(index).setBuildingList(((Property) square).getBuildingList());
        }
        if (message.charAt(0) == GameLogic.upgradeFlag)
            UIUpdater.getInstance().setMessage(name + " upgraded " + square);
        else
            UIUpdater.getInstance().setMessage(name + " downgraded " + square);

    }

    private void interpretMoneyChange(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        String name;
        String player = message.substring(message.indexOf('{'));
        Player received = null;
        int changedMoney = Integer.parseInt(message.substring(0, message.indexOf('{')));
        try {
            received = objectMapper.readValue(player, Player.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert received != null;
        name = received.getName();
        assert received.getName() != null;
        if (!name.equals(GameLogic.getInstance().getPlayerList().get(0).getName()) && !(received.getReadiness().equals("Bot") && GameLogic.getInstance().getPlayerList().get(0).getReadiness().equals("Host"))) {
            int money = changedMoney + GameLogic.getInstance().getPlayer(name).getBalance();
            GameLogic.getInstance().getPlayer(name).setBalance(money);
        }
        if (changedMoney > 0) UIUpdater.getInstance().setMessage(name + " 's money increased by " + changedMoney);
        else UIUpdater.getInstance().setMessage(name + " 's money decreased by " + (-changedMoney));
    }

    private void interpretMove(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        int[] location = new int[2];
        String name = null;
        try {
            location = objectMapper.readValue(message, Player.class).getToken().getLocation();
            name = objectMapper.readValue(message, Player.class).getName();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(name + " in the location " + Arrays.toString(location));

        GameLogic.getInstance().getPlayer(name).getToken().setLocation(location);

        UIUpdater.getInstance().setMessage(name + " moved to " + Board.getInstance().getSquare(location[0], location[1]).getName()); //TODO Mrmonopoly
    }

    private void interpretClose() {
        UIUpdater.getInstance().close();
    }

    private void interpretQueue(String q) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        try {
            String[] players = objectMapper.readValue(q, String[].class);
//            System.out.println(Arrays.toString(objectMapper.readValue(q, Player[].class)));
            Deque<String> temp = new LinkedList<>();
            for (String player : players) {
                temp.addLast(player);
            }
            GameLogic.getInstance().setPlayers(temp);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Bots play on only host's program
        if (GameLogic.getInstance().getPlayerList().get(0).getReadiness().equals("Host")) {

            // Check for bots if they starts the game.
            for (Player player : GameLogic.getInstance().getPlayerList()) {
                if (player.getReadiness().equals("Bot") && ((RandomPlayer) player).checkTurn()) {
                    break;
                }
            }
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

//        GameLogic.getInstance().getPlayer(name).getToken().setLocation(location);

        UIUpdater.getInstance().setMessage(name + " rolled " + faceValues[0] + " " + faceValues[1] + " " + faceValues[2]); //TODO Mrmonopoly
    }


    private void interpretBuy(String message) {
        /*may be wrong !!!! */
        System.out.println("in interpret buy flag");

        ObjectMapper objectMapper2 = new ObjectMapper();
        objectMapper2.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        int[] location;
        String name = null;
        Square square = null;
        try {

            name = objectMapper2.readValue(message, Player.class).getName();
            System.out.println("name");

            location = objectMapper2.readValue(message, Player.class).getToken().getLocation();
            /* sq will be done for other classes */
            System.out.println("location");
            square = Board.getInstance().getSquare(location[0], location[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (square instanceof SpecialSquareStrategy) return;
        if (square == null) return;

        GameLogic.getInstance().getPlayer(name).decreaseMoney(((DeedSquare) square).getBuyValue());

        if (square instanceof Property) {
            GameLogic.getInstance().getPlayer(name).getOwnedProperties().add(((Property) square));
            UIUpdater.getInstance().setMessage(name + " bought " + square); //TODO Mrmonopoly
            ((Property) square).setOwner(GameLogic.getInstance().getPlayer(name));


        } else if (square instanceof Railroad) {
            GameLogic.getInstance().getPlayer(name).getOwnedRailroads().add(((Railroad) square));
            UIUpdater.getInstance().setMessage(name + " bought " + square); //TODO Mrmonopoly
            ((Railroad) square).setOwner(GameLogic.getInstance().getPlayer(name));


        } else if (square instanceof Utility) {
            GameLogic.getInstance().getPlayer(name).getOwnedUtilities().add(((Utility) square));
            UIUpdater.getInstance().setMessage(name + " bought " + square); //TODO Mrmonopoly
            ((Utility) square).setOwner(GameLogic.getInstance().getPlayer(name));
        }


    }


    private void interpretRent(String message) {
        System.out.println("in interpretRent");

        ObjectMapper objectMapper3 = new ObjectMapper();
        objectMapper3.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        int[] location;
        String name = null;
        Square square = null;
        int rentVal = 0;
        try {
            name = objectMapper3.readValue(message, Player.class).getName();
            location = objectMapper3.readValue(message, Player.class).getToken().getLocation();
            square = Board.getInstance().getSquare(location[0], location[1]);
            rentVal = ((DeedSquare) square).getRent();
            /* sq will be done for other classes */
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*payer*/
        if (square instanceof SpecialSquareStrategy) return;
        if (square == null) return;
        GameLogic.getInstance().getPlayer(name).decreaseMoney(rentVal);
        /*taker*/

        if (square instanceof Property) {
            GameLogic.getInstance().getPlayer(((Property) square).getOwner().getName()).increaseMoney(rentVal);
            UIUpdater.getInstance().setMessage(name + " paid payRent " + rentVal + " dollars to " + ((DeedSquare) square).getOwner().getName()); //TODO Mrmonopoly

        } else if (square instanceof Railroad) {
            GameLogic.getInstance().getPlayer(((Railroad) square).getOwner().getName()).increaseMoney(rentVal);
            UIUpdater.getInstance().setMessage(name + " paid payRent " + rentVal + " dollars to " + ((DeedSquare) square).getOwner().getName()); //TODO Mrmonopoly

        } else if (square instanceof Utility) {
            GameLogic.getInstance().getPlayer(((Utility) square).getOwner().getName()).increaseMoney(rentVal);
            UIUpdater.getInstance().setMessage(name + " paid payRent " + rentVal + " dollars to " + ((DeedSquare) square).getOwner().getName()); //TODO Mrmonopoly
        }


    }


}
