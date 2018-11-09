package domain.board;

import domain.board.specialSquares.*;


public class Board {
    private static Board board;
    private Square[][] squareList;

    private Board() {
        squareList = new Square[3][56];
        initializeSquares();
    }

    public static Board getInstance() {
        if (board == null) {
            board = new Board();
        }
        return board;
    }


    public Square[][] getSquareList() {
        return squareList;
    }

    public Square getSquare(int layer, int index) {
        return squareList[layer][index];
    }

    private void initializeSquares() {

        // layer 0
        Subway sq1 = new Subway("subway", 0, 0);
        squareList[0][0] = sq1;
        Property sq2 = new Property("lake street", 0, 1, 30, 5, "PINK");
        squareList[0][1] = sq2;
        CommunityChest sq3 = new CommunityChest("communitychest", 0, 2);
        squareList[0][2] = sq3;
        Property sq4 = new Property("nicollet avenue", 0, 3, 30, 5, "PINK");
        squareList[0][3] = sq4;
        Property sq5 = new Property("hennepin avenue", 0, 4, 60, 15, "PINK");
        squareList[0][4] = sq5;
        BusTicket sq6 = new BusTicket("busticket", 0, 5);
        squareList[0][5] = sq6;
        Utility sq7 = new Utility("checker cab co.", 0, 6, 300, 1);
        squareList[0][6] = sq7;
        Railroad sq8 = new Railroad("reading railroad", 0, 7, 200, 25);
        squareList[0][7] = sq8;
        Property sq9 = new Property("esplanada avenue", 0, 8, 90, 25, "LIGHTGREEN");
        squareList[0][8] = sq9;
        Property sq10 = new Property("canal street", 0, 9, 90, 25, "LIGHTGREEN");
        squareList[0][9] = sq10;
        Chance sq11 = new Chance("chance", 0, 10);
        squareList[0][10] = sq11;
        Utility sq12 = new Utility("cable company", 0, 11, 150, 1);
        squareList[0][11] = sq12;
        Property sq13 = new Property("magazine street", 0, 12, 120, 40, "LIGHTGREEN");
        squareList[0][12] = sq13;
        Property sq14 = new Property("bourbon street", 0, 13, 120, 40, "LIGHTGREEN");
        squareList[0][13] = sq14;
        HollandTunnel sq15 = new HollandTunnel("hollandtunnel", 0, 14);
        squareList[0][14] = sq15;
        Auction sq16 = new Auction("auction", 0, 15);
        squareList[0][15] = sq16;
        Property sq17 = new Property("katy freeway", 0, 16, 150, 11, "LIGHTYELLOW");
        squareList[0][16] = sq17;
        Property sq18 = new Property("westheimer road", 0, 17, 150, 11, "LIGHTYELLOW");
        squareList[0][17] = sq18;
        Utility sq19 = new Utility("internet service provider", 0, 18, 150, 1);
        squareList[0][18] = sq19;
        Property sq20 = new Property("kirby drive", 0, 19, 180, 14, "LIGHTYELLOW");
        squareList[0][19] = sq20;
        Property sq21 = new Property("cullen boulevard", 0, 20, 180, 14, "LIGHTYELLOW");
        squareList[0][20] = sq21;
        Chance sq22 = new Chance("chance", 0, 21);
        squareList[0][21] = sq22;
        Utility sq23 = new Utility("black&white cab co.", 0, 22, 300, 1);
        squareList[0][22] = sq23;
        Property sq24 = new Property("dekalb avenue", 0, 23, 210, 17, "MEDIUMBLUE");
        squareList[0][23] = sq24;
        CommunityChest sq25 = new CommunityChest("communitychest", 0, 24);
        squareList[0][24] = sq25;
        Property sq26 = new Property("andrew young intl boulevard", 0, 25, 210, 17, "MEDIUMBLUE");
        squareList[0][25] = sq26;
        Property sq27 = new Property("decatur street", 0, 26, 240, 20, "MEDIUMBLUE");
        squareList[0][26] = sq27;
        Property sq28 = new Property("peachtree street", 0, 27, 240, 20, "MEDIUMBLUE");
        squareList[0][27] = sq28;
        PayDay sq29 = new PayDay("payday", 0, 28);
        squareList[0][28] = sq29;
        Property sq30 = new Property("randolph street", 0, 29, 270, 23, "PURPLE");
        squareList[0][29] = sq30;
        Chance sq31 = new Chance("chance", 0, 30);
        squareList[0][30] = sq31;
        Property sq32 = new Property("lake shore drive", 0, 31, 270, 23, "PURPLE");
        squareList[0][31] = sq32;
        Property sq33 = new Property("wacker drive", 0, 32, 300, 23, "PURPLE");
        squareList[0][32] = sq33;
        Property sq34 = new Property("michigan avenue", 0, 33, 300, 23, "PURPLE");
        squareList[0][33] = sq34;
        Utility sq35 = new Utility("yellow cab co.", 0, 34, 300, 30);
        squareList[0][34] = sq35;
        Railroad sq36 = new Railroad("b.&o. railroad", 0, 35, 200, 25);
        squareList[0][35] = sq36;
        CommunityChest sq37 = new CommunityChest("communitychest", 0, 36);
        squareList[0][36] = sq37;
        Property sq38 = new Property("south temple", 0, 37, 330, 32, "DARKOLIVEGREEN");
        squareList[0][37] = sq38;
        Property sq39 = new Property("west temple", 0, 38, 330, 32, "DARKOLIVEGREEN");
        squareList[0][38] = sq39;
        Utility sq40 = new Utility("trash collector", 0, 39, 150, 1);
        squareList[0][39] = sq40;
        Property sq41 = new Property("north temple", 0, 40, 360, 38, "DARKOLIVEGREEN");
        squareList[0][40] = sq41;
        Property sq42 = new Property("temple square", 0, 41, 360, 38, "DARKOLIVEGREEN");
        squareList[0][41] = sq42;
        ////////////////////// new square
        GoToJail sq43 = new GoToJail("gotojail", 0, 42);///!!!!!1
        squareList[0][42] = sq43;
        ///////////////////////////
        Property sq44 = new Property("south street", 0, 43, 390, 45, "LIGHTPINK");
        squareList[0][43] = sq44;
        Property sq45 = new Property("broad street", 0, 44, 390, 45, "LIGHTPINK");
        squareList[0][44] = sq45;
        Property sq46 = new Property("walnut street", 0, 45, 420, 55, "LIGHTPINK");
        squareList[0][45] = sq46;
        CommunityChest sq47 = new CommunityChest("communitychest", 0, 46);
        squareList[0][46] = sq47;
        Property sq48 = new Property("market street", 0, 47, 420, 55, "LIGHTPINK");
        squareList[0][47] = sq48;
        BusTicket sq49 = new BusTicket("busticket", 0, 48);
        squareList[0][48] = sq49;
        Utility sq50 = new Utility("sewage system", 0, 49, 150, 1);
        squareList[0][49] = sq50;
        Utility sq51 = new Utility("ute cab co.", 0, 50, 300, 1);
        squareList[0][50] = sq51;
        BirthDayGift sq52 = new BirthDayGift("birthdaygift", 0, 51);
        squareList[0][51] = sq52;
        Property sq53 = new Property("mulholland drive", 0, 52, 450, 70, "BROWN");
        squareList[0][52] = sq53;
        Property sq54 = new Property("ventura boulevard", 0, 53, 480, 80, "BROWN");
        squareList[0][53] = sq54;
        Chance sq55 = new Chance("chance", 0, 54);
        squareList[0][54] = sq55;
        Property sq56 = new Property("rodeo drive", 0, 55, 510, 90, "BROWN");
        squareList[0][55] = sq56;

        // layer 1

        GoSquare sq57 = new GoSquare("gosquare", 1, 0);
        squareList[1][0] = sq57;
        Property sq58 = new Property("mediterranean avenue", 1, 1, 60, 2, "MEDIUMPURPLE");
        squareList[1][1] = sq58;
        CommunityChest sq59 = new CommunityChest("communitychest", 1, 2);
        squareList[1][2] = sq59;
        Property sq60 = new Property("baltic avenue", 1, 3, 60, 4, "MEDIUMPURPLE");
        squareList[1][3] = sq60;
        IncomeTax sq61 = new IncomeTax("incometax", 1, 4);
        squareList[1][4] = sq61;
        Railroad sq62 = new Railroad("reading railroad", 1, 5, 200, 25);
        squareList[1][5] = sq62;
        Property sq63 = new Property("oriental avenue", 1, 6, 100, 6, "LIGHTBLUE");
        squareList[1][6] = sq63;
        Chance sq64 = new Chance("chance", 1, 7);
        squareList[1][7] = sq64;
        Property sq65 = new Property("vermont avenue", 1, 8, 100, 6, "LIGHTBLUE");
        squareList[1][8] = sq65;
        Property sq66 = new Property("connecticut avenue", 1, 9, 120, 8, "LIGHTBLUE");
        squareList[1][9] = sq66;
        Jail sq67 = new Jail("jail", 1, 10);
        squareList[1][10] = sq67;
        Property sq68 = new Property("st. charles place", 1, 11, 140, 10, "DEEPPINK");
        squareList[1][11] = sq68;
        Utility sq69 = new Utility("electric company", 1, 12, 150, 1);
        squareList[1][12] = sq69;
        Property sq70 = new Property("states avenue", 1, 13, 140, 10, "DEEPPINK");
        squareList[1][13] = sq70;
        Property sq71 = new Property("virginia avenue", 1, 14, 160, 12, "DEEPPINK");
        squareList[1][14] = sq71;
        Railroad sq72 = new Railroad("pennsylvania railroad", 1, 15, 200, 25);
        squareList[1][15] = sq72;
        Property sq73 = new Property("st. james place", 1, 16, 180, 14, "ORANGE");
        squareList[1][16] = sq73;
        CommunityChest sq74 = new CommunityChest("communitychest", 1, 17);
        squareList[1][17] = sq74;
        Property sq75 = new Property("tennessee avenue", 1, 18, 180, 14, "ORANGE");
        squareList[1][18] = sq75;
        Property sq76 = new Property("new york avenue", 1, 19, 200, 16, "ORANGE");
        squareList[1][19] = sq76;
        FreeParkingSquare sq77 = new FreeParkingSquare("freeparkingsquare", 1, 20);
        squareList[1][20] = sq77;
        Property sq78 = new Property("kentucky avenue", 1, 21, 220, 18, "RED");
        squareList[1][21] = sq78;
        Chance sq79 = new Chance("chance", 1, 22);
        squareList[1][22] = sq79;
        Property sq80 = new Property("indiana avenue", 1, 23, 220, 18, "RED");
        squareList[1][23] = sq80;
        Property sq81 = new Property("illinois avenue", 1, 24, 240, 20, "RED");
        squareList[1][24] = sq81;
        Railroad sq82 = new Railroad("b.&o. railroad", 1, 25, 200, 25);
        squareList[1][25] = sq82;
        Property sq83 = new Property("atlantic avenue", 1, 26, 260, 22, "YELLOW");
        squareList[1][26] = sq83;
        Property sq84 = new Property("ventinor avenue", 1, 27, 260, 22, "YELLOW");
        squareList[1][27] = sq84;
        Utility sq85 = new Utility("water works", 1, 28, 150, 1);
        squareList[1][28] = sq85;
        Property sq86 = new Property("marvin gardens", 1, 29, 280, 24, "YELLOW");
        squareList[1][29] = sq86;
        RollThree sq87 = new RollThree("rollthree", 1, 30);
        squareList[1][30] = sq87;
        Property sq88 = new Property("pacific avenue", 1, 31, 300, 26, "GREEN");
        squareList[1][31] = sq88;
        Property sq89 = new Property("north carolina avenue", 1, 32, 300, 26, "GREEN");
        squareList[1][32] = sq89;
        CommunityChest sq90 = new CommunityChest("communitychest", 1, 33);
        squareList[1][33] = sq90;
        Property sq91 = new Property("pennsylvania avenue", 1, 34, 320, 28, "GREEN");
        squareList[1][34] = sq91;
        Railroad sq92 = new Railroad("shortlinerailroad", 1, 35, 200, 25);
        squareList[1][35] = sq92;
        Chance sq93 = new Chance("chance", 1, 36);
        squareList[1][36] = sq93;
        Property sq94 = new Property("park place", 350, 37, 1, 35, "BLUE");
        squareList[1][37] = sq94;
        LuxuryTax sq95 = new LuxuryTax("luxurytax", 1, 38);
        squareList[1][38] = sq95;
        Property sq96 = new Property("boardwalk", 400, 39, 1, 50, "BLUE");
        squareList[1][39] = sq96;

        //layer2

        SqueezePlay sq97 = new SqueezePlay("squeezeplay", 2, 0);
        squareList[2][0] = sq97;
        Property sq98 = new Property("the embarcadero", 2, 1, 210, 17, "WHITE");
        squareList[2][1] = sq98;
        Property sq99 = new Property("fisherman's wharf", 2, 2, 250, 21, "WHITE");
        squareList[2][2] = sq99;
        Utility sq100 = new Utility("telephone company", 2, 3, 150, 1);
        squareList[2][3] = sq100;
        CommunityChest sq101 = new CommunityChest("communitychest", 2, 4);
        squareList[2][4] = sq101;
        Property sq102 = new Property("beacon street", 2, 5, 330, 30, "BLACK");
        squareList[2][5] = sq102;
        Bonus sq103 = new Bonus("bonus", 2, 6);
        squareList[2][6] = sq103;
        Property sq104 = new Property("boylston street", 2, 7, 330, 30, "BLACK");
        squareList[2][7] = sq104;
        Property sq105 = new Property("newbury street", 2, 8, 380, 40, "BLACK");
        squareList[2][8] = sq105;
        Railroad sq106 = new Railroad("pennsylvania railroad", 2, 9, 200, 25);
        squareList[2][9] = sq106;
        Property sq107 = new Property("fifth avenue", 2, 10, 430, 60, "PURPLE");
        squareList[2][10] = sq107;
        Property sq108 = new Property("madison avenue", 2, 11, 430, 60, "PURPLE");
        squareList[2][11] = sq108;
        StockExchange sq109 = new StockExchange("stockexchange", 2, 12);
        squareList[2][12] = sq109;
        Property sq110 = new Property("wall street", 2, 13, 500, 80, "GRAY");
        squareList[2][13] = sq110;
        TaxRefund sq111 = new TaxRefund("taxrefund", 2, 14);
        squareList[2][14] = sq111;
        Utility sq112 = new Utility("gas company", 2, 15, 150, 1);
        squareList[2][15] = sq112;
        Chance sq113 = new Chance("chance", 2, 16);
        squareList[2][16] = sq113;
        Property sq114 = new Property("florida avenue", 2, 17, 130, 9, "SANDYBROWN");
        squareList[2][17] = sq114;
        HollandTunnel sq115 = new HollandTunnel("hollandtunnel", 2, 18);
        squareList[2][18] = sq115;
        Property sq116 = new Property("miami avenue", 2, 19, 130, 9, "SANDYBROWN");
        squareList[2][19] = sq116;
        Property sq117 = new Property("biscayne street", 2, 20, 150, 11, "SANDYBROWN");
        squareList[2][20] = sq117;
        Railroad sq118 = new Railroad("short line railroad", 2, 21, 200, 25);
        squareList[2][21] = sq118;
        ReverseDirection sq119 = new ReverseDirection("reversedirection", 2, 22);
        squareList[2][22] = sq119;
        Property sq120 = new Property("lombard street", 2, 23, 210, 17, "WHITE");
        squareList[2][23] = sq120;


    }


}
