package domain.client;

import domain.server.controller.ConnectGameHandler;
import domain.util.Flags;
import network.client.clientFacade.ClientFacade;

public class PlayerActionController {
    private static PlayerActionController ourInstance;


    public static PlayerActionController getInstance() {
        if (ourInstance == null)
            ourInstance = new PlayerActionController();
        return ourInstance;
    }

    private PlayerActionController() {
    }


    public void roll() {
        ClientCommunicationHandler.getInstance().sendRequest(Flags.getFlag("Roll"), ClientFacade.getInstance().getUsername());
    }


//    public void finishTurn() {
//        GameLogic.getInstance().finishTurn();
//    }
//    // public void upgrade() {GameLogic.getInstance().upgrade(); }
//
//    //public void downgrade(){ GameLogic.getInstance().downgrade(); }
//
//    public boolean buy() {
//        System.out.println("in player action controller");
//        return (GameLogic.getInstance().buy());
//
//    }
//
//    public boolean rent() {
//        System.out.println("in player action controller");
//        return (GameLogic.getInstance().payRent());
//
//    }

    public void startGame() {
        ClientCommunicationHandler.getInstance().sendRequest(Flags.getFlag("Start"), ClientFacade.getInstance().getUsername());
    }

    public void join(String username, String ip, int port) {
        if (ConnectGameHandler.getInstance().connectClient(username, ip, port))
            ClientCommunicationHandler.getInstance().sendRequest(Flags.getFlag("AddPlayer"), ClientFacade.getInstance().getUsername());
    }

    public void host(String username, int port, boolean isMulti) {
        if (ConnectGameHandler.getInstance().connectHost(port, isMulti))
            join(username, "localhost", port);
    }

    public void changePlayerColor(String color) {
        ClientCommunicationHandler.getInstance().sendRequest(Flags.getFlag("Color"), ClientFacade.getInstance().getUsername(), color);
    }

//    public void changePlayerReadiness(int index) { // TODO DON'T USE INDEX
//        playerList.get(index).setReadiness();
//        if (playerList.size() > 1) {
//            ConnectGameHandler.getInstance().sendReadinessChange(playerList.get(index));
//        }
//        publishPlayerListEvent();
//    }

    public void changePlayerReadiness() {
        ClientCommunicationHandler.getInstance().sendRequest(Flags.getFlag("Readiness"), ClientFacade.getInstance().getUsername());
    }

    public void save() {
        ClientCommunicationHandler.getInstance().sendRequest(Flags.getFlag("Save"), ClientFacade.getInstance().getUsername());
    }

    public void pause() {

    }

    public void upgradeLabel(int location[]) {
        System.out.println("In player action controller");
        ClientCommunicationHandler.getInstance().sendRequest(Flags.getFlag("Upgrade"), ClientFacade.getInstance().getUsername(), location);
    }
    public void downgradeLabel(int location[]){
        System.out.println("In player action controller");
        ClientCommunicationHandler.getInstance().sendRequest(Flags.getFlag("Downgrade"), ClientFacade.getInstance().getUsername(),location);
    }
    public void upgrade (){
        ClientCommunicationHandler.getInstance().sendRequest(Flags.getFlag("LabelLighter"), ClientFacade.getInstance().getUsername());
    }
}
