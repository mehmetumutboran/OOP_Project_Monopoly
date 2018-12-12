package domain.server.util;

import domain.server.GameLogic;
import domain.server.board.*;
import domain.server.board.specialSquares.Chance;
import domain.server.board.specialSquares.CommunityChest;
import domain.server.player.Player;
import domain.util.GameInfo;

public class ButtonStringGenerator {

    private static ButtonStringGenerator instance;

    private ButtonStringGenerator() {
    }

    public static ButtonStringGenerator getInstance() {
        if (instance == null) instance = new ButtonStringGenerator();
        return instance;
    }

    public String getButtonString(String name) { //buy psyR mort unm finish roll upg downg card
        Player curP = GameInfo.getInstance().getPlayer(name);
        Square curSq = Board.getInstance().getSquare(curP.getToken().getLocation()[0], curP.getToken().getLocation()[1]);
        System.out.println("\n\nSq name" + curSq.getName());
        String buttonString = null;
        if (curSq instanceof DeedSquare) {
            if (curSq instanceof Property) {
                if (!((Property) curSq).isOwned()) {
                    buttonString = "100010000110";
                } else if (((Property) curSq).getOwner().equals(name)) {
                    buttonString = "100010000110"; // Buy is open due to error message
                } else {
                    buttonString = "010000000110"; // Force pay rent

                }
            } else if (curSq instanceof Railroad) {
                if (!((Railroad) curSq).isOwned()) {
                    buttonString = "100010000110";
                } else if (((Railroad) curSq).getOwner().equals(name)) {
                    buttonString = "100010000110";
                } else {
                    buttonString = "010000000110"; // Force pay rent
                }
            } else if (curSq instanceof Utility) {
                if (!((Utility) curSq).isOwned()) {
                    buttonString = "100010000110";
                }else if (((Utility) curSq).getOwner().equals(name)) {
                    buttonString = "100010000110";
                } else {
                    buttonString = "010000000110"; // Force pay rent
                }
            }
        } else if (curSq instanceof SpecialSquareStrategy) {
            if (curSq instanceof Chance) {
                buttonString = "000000001110";
            } else if (curSq instanceof CommunityChest) {
                buttonString = "000000001110";
            } else {
                buttonString = "000010000110";
            }
        }

        if(GameInfo.getInstance().getPlayer(name).getFaceValues()[2]==7){
            char[] buttonString2 = buttonString.toCharArray();
            buttonString2[11] = '1';
            buttonString2[4] = '0';
            buttonString = String.valueOf(buttonString2);}
        return buttonString;
    }

}
