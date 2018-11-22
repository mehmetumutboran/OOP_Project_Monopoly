package domain.interpreter;

import domain.GameLogic;
import domain.MessageConverter;
import domain.UIUpdater;
import domain.player.Player;

public class TokenMovementInterpreter implements Interpreter {
    @Override
    public void interpret(String[] message) {

        String name = message[1];

        int xLoc = 0;
        int yLoc = 0;
        int[] newLoc = MessageConverter.convertStringToIntArray(message[2]);

        int pIndex = 0;
        for (Player p: GameLogic.getInstance().getPlayerList()) {
            if(p.getName().equals(name)){
                pIndex = GameLogic.getInstance().getPlayerList().indexOf(p);
                break;
            }
        }

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
}
