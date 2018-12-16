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
    private Property p3 = (Property) Board.getInstance().getSquare(0, 1);

    @BeforeEach
    void setUp() {
        GameInfo.getInstance().reset();
        player.setOwnedProperties(new ArrayList<>());
        player.addDeed(p1);
        player.addDeed(p2);
        player.addDeed(p3);
        p1.setOwner(player.getName());
        p2.setOwner(player.getName());
        p3.setOwner(player.getName());
        GameInfo.getInstance().addPlayer(player);
        p1.setBuildingList(new ArrayList<>());
        p2.setBuildingList(new ArrayList<>());
        p3.setBuildingList(new ArrayList<>());
    }

    @Test
    void upgrade() {
        p1.upgrade();
        ArrayList<Building> arrayList = new ArrayList<>();
        arrayList.add(new House(p1.buildingCost));
        //Test1 One house
        assertEquals(arrayList, p1.getBuildingList());
        assertTrue(p1.isUpgraded());

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
        //Test4 Skyscraper
        assertEquals(arrayList, p1.getBuildingList());

        p1.upgrade();
        arrayList.clear();
        arrayList.add(new Skyscraper(p1.getBuildingCost()));
        //Test5 After Skyscraper
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
        //Test1 no building
        assertTrue(p1.isUpgradable());

        p1.upgrade();
        //Test2 for 1 house
        assertFalse(p1.isUpgradable());

        p1.upgrade();
        p1.upgrade();
        p1.upgrade();
        p1.upgrade();
        p1.upgrade();
        //Test3 For Skyscraper
        assertFalse(p1.isUpgradable());

        p1.setBuildingList(new ArrayList<>());

        p2.upgrade();
        p2.upgrade();
        p2.upgrade();
        p2.upgrade(); //4 House

        p3.upgrade();
        p3.upgrade();
        p3.upgrade();
        p3.upgrade(); //4 House

        p1.upgrade();
        p1.upgrade();
        p1.upgrade();
        p1.upgrade();
        p1.upgrade();  //Hotel

        //Test4 Hotel - House - House
        assertFalse(p1.isUpgradable());

        //Test5 House - House - Hotel
        assertTrue(p2.isUpgradable());
        assertTrue(p3.isUpgradable());


    }

    @Test
    void isDowngradable() {
        ArrayList<Building> arrayList1 = new ArrayList<>();
        arrayList1.add(new Skyscraper(p1.getBuildingCost()));
        ArrayList<Building> arrayList2 = new ArrayList<>();
        arrayList2.add(new Skyscraper(p2.getBuildingCost()));
        ArrayList<Building> arrayList3 = new ArrayList<>();
        arrayList3.add(new Skyscraper(p3.getBuildingCost()));
        p1.setBuildingList(arrayList1);
        p2.setBuildingList(arrayList2);
        p3.setBuildingList(arrayList3);
        p1.setUpgraded(true);
        p2.setUpgraded(true);
        p3.setUpgraded(true);

        //Test1 all squares have skyscraper
        assertTrue(p1.isDowngradable());
        assertTrue(p2.isDowngradable());
        assertTrue(p3.isDowngradable());

        p1.downgrade();
        //Test2 p1 has Hotel others Skyscraper
        assertFalse(p1.isDowngradable());
        assertTrue(p2.isDowngradable());
        assertTrue(p3.isDowngradable());

        p3.downgrade();
        //Test3 only p2 has Skyscraper
        assertFalse(p1.isDowngradable());
        assertTrue(p2.isDowngradable());
        assertFalse(p3.isDowngradable());

        p2.downgrade();
        //Test4 all same level(Hotel)
        assertTrue(p1.isDowngradable());
        assertTrue(p2.isDowngradable());
        assertTrue(p3.isDowngradable());


        p1.setBuildingList(new ArrayList<>());
        p2.setBuildingList(new ArrayList<>());
        p3.setBuildingList(new ArrayList<>());

        //Test5 empty Building lists
        assertFalse(p1.isDowngradable());
        assertFalse(p2.isDowngradable());
        assertFalse(p3.isDowngradable());

    }

    @Test
    void updateRent() {
        //Holds the first rent, adds a building to a property and calculates the new rent, not equal
        int rentBeforeAdditionofBuilding = p1.getCurrentRent();
        ArrayList<Building> bl = new ArrayList<>();
        bl.add(new House(p1.getBuildingCost()));
        p1.setBuildingList(bl);
        p1.updateRent();

        assertNotEquals(rentBeforeAdditionofBuilding, p1.getCurrentRent());


        //Holds the rent with building case, empties the list and calculates new rent, not equal
        int rentBeforeRemovalOfBuilding = p1.getCurrentRent();
        p1.getBuildingList().clear();
        p1.updateRent();

        assertNotEquals(rentBeforeRemovalOfBuilding, p1.getCurrentRent());


    }
}