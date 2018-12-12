package domain.client;

import domain.server.GameLogic;
import domain.server.controller.ConnectGameHandler;
import domain.util.Flags;
import domain.util.GameInfo;
import network.client.Client;
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

    public void checkMrMonopoly() {
        ClientCommunicationHandler.getInstance().sendRequest(Flags.getFlag("MrMonopoly") , ClientFacade.getInstance().getUsername());

    }
    public void finishTurn() {

//        String name = GameInfo.getInstance().getCurrentPlayer();
//        if( !(GameLogic.getInstance().checkMrMonopoly(name)
//               && GameLogic.getInstance().isMrMonopolyChecked())) {
            ClientCommunicationHandler.getInstance().sendRequest(Flags.getFlag("Finish"), ClientFacade.getInstance().getUsername());
 //           GameLogic.getInstance().setMrMonopolyChecked(false);
//        }

    }
//    // public void upgrade() {GameLogic.getInstance().upgrade(); }
//
//    //public void downgrade(){ GameLogic.getInstance().downgrade(); }
//
    public void buy() {
        System.out.println("in player action controller");
        ClientCommunicationHandler.getInstance().sendRequest(Flags.getFlag("Buy"), ClientFacade.getInstance().getUsername());

    }
//
    public void rent() {
        System.out.println("in player action controller");
        //      return (GameLogic.getInstance().payRent());
        ClientCommunicationHandler.getInstance().sendRequest(Flags.getFlag("PayRent"), ClientFacade.getInstance().getUsername());

    }

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
        ClientCommunicationHandler.getInstance().sendRequest(Flags.getFlag("Pause"), ClientFacade.getInstance().getUsername());

    }

    public void drawCard() {
        ClientCommunicationHandler.getInstance().sendRequest(Flags.getFlag("Draw"), ClientFacade.getInstance().getUsername());
    }

    public void resume() {
        ClientCommunicationHandler.getInstance().sendRequest(Flags.getFlag("Resume"), ClientFacade.getInstance().getUsername());
    }
}
