package domain.server.interpreter;

import domain.server.board.Property;
import domain.server.board.Railroad;
import domain.server.board.Square;
import domain.server.controller.ServerCommunicationHandler;
import domain.util.Flags;
import domain.util.GameInfo;
import domain.util.MessageConverter;

import java.util.ArrayList;
import java.util.stream.Collectors;


public class LabelLighterRequestInterpreter implements RequestInterpretable {

    @Override
    public void interpret(String[] message, int index) {

        String name = message[1];
        String actionType = message[2];
        ArrayList<String> sq = new ArrayList<>();

        if (actionType.equals("UP")) {
            sq = upgradeAction(name);
        } else if (actionType.equals("DOWN")) {
            sq = downgradeAction(name);
        } else if (actionType.equals(String.valueOf(Flags.getFlag("Mortgage")))) {
            sq = mortgageAction(name);
        } else if (actionType.equals(String.valueOf(Flags.getFlag("Unmortgage")))) {
            sq = unmortgageAction(name);
        }


        String[] squares = sq.toArray(new String[0]);

        if (sq.isEmpty()) {
            //JOPTION PANE WARN THE PLAYER THAT HE/SHE DOES NOT HAVE ANY PLACE TO PERFORM ACTIONTYPE
        } else
            ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("LabelLighter"), index, MessageConverter.convertArrayToString(squares), actionType);
    }

    private ArrayList<String> mortgageAction(String name) {
        ArrayList<String> sq = new ArrayList<>();
        sq.addAll(GameInfo.getInstance().getPlayer(name).getOwnedProperties().stream().map(Square::getName).collect(Collectors.toList()));
        sq.addAll(GameInfo.getInstance().getPlayer(name).getOwnedUtilities().stream().map(Square::getName).collect(Collectors.toList()));
        sq.addAll(GameInfo.getInstance().getPlayer(name).getOwnedRailroads().stream().map(Square::getName).collect(Collectors.toList()));
        return sq;
    }

    private ArrayList<String> unmortgageAction(String name) {
        return (ArrayList<String>) GameInfo.getInstance().getPlayer(name).getMortgagedSquares().stream().map(Square::getName).collect(Collectors.toList());
    }


    private ArrayList<String> downgradeAction(String name) {
        ArrayList<String> sq = new ArrayList<>();
        for (Square p : GameInfo.getInstance().getPlayer(name).getOwnedProperties()) {
            if (GameInfo.getInstance().getCurrentPlayer().checkMajority((Property) p) && ((Property) p).isDowngradable()) {
                sq.add(p.getName());
            }
        }
        for (Square p : GameInfo.getInstance().getPlayer(name).getOwnedRailroads()) {
            if (((Railroad) p).isUpgraded()) {
                sq.add(p.getName());
            }
        }
        return sq;
    }


    private ArrayList<String> upgradeAction(String name) {
        ArrayList<String> sq = new ArrayList<>();
        for (Square p : GameInfo.getInstance().getPlayer(name).getOwnedProperties()) {
            if (GameInfo.getInstance().getCurrentPlayer().checkMajority((Property) p) && ((Property) p).isUpgradable()) {
                sq.add(p.getName());
            }
        }
        for (Square p : GameInfo.getInstance().getPlayer(name).getOwnedRailroads()) {
            if (!((Railroad) p).isUpgraded()) {
                sq.add(p.getName());
            }
        }
        return sq;
    }
}
