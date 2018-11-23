package domain.controller;

import domain.player.Player;
import network.client.clientFacade.ClientFacade;

public class ConnectGameInterpreter {
    private static ConnectGameInterpreter ourInstance;
    private boolean isStarted = false;

    public static ConnectGameInterpreter getInstance() {
        if(ourInstance==null)
            ourInstance = new ConnectGameInterpreter();
        return ourInstance;
    }

    private ConnectGameInterpreter() {
    }


    public String messageHandler(String message){
        if(message==null || message.isEmpty()) return null;
        String[] info = message.split("[+]"); // flag+arg1+arg2+...
        char flag = message.charAt(0);
        switch (flag) {
            case 'E':
                if(!isCurrentPlayerHost())
                return "E";
                else return null;
            case 'X':
                if(isMessageHost(message)) return "XH";
                else return "XC";
            case 'C':
                return checkNewColor(info);
            case 'P':
                return checkNewPlayer(info);
            case 'R':
                return checkNewReadiness(info);
            case 'S':
                if(!this.isStarted) {
                    this.isStarted = true;
                    if(!MonopolyGameController.getInstance().getMyself().getReadiness().equals("Host"))
                        MonopolyGameController.getInstance().checkReadiness();
                    return "S";
                }
                else return null;
            default:
                return null;
        }
    }

    private boolean isCurrentPlayerHost(){
        return MonopolyGameController.getInstance().getMyself().getReadiness().equals("Host");
    }

    private boolean isMessageHost(String message){
        return MonopolyGameController.getInstance().
                getPlayerFromList(message.substring(1)).getReadiness().equals("Host");
    }

    private String checkNewColor(String[] text){
        // text[0] = flag, text[1] = name, text[2] = color
        if (!MonopolyGameController.getInstance().getPlayerFromList(text[1])
                .getToken().getColor().equals(text[2])) {
            MonopolyGameController.getInstance().changePlayerColor(MonopolyGameController.getInstance()
                    .getPlayerList().indexOf(MonopolyGameController.getInstance().getPlayerFromList(text[1])), text[2]);
            return "C";
        }
        return null;
    }

    private String checkNewPlayer(String[] text){
        // text[0] flag, text[1] sent player name text[2] player readiness text[3] player color
        String myPlayerName = MonopolyGameController.getInstance().getMyself().getName();
        String myReadiness = MonopolyGameController.getInstance().getMyself().getReadiness();
        String myColor = MonopolyGameController.getInstance().getMyself().getToken().getColor();
        if (MonopolyGameController.getInstance().getPlayerFromList(text[1])==null) { // If the player didn't existed in the list
            ClientFacade.getInstance().send(myPlayerName,"P+"+myPlayerName+"+"+myReadiness+"+"+myColor);
            if (MonopolyGameController.getInstance().getMyself().getReadiness().equals("Host")) { // If the checker is the Host
                MonopolyGameController.getInstance().getPlayerList().stream().filter(p -> p.getReadiness().equals("Bot"))
                        .forEach(p -> ClientFacade.getInstance()
                                .send(p.getName(),"P+"+p.getName()+"+"+p.getReadiness()+"+"+p.getToken().getColor())); // Send bot info
            }
            Player newPlayer = new Player(text[1]); // Add the joining player
            newPlayer.setReadiness(text[2]);
            newPlayer.getToken().setColor(text[3]);
            MonopolyGameController.getInstance().addPlayer(newPlayer);
            return "P";
        }
        return null;
    }

    private String checkNewReadiness(String[] text){
        // text[0] flag, text[1] player name, text[2] new readiness
        if (!MonopolyGameController.getInstance().getPlayerFromList(text[1])  // Readiness changed
                .getReadiness().equals(text[2])) {
            MonopolyGameController.getInstance()
                    .changePlayerReadiness(MonopolyGameController.getInstance().getPlayerList()
                            .indexOf(MonopolyGameController.getInstance().getPlayerFromList(text[1])));
            return "R";
        }
        return null;
    }


}
