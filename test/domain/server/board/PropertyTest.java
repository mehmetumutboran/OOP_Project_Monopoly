package domain.server.board;

import domain.server.building.Building;
import domain.server.building.Hotel;
import domain.server.building.House;
import domain.server.building.Skyscraper;
import domain.server.player.Player;
import domain.util.GameInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PropertyTest {
    private Player player = new Player("Owner");
    private Property p1 = (Property) Board.getInstance().getSquare(0, 3);
    private Property p2 = (Property) Board.getInstance().getSquare(0, 4);

    @BeforeEach
    void setUp(){
        GameInfo.getInstance().reset();
        player.addDeed(p1);
        player.addDeed(p2);
        p1.setOwner(player.getName());
        p2.setOwner(player.getName());
        GameInfo.getInstance().addPlayer(player);
        p1.setBuildingList(new ArrayList<>());
        p2.setBuildingList(new ArrayList<>());
    }

    @Test
    void upgrade() {
        p1.upgrade();
        ArrayList<Building> arrayList = new ArrayList<>();
        arrayList.add(new House(p1.buildingCost));
        //Test1 One house
        assertEquals(arrayList, p1.getBuildingList());

        p1.upgrade();
        p1.upgrade();
        p1.upgrade();

        arrayList.add(new House(p1.buildingCost));
        arrayList.add(new House(p1.buildingCost));
        arrayList.add(new House(p1.buildingCost));
        //Test2 Four houses
        assertEquals(arrayList, p1.getBuildingList());

        p1.upgrade();
        arrayList.clear();
        arrayList.add(new Hotel(p1.getBuildingCost()));
        //Test3 Hotel
        assertEquals(arrayList, p1.getBuildingList());

        p1.upgrade();
        arrayList.clear();
        arrayList.add(new Skyscraper(p1.getBuildingCost()));
        //Test4 Hotel
        assertEquals(arrayList, p1.getBuildingList());

    }

    @Test
    void downgrade() {
        p1.upgrade();
        p1.upgrade();
        p1.upgrade();
        p1.upgrade();
        p1.upgrade();
        p1.upgrade(); //Upgraded to Skyscraper level

        ArrayList<Building> arrayList = new ArrayList<>();
        arrayList.add(new Skyscraper(p1.getBuildingCost()));
        assertEquals(arrayList, p1.getBuildingList());

        //Test1 One downgrade (Hotel)
        p1.downgrade();
        arrayList.clear();
        arrayList.add(new Hotel(p1.getBuildingCost()));
        assertEquals(arrayList, p1.getBuildingList());

        //Test2 Downgrade from hotel to 4 houses
        p1.downgrade();
        arrayList.clear();
        arrayList.add(new House(p1.getBuildingCost()));
        arrayList.add(new House(p1.getBuildingCost()));
        arrayList.add(new House(p1.getBuildingCost()));
        arrayList.add(new House(p1.getBuildingCost()));
        assertEquals(arrayList, p1.getBuildingList());

        //Test3 Downgrade from 4 houses to 1 house
        p1.downgrade();
        p1.downgrade();
        p1.downgrade();
        arrayList.clear();
        arrayList.add(new House(p1.getBuildingCost()));
        assertEquals(arrayList, p1.getBuildingList());

        //Test4 Downgrade from 1 house
        p1.downgrade();
        arrayList.clear();
        assertEquals(arrayList, p1.getBuildingList());

        //Test5 Already empty buildingList
        p1.downgrade();
        assertEquals(arrayList, p1.getBuildingList());

    }

    @Test
    void isUpgradable() {
    }

    @Test
    void isDowngradable() {
    }
}