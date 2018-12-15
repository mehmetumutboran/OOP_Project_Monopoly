package domain.server.move;

import domain.server.board.Board;
import domain.server.controller.ServerCommunicationHandler;
import domain.util.Flags;
import domain.util.GameInfo;
import domain.util.MessageConverter;

public class MoveControl {

    private static MoveControl instance;
    private static final int SECOND_LAYER_SQ = 24;
    private static final int FIRST_LAYER_SQ = 40;
    private static final int ZEROTH_LAYER_SQ = 56;

    private MoveControl() {
    }

    public static MoveControl getInstance() {
        if (instance == null) instance = new MoveControl();
        return instance;
    }

    /**
     * This method gets a player name and moves this player corresponding to the total roll that player rolled. It uses {@link NormalMove,UpDownMove,ReverseMove}.
     *
     * @param name The name of the player that are going to change location.
     * @return The name of the square that the player moved.
     */
    public String move(String name) {
        int[] lastLoc = GameInfo.getInstance().getPlayer(name).getToken().getLocation();
        int[] newLoc;
        int totalRoll;
        int layerSQNumber = 0;
        switch (lastLoc[0]) {
            case 0:
                layerSQNumber = ZEROTH_LAYER_SQ;
                break;
            case 1:
                layerSQNumber = FIRST_LAYER_SQ;
                break;
            case 2:
                layerSQNumber = SECOND_LAYER_SQ;
                break;
        }
        totalRoll = getTotalRoll(name);
        if (Board.getInstance().railRoadFind(lastLoc, totalRoll)[0] != null) {
            if (totalRoll % 2 == 1) {
                newLoc = NormalMove.getInstance().move(lastLoc, totalRoll, layerSQNumber);
            } else {
                newLoc = UpDownMove.getInstance().move(lastLoc, totalRoll, layerSQNumber);
            }
        } else {
            newLoc = NormalMove.getInstance().move(lastLoc, totalRoll, layerSQNumber);
        }

        return MessageConverter.convertArrayToString(newLoc);
    }

    /**
     * This method calculates the total face values of the given player.
     *
     * @param name The name of the player that we want to get total roll of.
     * @return total of the face values of the given player
     */
    public int getTotalRoll(String name) {
        int totalRoll;
        if (GameInfo.getInstance().getPlayer(name).getFaceValues()[2] <= 3) {
            totalRoll = GameInfo.getInstance().getPlayer(name).getFaceValues()[0]
                    + GameInfo.getInstance().getPlayer(name).getFaceValues()[1]
                    + GameInfo.getInstance().getPlayer(name).getFaceValues()[2];
        } else {
            totalRoll = GameInfo.getInstance().getPlayer(name).getFaceValues()[0]
                    + GameInfo.getInstance().getPlayer(name).getFaceValues()[1];
        }
        return totalRoll;
    }

    private boolean checkDouble(String name) {
        return (GameInfo.getInstance().getPlayer(name).getFaceValues()[0] ==
                GameInfo.getInstance().getPlayer(name).getFaceValues()[1]);
    }

    private boolean checkJail(String name) {
        return GameInfo.getInstance().getPlayer(name).isInJail();
    }

    private boolean checkBus(String name) {
        return false;
    }

    private boolean checkTriple(String name) {
        return (GameInfo.getInstance().getPlayer(name).getFaceValues()[0] ==
                GameInfo.getInstance().getPlayer(name).getFaceValues()[1] && GameInfo.getInstance().getPlayer(name).getFaceValues()[1] ==
                GameInfo.getInstance().getPlayer(name).getFaceValues()[2]);
    }

    private boolean checkThirdDouble(String name) {
        if (GameInfo.getInstance().getPlayer(name).getDoubleCounter() == 3) {
            ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("DoubleCounter"), name, "0");
            return true;
        }
        return false;
    }

    public boolean checkSecondTurn(String name) {
        if (!checkDouble(name)) return false;
        else return !checkJail(name);
    }

    public void updateDoubleCounter(String name) {
        if (checkDouble(name) && !checkJail(name)) {
            ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("DoubleCounter"), name, "1");
        }
    }

    /**
     * This method checks whether the player rolled third double, or in jail, or rolled triple or rolled bus. Then does corresponding actions of them and returns false.
     * If these are not the case simply returns true. For example, it returns false(does not allow to move) and sends the player to jail because the player rolled three double in same turn.
     *
     * @param name The name of the player that will move.
     * @return true if all pre-conditions of move is happened, if not returns false.
     */
    public boolean checkMoveConditions(String name) {
        if (checkThirdDouble(name)) {
            sendToJail(name);
            return false;
        } else if (checkJail(name)) {
            tryToGoOutOfJail(name);
            return false;
        } else if (checkTriple(name)) {
            return false;
        } else return !checkBus(name);

    }

    private void tryToGoOutOfJail(String name) {
        if (checkDouble(name)) {
            ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("GoOutOfJail"), name);
            //TODO Prompt yollanabilir.
            move(name);
        }
    }

    private void sendToJail(String name) {
        ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("GoToJail"), name);
        ServerCommunicationHandler.getInstance().sendResponse(Flags.getFlag("Move"), name, MessageConverter.convertArrayToString(Board.getInstance().getNameGivenSquare("Jail").getLocation()) + "@" + "Jail");
    }
}
