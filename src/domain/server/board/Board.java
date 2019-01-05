package domain.server.board;

import domain.server.board.specialSquares.*;
import domain.server.card.ChanceCard;
import domain.server.card.Community;
import domain.server.card.chanceDeck.SocialMediaFail;
import domain.server.card.chanceDeck.HolidayBonus;
import domain.server.card.communityDeck.PayHospitalBills;
import domain.server.card.communityDeck.TheInsidersEdge;

import java.util.HashMap;

public class Board {
    private static Board instance;
    private Square[][] squareList;
    private HashMap<String, Property[]> squareMap;
    private static final int SECONDLAYERSQ = 24;
    private static final int FIRSTLAYERSQ = 40;
    private static final int ZEROTHLAYERSQ = 56;
    private static final int[][] railRoads = {{0, 7}, {0, 35}, {1, 5}, {1, 15}, {1, 25}, {1, 35}, {2, 9}, {2, 21}};
    private ChanceCard[] chanceList;
    private Community[] communityList;
    private int pool;

    //private volatile Deque<ChanceCard> chanceCardsDeck;
    //private volatile Deque<Community> communityCardsDeck;

   // private static HashMap<String, Integer> ColorNumber = new HashMap<>();


    private Board() {
        squareList = new Square[3][56];
        squareMap = new HashMap<>();
        initializeSquares();
        initializeDecks();
        pool = 0;
    }

    public static Board getInstance() {
        if (instance == null) {
            instance = new Board();
        }
        return instance;
    }

    public int getPool() {
        return pool;
    }

    public void setPool(int money) {
        this.pool = money;
    }

    public void increasePool(int money) {
        this.pool += money;
    }

   // public Deque<ChanceCard> getChanceDeckList() {
   //     return chanceCardsDeck;
   // }
     public ChanceCard[] getChanceList() {
         return chanceList;
     }

    public void setChanceList(int index, ChanceCard card) {
        chanceList[index] = card;
    }

    //  public Deque<Community> getCommunityDeckList() {
  //      return communityCardsDeck;
  //  }
  public Community[] getCommunityList() {
      return communityList;
  }

    public void setCommunityList(int index, Community card) {
        communityList[index] = card;
    }

    public Square[][] getSquareList() {
        return squareList;
    }

    public Square getSquare(int layer, int index) {
        return squareList[layer][index];
    } //TODO: Change name

    public Property[] getSameColoredSquares(String color) {
        return squareMap.get(color);
    }

    /**
     * This method takes a location and roll number and tries to find a closest railroad to that location within roll number away.
     * @param location The location that the closest railroad will be found.
     * @param roll The roll number of a player.
     * @return Square array of closest railroad.
     */
    public Square[] railRoadFind(int[] location, int roll) {
        // @requires: location.length() = 2, 0=<location[0]=<2
        // @effects: returns a new array containing closest railroad square.
        int layerSQNumber = 0;
        switch (location[0]) {
            case 0:
                layerSQNumber = ZEROTHLAYERSQ;
                break;
            case 1:
                layerSQNumber = FIRSTLAYERSQ;
                break;
            case 2:
                layerSQNumber = SECONDLAYERSQ;
                break;
        }
        Square[] closestRailRoads = new Square[1];
        for (int i = 0; i <= roll; i++) {
            if (location[1] + i <= layerSQNumber - 1) {
                if (squareList[location[0]][location[1] + i] instanceof Railroad) {
                    closestRailRoads[0] = squareList[location[0]][location[1] + i];
                    break;
                }
            } else {
                if (squareList[location[0]][location[1] + i - layerSQNumber + 1] instanceof Railroad) {
                    closestRailRoads[0] = squareList[location[0]][location[1] + i - layerSQNumber + 1];
                    break;
                }
            }
        }
        return closestRailRoads;
    }

    private void initializeDecks() {
//        communityCardsDeck = (Deque<Community>) new ArrayList<Community>();
//        chanceCardsDeck = (Deque<ChanceCard>) new ArrayList<ChanceCard>();

//        chanceCardsDeck.add(new ForwardThinker("ForwardThinker"));
//        chanceCardsDeck.add(new HolidayBonus("HolidayBonus"));
//        communityCardsDeck.add(new TheInsidersEdge("TheInsidersEdge"));
//        communityCardsDeck.add(new PayHospitalBills("PayHospitalBills"));

        chanceList = new ChanceCard[2];
        communityList = new Community[2];

        TheInsidersEdge theInsidersEdge = new TheInsidersEdge("The Insiders Edge");
        communityList[0] = theInsidersEdge;
        PayHospitalBills payHospitalBills = new PayHospitalBills("Pay Hospital Bills");
        communityList[1] = payHospitalBills;
        HolidayBonus holidayBonus = new HolidayBonus("Holiday Bonus");
        chanceList[0] = holidayBonus;
        SocialMediaFail socialMediaFail = new SocialMediaFail("Social Media Fail");
        chanceList[1] = socialMediaFail;



    }

