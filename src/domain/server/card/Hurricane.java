package domain.server.card;

import domain.client.ClientCommunicationHandler;
import domain.client.UIUpdater;
import domain.server.board.Board;
import domain.server.board.Property;
import domain.server.player.Player;
import domain.util.Flags;
import domain.util.GameInfo;
import network.client.clientFacade.ClientFacade;

import javax.swing.*;
import javax.swing.text.StyledEditorKit;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Hurricane extends ChanceCard{

    public Hurricane(String name) {
        super(name);
    }

    @Override
    public boolean doAction(String name) {
        ArrayList<Player> players = hurricaneList();


        int choice1 = JOptionPane.showOptionDialog(null, //Component parentComponent
                "Which player do you want to pick?", //Object message,
                "Hurricane", //String title
                JOptionPane.YES_NO_OPTION, //int optionType
                JOptionPane.INFORMATION_MESSAGE, //int messageType
                null, //Icon icon,
                players.stream().map(Player::getName).toArray(), //Object[] options,
                null);//Object initialValue

        ArrayList<String> colors = new ArrayList<>();

        for(Property p : players.get(choice1).getOwnedProperties()) {
            if(!colors.contains(p.getColor()) && p.hasUpgradedSquareInGroup()) {
                colors.add(p.getColor());
            }
        }

        int choice2 = JOptionPane.showOptionDialog(null, //Component parentComponent
                "Which color do you want to pick?", //Object message,
                "Hurricane", //String title
                JOptionPane.YES_NO_OPTION, //int optionType
                JOptionPane.INFORMATION_MESSAGE, //int messageType
                null, //Icon icon,
                colors.toArray(), //Object[] options,
                null);//Object initialValue

        for(Property p : Board.getInstance().getSameColoredSquares(colors.get(choice2))) {
          if(p.isUpgraded() && players.get(choice1).getOwnedProperties().contains(p)){
              ClientCommunicationHandler.getInstance().sendRequest(Flags.getFlag("Downgrade"), ClientFacade.getInstance().getUsername(), p.getLocation());
          }
        }

        return true;
    }

    private ArrayList<Player> hurricaneList() {
        ArrayList<Player> players = new ArrayList<>();
        for(Player player : GameInfo.getInstance().getPlayerList()) {
            if(!player.getName().equals(ClientFacade.getInstance().getUsername()) && hasUpgrade(player)){
                players.add(player);
            }
        }
        return players;
    }

    private boolean hasUpgrade(Player player) {
        for(Property property : player.getOwnedProperties()) {
            if(property.hasUpgradedSquareInGroup()){
                return true;
            }
        }
        return false;
    }
}
