package domain.server.interpreter;

import domain.client.PlayerActionController;
import domain.server.board.Board;
import domain.server.board.DeedSquare;
import domain.server.move.MoveControl;
import domain.server.player.Player;
import domain.util.GameInfo;
import network.server.serverFacade.ServerFacade;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MrMonopolyRequestInterpreterTest {


    private Player player1;

    @BeforeEach
    void setUp() {
        GameInfo.getInstance().reset();
        PlayerActionController.getInstance().host("Test2", 2222, true);
        while (GameInfo.getInstance().getPlayer("Test2") == null) continue;
        player1 = GameInfo.getInstance().getPlayer("Test2");
        GameInfo.getInstance().getPlayer("Test2").setFaceValues(new int[] {1,1,7});
        ((DeedSquare)Board.getInstance().getSquare(1,3)).setOwner("owner2");
        ((DeedSquare)Board.getInstance().getSquare(1,5)).setOwner("owner2");
        ((DeedSquare)Board.getInstance().getSquare(1,6)).setOwner("owner2");
        ((DeedSquare)Board.getInstance().getSquare(1,8)).setOwner("owner2");
        ((DeedSquare)Board.getInstance().getSquare(1,9)).setOwner("owner2");
    }


    @Test
    void interpret() {

        int[]  locat = MoveControl.getInstance().findNextUnOwnedSquare(new int[] {1,2});

  //      MoveControl.getInstance().checkMrMonopoly("Test");
        int location1 = 1;
        int location2 = 11;
        assertEquals(location1, locat[0]);
        assertEquals(location2, locat[1]);



    }

    @AfterEach
    void shutDown(){
        ServerFacade.getInstance().shutDown();
    }
}