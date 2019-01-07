package domain.server.card.chanceDeck;

import domain.client.ClientCommunicationHandler;
import domain.server.board.Board;
import domain.server.board.Property;
import domain.server.card.ChanceCard;
import domain.server.player.Player;
import domain.util.Flags;
import domain.util.GameInfo;
import network.client.clientFacade.ClientFacade;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class Hurricane extends ChanceCard {

    public Hurricane(String name) {
        super(name);
    }

    @Override
    public boolean doAction(String name) {
        ArrayList<Player> players = hurricaneList(name);

        if (players.isEmpty()) { // No player has upgrades
            ClientCommunicationHandler.getInstance().sendRequest(Flags.getFlag("Hurricane"), name, "fail");

            return false;
        }

        if (GameInfo.getInstance().isBot(name)) {
            hurricaneForBot(players, name);
        } else {
            hurricaneForPlayer(players);
        }

        return true;
    }

    private void hurricaneForBot(ArrayList<Player> players, String name) {
        Random random = new Random();
        int randPlayer = random.nextInt(players.size());

        ArrayList<String> colors = new ArrayList<>();

        for (Property p : players.get(randPlayer).getOwnedProperties()) {
            if (!colors.contains(p.getColor()) && p.hasUpgradedSquareInGroup()) {
                colors.add(p.getColor());
            }
        }

        ArrayList<String> hurricaneSquareNames = new ArrayList<>();

        int randColor = random.nextInt(colors.size());

        for (Property p : Board.getInstance().getSameColoredSquares(colors.get(randColor))) {
            if (p.isUpgraded() && players.get(randColor).getOwnedProperties().contains(p)) {
                hurricaneSquareNames.add(p.getName());
            }
        }

        ClientCommunicationHandler.getInstance().sendRequest(Flags.getFlag("Hurricane"), name, hurricaneSquareNames.toString());


    }

    private void hurricaneForPlayer(ArrayList<Player> players) {
        // At least a player has upgrades
        int choice1 = JOptionPane.showOptionDialog(null, //Component parentComponent
                "Which player do you want to pick?", //Object message,
                "Hurricane", //String title
                JOptionPane.YES_NO_OPTION, //int optionType
                JOptionPane.INFORMATION_MESSAGE, //int messageType
                null, //Icon icon,
                players.stream().map(Player::getName).toArray(), //Object[] options,
                null);//Object initialValue

        ArrayList<String> colors = new ArrayList<>();

        for (Property p : players.get(choice1).getOwnedProperties()) {
            if (!colors.contains(p.getColor()) && p.hasUpgradedSquareInGroup()) {
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

        ArrayList<String> hurricaneSquareNames = new ArrayList<>();
        for (Property p : Board.getInstance().getSameColoredSquares(colors.get(choice2))) {
            if (p.isUpgraded() && players.get(choice1).getOwnedProperties().contains(p)) {
                hurricaneSquareNames.add(p.getName());
            }
        }

        ClientCommunicationHandler.getInstance().sendRequest(Flags.getFlag("Hurricane"), ClientFacade.getInstance().getUsername(), hurricaneSquareNames.toString());
    }

    private ArrayList<Player> hurricaneList(String name) {
        ArrayList<Player> players = new ArrayList<>();
        for (Player player : GameInfo.getInstance().getPlayerList()) {
            if (!player.getName().equals(name) && hasUpgrade(player)) {
                players.add(player);
            }
        }
        return players;
    }

    private boolean hasUpgrade(Player player) {
        for (Property property : player.getOwnedProperties()) {
            if (property.hasUpgradedSquareInGroup()) {
                return true;
            }
        }
        return false;
    }
}