    private void initializeSquares() {
        /*EXTRA NUMBERS :
        For property square
        * Mortgage Value
        * Houses Cost
        * Hotels
        * Skcyscrapers
        *
        For RR && Utility
        Mortgage value


        * */
        /*rents  layer 1*/
        int[] MediterranianAve = {2, 10, 30, 90, 160, 250, 750 ,0, 30,50 };
        int[] BalticAve = {4, 20, 60, 180, 320, 450, 900,0,30,50};
        int[] OrientalAve = {6, 30, 90, 270, 400, 550, 1050,0,50,50};
        int[] VermontAve = {6, 30, 90, 270, 400, 550, 1050,0,50,50};
        int[] ConnecticutAve = {8, 40, 100, 300, 450, 600, 1100,0,60,50};
        int[] StCharlesPlace = {10, 50, 150, 450, 625, 750, 1250,0,70,100};
        int[] StatesAve = {10, 50, 150, 450, 625, 750, 1250,0,70,100};
        int[] VirginiaAve = {12, 60, 180, 500, 700, 900, 1400,0,80,100};
        int[] StJamesPlace = {14, 70, 200, 550, 750, 950, 1450,0,90,100};
        int[] TennesseeAve ={14, 70, 200, 550, 750, 950, 1450,0,90,100};
        int[] NewYorkAve = {16, 80, 220, 600, 800, 1000, 1500,0,100,100};
        int[] KentuckyAve = {18, 90, 250, 700, 875, 1050, 2050,0,100,150};
        int[] IndianaAve = {18, 90, 250, 700, 875, 1050, 2050,0,100,150};
        int[] IllinoisAve = {20, 100, 300, 750, 925, 1100, 2100,0,120,150};
        int[] AtlanticAve = {22, 110, 330, 800, 975, 1150, 2150,0,130,150};
        int[] VentnorAve = {22, 110, 330, 800, 975, 1150, 2150,0,130,150};
        int[] MarvinGardens = {24, 120, 350, 850, 1025, 1200, 2200,0,140,150};
        int[] PacificAve = {26, 130, 390, 900, 1100, 1275, 2275 ,0, 150,200};
        int[] NorthCarolinaAve = {26, 130, 390, 900, 1100, 1275, 2275,0,150,200};
        int[] PennsylvaniaAve = {28, 150, 450, 1000, 2000, 1400, 2400,0,160,200};
        int[] ParkPlace = {35, 175, 500, 1100, 1300, 1500, 2500,0,200,200};
        int[] Boardwalk = {50, 200, 600, 1400, 1700, 2000, 3000,0,200,200};

        int[] LombardSt = {17,85,240,475,670,1025,1525,0,105,100};
        int[] Embarcadero = {17,85,240,475,670,1025,1525,0,105,100};
        int[] FishermansWharf = {21,105,320,780,950,1125,1625,0,125,100};

        /*rents  layer 0,2*/
        int[] BiscayneAve = {11,55,160,475,650,800,1300,0,75,50 };
        int[] MiamiAve = {9,45,120,350,500,700,1200,0,65,50};
        int[] FloridaAve = {9,45,120,350,500,700,1200,0,65,50};
        int[] BeaconSt = {30,160,470,1050,1250,1500,2500,0,165,200};
        int[] BoylstonSt = {30,160,470,1050,1250,1500,2500,0,165,200};
        int[] NewburySt = {40,185,550,1200,1500,1700,2700,0,190,200};
        int[] FifthAve = {60,220,650,1500,1800,2100,3600,0,215,300};
        int[] MadisonAve = {60,220,650,1500,1800,2100,3600,0,215,300};
        int[] WallSt = {80,300,800,1800,2200,2700,4200,0,250,300};
        int[] LakeSt ={1,5,15,45,80,125,625,0,15,50};
        int[] NicolletAve = {1,5,15,45,80,125,625,0,15,50};
        int[] HennepinAve = {1,5,15,45,120,240,350,850,0,30,50};
        int[] Esplanade = {5,25,80,225,360,600,1000,0,50,50};
        int[] KatyFreeway = {11,55,160,475,650,800,1300,0,70,100};
        int[] WestheimerRd = {11,55,160,475,650,800,1300,0,70,100};
        int[] KirbyDr = {14,70,200,550,750,950,1450,0,80,100};
        int[] CullenBlvd = {14,70,200,550,750,950,1450,0,80,100};
        int[] CanalSt = {5,25,80,225,360,600,1000,0,50,50};
        int[] MagazineSt = {8,40,100,300,450,600,1100,0,60,50};
        int[] BourbonSt = {8,40,100,300,450,600,1100,0,60,50};
        int[] DekalbAve = {17,85,240,670,840,1025,1525,0,90,100};
        int[] YoungIntlBlvd = {17,85,240,670,840,1025,1525,0,90,100};
        int[] DecaturSt= {20,100,300,750,925,1100,1600,0,100,100};
        int[] PeachtreeSt= {20,100,300,750,925,1100,1600,0,100,100};
        int[] RandolphSt= {23,115,345,825,1010,1180,2180,0,110,150};
        int[] LakeShoreDr= {23,115,345,825,1010,1180,2180,0,110,150};
        int[] WackerDr= {26,130,390,900,1100,1275,2275,0,120,150};
        int[] MichiganAve= {26,130,390,900,1100,1275,2275,0,120,150};
        int[] SouthTemple= {32,160,470,1050,1250,1500,2500,0,130,200};
        int[] WestTemple= {32,160,470,1050,1250,1500,2500,0,130,200};
        /*Hotel decreaes rent !!*/
        int[] NorthTemple= {38,170,520,1125,1425,1275,1650,0,140,200};
        int[] TempleSquare= {38,170,520,1125,1425,1275,1650,0,140,200};
        int[] SouthSt= {45,210,575,1300,1600,1800,3300,0,150,250};
        int[] BroadSt= {45,210,575,1300,1600,1800,3300,0,150,250};
        int[] WalnutSt= {55,225,630,1450,1750,2050,3550,0,160,250};
        int[] MarketSt= {55,225,630,1450,1750,2050,3550,0,160,250};
        int[] MulhollandBlvd= {70,350,750,1600,1850,2100,3600,0,175,300};
        int[] VenturaBlvd= {80,400,825,1800,2175,2550,4050,0,200,300};
        int[] RodeoDr= {90,450,900,2000,2500,3000,4500,0,250,300};

        /*cab rents different ???*/
        int[] utilityRent= {4,10,20,40,80,100,120,150,75,0};
        int[] utilityRentCab= {30,60,120,240,0,0,0,0,150,0};
        int[] railRoadRent= {25,50,200,200,0,0,0,0,100,100};




        // layer 0
        Subway sq1 = new Subway("Subway", 0, 0);
        squareList[0][0] = sq1;
        Property sq2 = new Property("Lake Street", 0, 1, 30, LakeSt, "PINK");
        squareList[0][1] = sq2;
        CommunityChest sq3 = new CommunityChest("Community Chest", 0, 2);
        squareList[0][2] = sq3;
        Property sq4 = new Property("Nicollet Avenue", 0, 3, 30, NicolletAve, "PINK");
        squareList[0][3] = sq4;
        Property sq5 = new Property("Hennepin Avenue", 0, 4, 60, HennepinAve, "PINK");
        squareList[0][4] = sq5;
        squareMap.put("PINK", new Property[]{sq2, sq4, sq5});

        BusTicket sq6 = new BusTicket("Bus Ticket", 0, 5);
        squareList[0][5] = sq6;
        Utility sq7 = new Utility("Checker Cab Co.", 0, 6, 300, utilityRentCab);
        squareList[0][6] = sq7;
        Railroad sq8 = new Railroad("Reading Railroad", 0, 7, 200, railRoadRent);
        squareList[0][7] = sq8;
        Property sq9 = new Property("Esplanade Avenue", 0, 8, 90, Esplanade, "LIGHTGREEN");
        squareList[0][8] = sq9;
        Property sq10 = new Property("Canal Street", 0, 9, 90, CanalSt, "LIGHTGREEN");
        squareList[0][9] = sq10;
        Chance sq11 = new Chance("Chance", 0, 10);
        squareList[0][10] = sq11;
        Utility sq12 = new Utility("Cable Company", 0, 11, 150, utilityRent);
        squareList[0][11] = sq12;
        Property sq13 = new Property("Magazine Street", 0, 12, 120, MagazineSt, "LIGHTGREEN");
        squareList[0][12] = sq13;
        Property sq14 = new Property("Bourbon Street", 0, 13, 120, BourbonSt, "LIGHTGREEN");
        squareList[0][13] = sq14;
        squareMap.put("LIGHTGREEN", new Property[]{sq9, sq10, sq13, sq14});

        HollandTunnel sq15 = new HollandTunnel("Holland Tunnel", 0, 14);
        squareList[0][14] = sq15;
        Auction sq16 = new Auction("Auction", 0, 15);
        squareList[0][15] = sq16;
        Property sq17 = new Property("Katy Freeway", 0, 16, 150, KatyFreeway, "LIGHTYELLOW");
        squareList[0][16] = sq17;
        Property sq18 = new Property("Westheimer Road", 0, 17, 150, WestheimerRd, "LIGHTYELLOW");
        squareList[0][17] = sq18;
        Utility sq19 = new Utility("Internet Service Provider", 0, 18, 150, utilityRent);
        squareList[0][18] = sq19;
        Property sq20 = new Property("Kirby Drive", 0, 19, 180, KirbyDr, "LIGHTYELLOW");
        squareList[0][19] = sq20;
        Property sq21 = new Property("Cullen Boulevard", 0, 20, 180, CullenBlvd, "LIGHTYELLOW");
        squareList[0][20] = sq21;
        squareMap.put("LIGHTYELLOW", new Property[]{sq17, sq18, sq20, sq21});

        Chance sq22 = new Chance("Chance", 0, 21);
        squareList[0][21] = sq22;
        Utility sq23 = new Utility("Black&white Cab Co.", 0, 22, 300, utilityRentCab);
        squareList[0][22] = sq23;
        Property sq24 = new Property("Dekalb Avenue", 0, 23, 210, DekalbAve, "MEDIUMBLUE");
        squareList[0][23] = sq24;
        CommunityChest sq25 = new CommunityChest("Community Chest", 0, 24);
        squareList[0][24] = sq25;
        Property sq26 = new Property("Andrew Young Intl Boulevard", 0, 25, 210, YoungIntlBlvd, "MEDIUMBLUE");
        squareList[0][25] = sq26;
        Property sq27 = new Property("Decatur Street", 0, 26, 240, DecaturSt, "MEDIUMBLUE");
        squareList[0][26] = sq27;
        Property sq28 = new Property("Peachtree Street", 0, 27, 240, PeachtreeSt, "MEDIUMBLUE");
        squareList[0][27] = sq28;
        squareMap.put("MEDIUMBLUE", new Property[]{sq24, sq26, sq27, sq28});

        PayDay sq29 = new PayDay("Pay Day", 0, 28);
        squareList[0][28] = sq29;
        Property sq30 = new Property("Randolph Street", 0, 29, 270, RandolphSt, "PURPLE");
        squareList[0][29] = sq30;
        Chance sq31 = new Chance("Chance", 0, 30);
        squareList[0][30] = sq31;
        Property sq32 = new Property("Lake Shore Drive", 0, 31, 270, LakeShoreDr, "PURPLE");
        squareList[0][31] = sq32;
        Property sq33 = new Property("Wacker Drive", 0, 32, 300, WackerDr, "PURPLE");
        squareList[0][32] = sq33;
        Property sq34 = new Property("Michigan Avenue", 0, 33, 300, MichiganAve, "PURPLE");
        squareList[0][33] = sq34;
        squareMap.put("PURPLE", new Property[]{sq30, sq32, sq33, sq34});

        Utility sq35 = new Utility("Yellow Cab Co.", 0, 34, 300, utilityRentCab);
        squareList[0][34] = sq35;
        Railroad sq36 = new Railroad("B.&O. Railroad", 0, 35, 200, railRoadRent);
        squareList[0][35] = sq36;
        CommunityChest sq37 = new CommunityChest("Community Chest", 0, 36);
        squareList[0][36] = sq37;
        Property sq38 = new Property("South Temple", 0, 37, 330, SouthTemple, "DARKOLIVEGREEN");
        squareList[0][37] = sq38;
        Property sq39 = new Property("West Temple", 0, 38, 330, WestTemple, "DARKOLIVEGREEN");
        squareList[0][38] = sq39;
        Utility sq40 = new Utility("Trash Collector", 0, 39, 150, utilityRent);
        squareList[0][39] = sq40;
        Property sq41 = new Property("North Temple", 0, 40, 360, NorthTemple, "DARKOLIVEGREEN");
        squareList[0][40] = sq41;
        Property sq42 = new Property("Temple Square", 0, 41, 360, TempleSquare, "DARKOLIVEGREEN");
        squareList[0][41] = sq42;
        squareMap.put("DARKOLIVEGREEN", new Property[]{sq38, sq39, sq42, sq42});

        ////////////////////// new square
        GoToJail sq43 = new GoToJail("Go to Jail", 0, 42);///!!!!!1
        squareList[0][42] = sq43;
        ///////////////////////////
        Property sq44 = new Property("South Street", 0, 43, 390, SouthSt, "LIGHTPINK");
        squareList[0][43] = sq44;
        Property sq45 = new Property("Broad Street", 0, 44, 390, BroadSt, "LIGHTPINK");
        squareList[0][44] = sq45;
        Property sq46 = new Property("Walnut Street", 0, 45, 420, WalnutSt, "LIGHTPINK");
        squareList[0][45] = sq46;
        CommunityChest sq47 = new CommunityChest("Community Chest", 0, 46);
        squareList[0][46] = sq47;
        Property sq48 = new Property("Market Street", 0, 47, 420, MarketSt, "LIGHTPINK");
        squareList[0][47] = sq48;
        squareMap.put("LIGHTPINK", new Property[]{sq44, sq45, sq46, sq48});

        BusTicket sq49 = new BusTicket("Bus Ticket", 0, 48);
        squareList[0][48] = sq49;
        Utility sq50 = new Utility("Sewage System", 0, 49, 150, utilityRent);
        squareList[0][49] = sq50;
        Utility sq51 = new Utility("Ute Cab Co.", 0, 50, 300, utilityRentCab);
        squareList[0][50] = sq51;
        BirthDayGift sq52 = new BirthDayGift("Birthday Gift", 0, 51);
        squareList[0][51] = sq52;
        Property sq53 = new Property("Mulholland Drive", 0, 52, 450, MulhollandBlvd, "BROWN");
        squareList[0][52] = sq53;
        Property sq54 = new Property("Ventura Boulevard", 0, 53, 480, VenturaBlvd, "BROWN");
        squareList[0][53] = sq54;
        Chance sq55 = new Chance("Chance", 0, 54);
        squareList[0][54] = sq55;
        Property sq56 = new Property("Rodeo Drive", 0, 55, 510, RodeoDr, "BROWN");
        squareList[0][55] = sq56;
        squareMap.put("BROWN", new Property[]{sq53, sq54, sq56});

        // layer 1

        GoSquare sq57 = new GoSquare("Go Square", 1, 0);
        squareList[1][0] = sq57;
        Property sq58 = new Property("Mediterranean Avenue", 1, 1, 60, MediterranianAve, "MEDIUMPURPLE");
        squareList[1][1] = sq58;
        CommunityChest sq59 = new CommunityChest("Community Chest", 1, 2);
        squareList[1][2] = sq59;
        Property sq60 = new Property("Baltic Avenue", 1, 3, 60, BalticAve, "MEDIUMPURPLE");
        squareList[1][3] = sq60;
        squareMap.put("MEDIUMPURPLE", new Property[]{sq58, sq60});

        IncomeTax sq61 = new IncomeTax("Income Tax", 1, 4);
        squareList[1][4] = sq61;
        Railroad sq62 = new Railroad("Reading Railroad", 1, 5, 200, railRoadRent);
        squareList[1][5] = sq62;
        Property sq63 = new Property("Oriental Avenue", 1, 6, 100, OrientalAve, "LIGHTBLUE");
        squareList[1][6] = sq63;
        Chance sq64 = new Chance("Chance", 1, 7);
        squareList[1][7] = sq64;
        Property sq65 = new Property("Vermont Avenue", 1, 8, 100, VermontAve, "LIGHTBLUE");
        squareList[1][8] = sq65;
        Property sq66 = new Property("Connecticut Avenue", 1, 9, 120, ConnecticutAve, "LIGHTBLUE");
        squareList[1][9] = sq66;
        squareMap.put("LIGHTBLUE", new Property[]{sq63, sq65, sq66});

        Jail sq67 = new Jail("Jail", 1, 10);
        squareList[1][10] = sq67;
        Property sq68 = new Property("St. Charles Place", 1, 11, 140, StCharlesPlace, "DEEPPINK");
        squareList[1][11] = sq68;
        Utility sq69 = new Utility("Electric Company", 1, 12, 150, utilityRent);
        squareList[1][12] = sq69;
        Property sq70 = new Property("States Avenue", 1, 13, 140, StatesAve, "DEEPPINK");
        squareList[1][13] = sq70;
        Property sq71 = new Property("Virginia Avenue", 1, 14, 160, VirginiaAve, "DEEPPINK");
        squareList[1][14] = sq71;
        squareMap.put("DEEPPINK", new Property[]{sq68, sq70, sq71});

        Railroad sq72 = new Railroad("Pennsylvania Railroad", 1, 15, 200, railRoadRent);
        squareList[1][15] = sq72;
        Property sq73 = new Property("St. James Place", 1, 16, 180, StJamesPlace, "ORANGE");
        squareList[1][16] = sq73;
        CommunityChest sq74 = new CommunityChest("Community Chest", 1, 17);
        squareList[1][17] = sq74;
        Property sq75 = new Property("Tennessee Avenue", 1, 18, 180, TennesseeAve, "ORANGE");
        squareList[1][18] = sq75;
        Property sq76 = new Property("New York Avenue", 1, 19, 200, NewYorkAve, "ORANGE");
        squareList[1][19] = sq76;
        squareMap.put("ORANGE", new Property[]{sq73, sq75, sq76});

        FreeParkingSquare sq77 = new FreeParkingSquare("Free Parking Square", 1, 20);
        squareList[1][20] = sq77;
        Property sq78 = new Property("Kentucky Avenue", 1, 21, 220, KentuckyAve, "RED");
        squareList[1][21] = sq78;
        Chance sq79 = new Chance("Chance", 1, 22);
        squareList[1][22] = sq79;
        Property sq80 = new Property("Indiana Avenue", 1, 23, 220, IndianaAve, "RED");
        squareList[1][23] = sq80;
        Property sq81 = new Property("Illinois Avenue", 1, 24, 240, IllinoisAve, "RED");
        squareList[1][24] = sq81;
        squareMap.put("RED", new Property[]{sq78, sq80, sq81});

        Railroad sq82 = new Railroad("B.&O. Railroad", 1, 25, 200, railRoadRent);
        squareList[1][25] = sq82;
        Property sq83 = new Property("Atlantic Avenue", 1, 26, 260, AtlanticAve, "YELLOW");
        squareList[1][26] = sq83;
        Property sq84 = new Property("Ventinor Avenue", 1, 27, 260, VentnorAve, "YELLOW");
        squareList[1][27] = sq84;
        Utility sq85 = new Utility("Water Works", 1, 28, 150, utilityRent);
        squareList[1][28] = sq85;
        Property sq86 = new Property("Marvin Gardens", 1, 29, 280, MarvinGardens, "YELLOW");
        squareList[1][29] = sq86;
        squareMap.put("YELLOW", new Property[]{sq83, sq84, sq86});

        RollThree sq87 = new RollThree("Roll Three", 1, 30);
        squareList[1][30] = sq87;
        Property sq88 = new Property("Pacific Avenue", 1, 31, 300, PacificAve, "GREEN");
        squareList[1][31] = sq88;
        Property sq89 = new Property("North Carolina Avenue", 1, 32, 300, NorthCarolinaAve, "GREEN");
        squareList[1][32] = sq89;
        CommunityChest sq90 = new CommunityChest("Community Chest", 1, 33);
        squareList[1][33] = sq90;
        Property sq91 = new Property("Pennsylvania Avenue", 1, 34, 320, PennsylvaniaAve, "GREEN");
        squareList[1][34] = sq91;
        squareMap.put("GREEN", new Property[]{sq88, sq89, sq91});

        Railroad sq92 = new Railroad("Short Line Railroad", 1, 35, 200, railRoadRent);
        squareList[1][35] = sq92;
        Chance sq93 = new Chance("Chance", 1, 36);
        squareList[1][36] = sq93;
        Property sq94 = new Property("Park Place", 1, 37, 1, ParkPlace, "BLUE");
        squareList[1][37] = sq94;
        LuxuryTax sq95 = new LuxuryTax("Luxury Tax", 1, 38);
        squareList[1][38] = sq95;
        Property sq96 = new Property("Board Walk", 1, 39, 1, Boardwalk, "BLUE");
        squareList[1][39] = sq96;
        squareMap.put("BLUE", new Property[]{sq94, sq96});

        //layer2

        SqueezePlay sq97 = new SqueezePlay("Squeeze Play", 2, 0);
        squareList[2][0] = sq97;
        Property sq98 = new Property("The Embarcadero", 2, 1, 210, Embarcadero, "WHITE");
        squareList[2][1] = sq98;
        Property sq99 = new Property("Fisherman's Wharf", 2, 2, 250, FishermansWharf, "WHITE");
        squareList[2][2] = sq99;
        Utility sq100 = new Utility("Telephone Company", 2, 3, 150, utilityRent);
        squareList[2][3] = sq100;
        CommunityChest sq101 = new CommunityChest("Community Chest", 2, 4);
        squareList[2][4] = sq101;
        Property sq102 = new Property("Beacon Street", 2, 5, 330, BeaconSt, "BLACK");
        squareList[2][5] = sq102;
        Bonus sq103 = new Bonus("Bonus", 2, 6);
        squareList[2][6] = sq103;
        Property sq104 = new Property("Boylston Street", 2, 7, 330, BoylstonSt, "BLACK");
        squareList[2][7] = sq104;
        Property sq105 = new Property("Newbury Street", 2, 8, 380, NewburySt, "BLACK");
        squareList[2][8] = sq105;
        squareMap.put("BLACK", new Property[]{sq102, sq104, sq105});

        Railroad sq106 = new Railroad("Pennsylvania Railroad", 2, 9, 200, railRoadRent);
        squareList[2][9] = sq106;
        Property sq107 = new Property("Fifth Avenue", 2, 10, 430, FifthAve, "GRAY");
        squareList[2][10] = sq107;
        Property sq108 = new Property("Madison Avenue", 2, 11, 430, MadisonAve, "GRAY");
        squareList[2][11] = sq108;
        StockExchange sq109 = new StockExchange("Stock Exchange", 2, 12);
        squareList[2][12] = sq109;
        Property sq110 = new Property("Wall Street", 2, 13, 500, WallSt, "GRAY");
        squareList[2][13] = sq110;
        squareMap.put("GRAY", new Property[]{sq107, sq108, sq110});

        TaxRefund sq111 = new TaxRefund("Tax Refund", 2, 14);
        squareList[2][14] = sq111;
        Utility sq112 = new Utility("Gas Company", 2, 15, 150, utilityRent);
        squareList[2][15] = sq112;
        Chance sq113 = new Chance("Chance", 2, 16);
        squareList[2][16] = sq113;
        Property sq114 = new Property("Florida Avenue", 2, 17, 130, FloridaAve, "SANDYBROWN");
        squareList[2][17] = sq114;
        HollandTunnel sq115 = new HollandTunnel("Holland Tunnel", 2, 18);
        squareList[2][18] = sq115;
        Property sq116 = new Property("Miami Avenue", 2, 19, 130, MiamiAve, "SANDYBROWN");
        squareList[2][19] = sq116;
        Property sq117 = new Property("Biscayne Street", 2, 20, 150, BiscayneAve, "SANDYBROWN");
        squareList[2][20] = sq117;
        squareMap.put("SANDYBROWN", new Property[]{sq114, sq116, sq117});

        Railroad sq118 = new Railroad("Short Line Railroad", 2, 21, 200, railRoadRent);
        squareList[2][21] = sq118;
        ReverseDirection sq119 = new ReverseDirection("Reverse Direction", 2, 22);
        squareList[2][22] = sq119;
        Property sq120 = new Property("Lombard Street", 2, 23, 210, LombardSt, "WHITE");
        squareList[2][23] = sq120;
        squareMap.put("WHITE", new Property[]{sq98, sq99, sq120});
    }

//    static{
//        ColorNumber.put("PINK", )
//    }
    public Square getNameGivenSquare(String name) {
//        return Arrays.stream(squareList).forEach(x -> Arrays.stream(x).filter(y -> y.getName().equals(name)).collect(Collectors.toList()).get(0));
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 56; y++) {
                if (squareList[x][y] == null) continue;
                if (name.equals(squareList[x][y].getName()))
                    return squareList[x][y];
            }
        }
        return null;
    }


}
