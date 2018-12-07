package domain.server.util;

import domain.server.board.*;
import domain.server.board.specialSquares.Chance;
import domain.server.board.specialSquares.CommunityChest;
import domain.server.player.Player;
import domain.util.GameInfo;

public class ButtonStringGenerator {

    private static ButtonStringGenerator instance;

    private ButtonStringGenerator(){}

    public static ButtonStringGenerator getInstance(){
        if(instance == null) instance = new ButtonStringGenerator();
        return instance;
    }

    public String getButtonString(String name){
        Player curP = GameInfo.getInstance().getPlayer(name);
        Square curSq = Board.getInstance().getSquare(curP.getToken().getLocation()[0],curP.getToken().getLocation()[1]);
        String buttonString = null;
        if(curSq instanceof DeedSquare){
            if(curSq instanceof Property){
                if(((Property) curSq).getOwner().equals(name)){

                }else{
                    if(!((Property) curSq).isOwned()){

                    }else{

                    }
                }
            }else if(curSq instanceof Railroad){
                if(((Railroad) curSq).getOwner().equals(name)){

                }else{
                    if(!((Railroad) curSq).isOwned()){

                    }else{

                    }
                }
            }else if(curSq instanceof Utility){
                if(((Utility) curSq).getOwner().equals(name)){

                }else{
                    if(!((Utility) curSq).isOwned()){

                    }else{

                    }
                }
            }
        }else if(curSq instanceof SpecialSquareStrategy){
            if(curSq instanceof Chance){

            }else if(curSq instanceof CommunityChest){

            }else{

            }
        }
        return buttonString;
    }

}
